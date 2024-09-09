package com.example.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import redis.clients.jedis.ShardedJedisPoolConfig;

@EnableCaching
@Configuration
@EnableRedisRepositories
public class RedisConfigV1 extends CachingConfigurerSupport {

  @Value("${spring.redis.database}")
  private int database;

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.password}")
  private String password;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.timeout}")
  private long timeout;

  @Value("${spring.redis.lettuce.shutdown-timeout}")
  private long shutDownTimeout;

  @Value("${spring.redis.lettuce.pool.max-idle}")
  private int maxIdle;

  @Value("${spring.redis.lettuce.pool.min-idle}")
  private int minIdle;

  @Value("${spring.redis.lettuce.pool.max-active}")
  private int maxActive;

  @Value("${spring.redis.lettuce.pool.max-wait}")
  private long maxWait;

//    @Bean
//    public RedisConnectionFactory lettuceConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }

  @Bean
  public LettuceConnectionFactory lettuceConnectionFactory() {
    ShardedJedisPoolConfig genericObjectPoolConfig = new ShardedJedisPoolConfig();
    genericObjectPoolConfig.setMaxIdle(maxIdle);
    genericObjectPoolConfig.setMinIdle(minIdle);
    genericObjectPoolConfig.setMaxTotal(maxActive);
    genericObjectPoolConfig.setMaxWait(Duration.ofMillis(maxWait));
    genericObjectPoolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(100));
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setDatabase(database);
    redisStandaloneConfiguration.setHostName(host);
    redisStandaloneConfiguration.setPort(port);
    redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
    LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(timeout))
        .shutdownTimeout(Duration.ofMillis(shutDownTimeout)).poolConfig(genericObjectPoolConfig).build();

    return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
  }

  @Primary
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    //Jedis 4.0 移除了 JedisShardInfo，可使用 new LettuceConnectionFactory()
    //JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

    return lettuceConnectionFactory();
  }

  @Bean
  public RedisSerializer<Object> redisSerializer() {
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);

    return jackson2JsonRedisSerializer;
  }


  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(cf);

    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    RedisSerializer<String> stringSerializer = new StringRedisSerializer();
    // key采用String的序列化方式
    redisTemplate.setKeySerializer(stringSerializer);
    // hash的key也采用String的序列化方式
    redisTemplate.setHashKeySerializer(stringSerializer);
    // value序列化方式采用jackson
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    // hash的value序列化方式采用jackson
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);

    return redisTemplate;
  }

  @Primary
  @Bean(name = "redisTemplate")
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf, RedisSerializer redisSerializer) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(cf);

    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);

    redisTemplate.setValueSerializer(redisSerializer);
    redisTemplate.setHashValueSerializer(redisSerializer);

    return redisTemplate;
  }

  @Bean
  public ListOperations<String, String> listOperations() {
    return redisTemplate(lettuceConnectionFactory(), redisSerializer()).opsForList();
  }

  @Bean
  public ValueOperations<String, String> valueOperations() {
    return redisTemplate(lettuceConnectionFactory(), redisSerializer()).opsForValue();
  }
}
