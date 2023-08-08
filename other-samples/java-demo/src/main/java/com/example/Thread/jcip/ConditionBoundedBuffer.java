package com.example.Thread.jcip;

import com.example.Thread.jcip.annotations.GuardedBy;
import com.example.Thread.jcip.annotations.ThreadSafe;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionBoundedBuffer
 * <p/>
 * Bounded buffer using explicit condition variables
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class ConditionBoundedBuffer<T> {

  private static final int BUFFER_SIZE = 100;
  protected final Lock lock = new ReentrantLock();
  // CONDITION PREDICATE: notFull (count < items.length)
  private final Condition notFull = lock.newCondition();
  // CONDITION PREDICATE: notEmpty (count > 0)
  private final Condition notEmpty = lock.newCondition();
  @GuardedBy("lock")
  private final T[] items = (T[]) new Object[BUFFER_SIZE];
  @GuardedBy("lock")
  private int tail, head, count;

  // BLOCKS-UNTIL: notFull
  public void put(T x) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length) {
        notFull.await();
      }
      items[tail] = x;
      if (++tail == items.length) {
        tail = 0;
      }
      ++count;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  // BLOCKS-UNTIL: notEmpty
  public T take() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      T x = items[head];
      items[head] = null;
      if (++head == items.length) {
        head = 0;
      }
      --count;
      notFull.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }
}
