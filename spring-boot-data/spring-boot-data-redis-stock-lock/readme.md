```java
/*
 * Copyright 2014-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.redis.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.integration.support.locks.ExpirableLockRegistry;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.concurrent.SettableListenableFuture;

/**
 * Implementation of {@link ExpirableLockRegistry} providing a distributed lock using Redis.
 * Locks are stored under the key {@code registryKey:lockKey}. Locks expire after
 * (default 60) seconds. Threads unlocking an
 * expired lock will get an {@link IllegalStateException}. This should be
 * considered as a critical error because it is possible the protected
 * resources were compromised.
 * <p>
 * Locks are reentrant.
 * <p>
 * <b>However, locks are scoped by the registry; a lock from a different registry with the
 * same key (even if the registry uses the same 'registryKey') are different
 * locks, and the second cannot be acquired by the same thread while the first is
 * locked.</b>
 * <p>
 * <b>Note: This is not intended for low latency applications.</b> It is intended
 * for resource locking across multiple JVMs.
 * <p>
 * {@link Condition}s are not supported.
 *
 * @author Gary Russell
 * @author Konstantin Yakimov
 * @author Artem Bilan
 * @author Vedran Pavic
 * @author Unseok Kim
 *
 * @since 4.0
 *
 */
public final class RedisLockRegistry implements ExpirableLockRegistry, DisposableBean {

	private static final Log LOGGER = LogFactory.getLog(RedisLockRegistry.class);

	private static final long DEFAULT_EXPIRE_AFTER = 60000L;

	private static final int DEFAULT_CAPACITY = 100_000;

	private static final String OBTAIN_LOCK_SCRIPT =
			"local lockClientId = redis.call('GET', KEYS[1])\n" +
					"if lockClientId == ARGV[1] then\n" +
					"  redis.call('PEXPIRE', KEYS[1], ARGV[2])\n" +
					"  return true\n" +
					"elseif not lockClientId then\n" +
					"  redis.call('SET', KEYS[1], ARGV[1], 'PX', ARGV[2])\n" +
					"  return true\n" +
					"end\n" +
					"return false";

	private static final String UNLINK_UNLOCK_SCRIPT =
			"if (redis.call('unlink', KEYS[1]) == 1) then " +
					"redis.call('publish', ARGV[1], KEYS[1]) " +
					"return true " +
					"end " +
					"return false";

	private static final String DELETE_UNLOCK_SCRIPT =
			"if (redis.call('del', KEYS[1]) == 1) then " +
					"redis.call('publish', ARGV[1], KEYS[1]) " +
					"return true " +
					"end " +
					"return false";


	private final Map<String, RedisLock> locks =
			new LinkedHashMap<String, RedisLock>(16, 0.75F, true) {

				@Override
				protected boolean removeEldestEntry(Entry<String, RedisLock> eldest) {
					return size() > RedisLockRegistry.this.cacheCapacity;
				}

			};

	private final String clientId = UUID.randomUUID().toString();

	private final String registryKey;

	private final String unLockChannelKey;

	private final StringRedisTemplate redisTemplate;

	private final RedisScript<Boolean> obtainLockScript;

	private final RedisScript<Boolean> unLinkUnLockScript;

	private final RedisScript<Boolean> deleteUnLockScript;

	private final RedisUnLockNotifyMessageListener unlockNotifyMessageListener;

	private final RedisMessageListenerContainer redisMessageListenerContainer;

	private final long expireAfter;

	private int cacheCapacity = DEFAULT_CAPACITY;

	/**
	 * An {@link ExecutorService} to call {@link StringRedisTemplate#delete} in
	 * the separate thread when the current one is interrupted.
	 */
	private Executor executor =
			Executors.newCachedThreadPool(new CustomizableThreadFactory("redis-lock-registry-"));

	/**
	 * Flag to denote whether the {@link ExecutorService} was provided via the setter and
	 * thus should not be shutdown when {@link #destroy()} is called
	 */
	private boolean executorExplicitlySet;

	private volatile boolean unlinkAvailable = true;

	/**
	 * Constructs a lock registry with the default (60 second) lock expiration.
	 * @param connectionFactory The connection factory.
	 * @param registryKey The key prefix for locks.
	 */
	public RedisLockRegistry(RedisConnectionFactory connectionFactory, String registryKey) {
		this(connectionFactory, registryKey, DEFAULT_EXPIRE_AFTER);
	}

	/**
	 * Constructs a lock registry with the supplied lock expiration.
	 * @param connectionFactory The connection factory.
	 * @param registryKey The key prefix for locks.
	 * @param expireAfter The expiration in milliseconds.
	 */
	public RedisLockRegistry(RedisConnectionFactory connectionFactory, String registryKey, long expireAfter) {
		Assert.notNull(connectionFactory, "'connectionFactory' cannot be null");
		Assert.notNull(registryKey, "'registryKey' cannot be null");
		this.redisTemplate = new StringRedisTemplate(connectionFactory);
		this.obtainLockScript = new DefaultRedisScript<>(OBTAIN_LOCK_SCRIPT, Boolean.class);
		this.unLinkUnLockScript = new DefaultRedisScript<>(UNLINK_UNLOCK_SCRIPT, Boolean.class);
		this.deleteUnLockScript = new DefaultRedisScript<>(DELETE_UNLOCK_SCRIPT, Boolean.class);
		this.registryKey = registryKey;
		this.expireAfter = expireAfter;
		this.unLockChannelKey = registryKey + "-channel";
		this.unlockNotifyMessageListener = new RedisUnLockNotifyMessageListener();
		this.redisMessageListenerContainer = new RedisMessageListenerContainer();
		setupUnlockMessageListener(connectionFactory);
	}

	private void setupUnlockMessageListener(RedisConnectionFactory connectionFactory) {
		final Topic topic = new ChannelTopic(this.unLockChannelKey);
		this.redisMessageListenerContainer.setConnectionFactory(connectionFactory);
		this.redisMessageListenerContainer.setTaskExecutor(this.executor);
		this.redisMessageListenerContainer.setSubscriptionExecutor(this.executor);
		this.redisMessageListenerContainer.addMessageListener(this.unlockNotifyMessageListener, topic);
	}

	/**
	 * Set the {@link Executor}, where is not provided then a default of
	 * cached thread pool Executor will be used.
	 * @param executor the executor service
	 * @since 5.0.5
	 */
	public void setExecutor(Executor executor) {
		this.executor = executor;
		this.executorExplicitlySet = true;
		this.redisMessageListenerContainer.setTaskExecutor(this.executor);
		this.redisMessageListenerContainer.setSubscriptionExecutor(this.executor);
	}

	/**
	 * Set the capacity of cached locks.
	 * @param cacheCapacity The capacity of cached lock, (default 100_000).
	 * @since 5.5.6
	 */
	public void setCacheCapacity(int cacheCapacity) {
		this.cacheCapacity = cacheCapacity;
	}

	@Override
	public Lock obtain(Object lockKey) {
		Assert.isInstanceOf(String.class, lockKey);
		String path = (String) lockKey;
		synchronized (this.locks) {
			return this.locks.computeIfAbsent(path, RedisLock::new);
		}
	}

	@Override
	public void expireUnusedOlderThan(long age) {
		long now = System.currentTimeMillis();
		synchronized (this.locks) {
			this.locks.entrySet()
					.removeIf(entry -> {
						RedisLock lock = entry.getValue();
						return now - lock.getLockedAt() > age && !lock.isAcquiredInThisProcess();
					});
		}
	}

	@Override
	public void destroy() {
		if (!this.executorExplicitlySet) {
			((ExecutorService) this.executor).shutdown();
		}
		try {
			this.redisMessageListenerContainer.destroy();
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}

	private final class RedisLock implements Lock {

		private final String lockKey;

		private final ReentrantLock localLock = new ReentrantLock();

		private volatile long lockedAt;

		private RedisLock(String path) {
			this.lockKey = constructLockKey(path);
		}

		private String constructLockKey(String path) {
			return RedisLockRegistry.this.registryKey + ':' + path;
		}

		public long getLockedAt() {
			return this.lockedAt;
		}

		@Override
		public void lock() {
			this.localLock.lock();
			while (true) {
				try {
					if (subscribeLock(-1L)) {
						return;
					}
				}
				catch (InterruptedException e) {
					/*
					 * This method must be uninterruptible so catch and ignore
					 * interrupts and only break out of the while loop when
					 * we get the lock.
					 */
				}
				catch (Exception e) {
					this.localLock.unlock();
					rethrowAsLockException(e);
				}
			}
		}

		private void rethrowAsLockException(Exception e) {
			throw new CannotAcquireLockException("Failed to lock mutex at " + this.lockKey, e);
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			this.localLock.lockInterruptibly();
			while (true) {
				try {
					if (subscribeLock(-1L)) {
						return;
					}
				}
				catch (InterruptedException ie) {
					this.localLock.unlock();
					Thread.currentThread().interrupt();
					throw ie;
				}
				catch (Exception e) {
					this.localLock.unlock();
					rethrowAsLockException(e);
				}
			}
		}

		@Override
		public boolean tryLock() {
			try {
				return tryLock(0, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return false;
			}
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			if (!this.localLock.tryLock(time, unit)) {
				return false;
			}
			try {
				long waitTime = TimeUnit.MILLISECONDS.convert(time, unit);
				boolean acquired = subscribeLock(waitTime);
				if (!acquired) {
					this.localLock.unlock();
				}
				return acquired;
			}
			catch (Exception e) {
				this.localLock.unlock();
				rethrowAsLockException(e);
			}
			return false;
		}

		private boolean subscribeLock(long time) throws ExecutionException, InterruptedException {
			if (!obtainLock()) {
				if (!RedisLockRegistry.this.redisMessageListenerContainer.isRunning()) {
					RedisLockRegistry.this.redisMessageListenerContainer.afterPropertiesSet();
					RedisLockRegistry.this.redisMessageListenerContainer.start();
				}
				try {
					Future<String> future =
							RedisLockRegistry.this.unlockNotifyMessageListener.subscribeLock(this.lockKey);
					//DCL
					if (!obtainLock()) {
						try {
							//if short expireAfter key expire for ttl, no receive unlock msg
							long waitTime = time >= 0 ? time : RedisLockRegistry.this.expireAfter;
							future.get(waitTime, TimeUnit.MILLISECONDS);
						}
						catch (TimeoutException ignore) {
						}
						return obtainLock();
					}
				}
				finally {
					RedisLockRegistry.this.unlockNotifyMessageListener.unSubscribeLock(this.lockKey);
				}
			}
			return true;
		}

		private boolean obtainLock() {
			Boolean success =
					RedisLockRegistry.this.redisTemplate.execute(RedisLockRegistry.this.obtainLockScript,
							Collections.singletonList(this.lockKey), RedisLockRegistry.this.clientId,
							String.valueOf(RedisLockRegistry.this.expireAfter));

			boolean result = Boolean.TRUE.equals(success);

			if (result) {
				this.lockedAt = System.currentTimeMillis();
			}
			return result;
		}

		@Override
		public void unlock() {
			if (!this.localLock.isHeldByCurrentThread()) {
				throw new IllegalStateException("You do not own lock at " + this.lockKey);
			}
			if (this.localLock.getHoldCount() > 1) {
				this.localLock.unlock();
				return;
			}
			try {
				if (!isAcquiredInThisProcess()) {
					throw new IllegalStateException("Lock was released in the store due to expiration. " +
							"The integrity of data protected by this lock may have been compromised.");
				}

				if (Thread.currentThread().isInterrupted()) {
					RedisLockRegistry.this.executor.execute(this::removeLockKey);
				}
				else {
					removeLockKey();
				}

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Released lock; " + this);
				}
			}
			catch (Exception e) {
				ReflectionUtils.rethrowRuntimeException(e);
			}
			finally {
				this.localLock.unlock();
			}
		}

		private void removeLockKey() {
			if (RedisLockRegistry.this.unlinkAvailable) {
				try {
					RedisLockRegistry.this.redisTemplate.execute(
							RedisLockRegistry.this.unLinkUnLockScript, Collections.singletonList(this.lockKey),
							RedisLockRegistry.this.unLockChannelKey);
					return;
				}
				catch (Exception ex) {
					RedisLockRegistry.this.unlinkAvailable = false;
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("The UNLINK command has failed (not supported on the Redis server?); " +
								"falling back to the regular DELETE command", ex);
					}
					else {
						LOGGER.warn("The UNLINK command has failed (not supported on the Redis server?); " +
								"falling back to the regular DELETE command: " + ex.getMessage());
					}
				}
			}

			RedisLockRegistry.this.redisTemplate.execute(
					RedisLockRegistry.this.deleteUnLockScript, Collections.singletonList(this.lockKey),
					RedisLockRegistry.this.unLockChannelKey);
		}

		@Override
		public Condition newCondition() {
			throw new UnsupportedOperationException("Conditions are not supported");
		}

		public boolean isAcquiredInThisProcess() {
			return RedisLockRegistry.this.clientId.equals(
					RedisLockRegistry.this.redisTemplate.boundValueOps(this.lockKey).get());
		}

		@Override
		public String toString() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss.SSS");
			return "RedisLock [lockKey=" + this.lockKey
					+ ",lockedAt=" + dateFormat.format(new Date(this.lockedAt))
					+ ", clientId=" + RedisLockRegistry.this.clientId
					+ "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((this.lockKey == null) ? 0 : this.lockKey.hashCode());
			result = prime * result + (int) (this.lockedAt ^ (this.lockedAt >>> 32)); // NOSONAR magic number
			result = prime * result + RedisLockRegistry.this.clientId.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			RedisLock other = (RedisLock) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (!this.lockKey.equals(other.lockKey)) {
				return false;
			}
			return this.lockedAt == other.lockedAt;
		}

		private RedisLockRegistry getOuterType() {
			return RedisLockRegistry.this;
		}

	}

	private static final class RedisUnLockNotifyMessageListener implements MessageListener {

		private final Map<String, SettableListenableFuture<String>> notifyMap = new ConcurrentHashMap<>();

		@Override
		public void onMessage(Message message, byte[] pattern) {
			final String lockKey = new String(message.getBody());
			unlockNotify(lockKey);
		}

		public Future<String> subscribeLock(String lockKey) {
			return this.notifyMap.computeIfAbsent(lockKey, key -> new SettableListenableFuture<>());
		}

		public void unSubscribeLock(String localLock) {
			this.notifyMap.remove(localLock);
		}

		private void unlockNotify(String lockKey) {
			this.notifyMap.computeIfPresent(lockKey, (key, lockFuture) -> {
				lockFuture.set(key);
				return lockFuture;
			});
		}

	}

}

```
