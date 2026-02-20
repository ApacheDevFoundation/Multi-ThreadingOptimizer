package org.apache.multithread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ThreadSafeLock provides thread-safe read/write locking mechanisms.
 * It uses ReentrantReadWriteLock to allow multiple readers or a single writer.
 */
public class ThreadSafeLock {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private volatile int sharedData = 0;

    /**
     * Reads the shared data with a read lock.
     *
     * @return the current value of shared data
     */
    public int readData() {
        lock.readLock().lock();
        try {
            return sharedData;
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Writes to the shared data with a write lock.
     *
     * @param value the value to write
     */
    public void writeData(int value) {
        lock.writeLock().lock();
        try {
            this.sharedData = value;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Increments the shared data atomically with a write lock.
     */
    public void incrementData() {
        lock.writeLock().lock();
        try {
            this.sharedData++;
        } finally {
            lock.writeLock().unlock();
        }
    }
}