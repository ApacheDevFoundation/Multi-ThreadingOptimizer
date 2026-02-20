package org.apache.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolManager manages the creation and lifecycle of thread pools.
 * It provides a configurable thread pool for executing tasks concurrently.
 */
public class ThreadPoolManager {
    private final ExecutorService executorService;
    private final int poolSize;

    /**
     * Creates a ThreadPoolManager with a fixed thread pool of specified size.
     *
     * @param poolSize the number of threads in the pool
     */
    public ThreadPoolManager(int poolSize) {
        this.poolSize = poolSize;
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    /**
     * Submits a task to the thread pool for execution.
     *
     * @param task the task to execute
     */
    public void executeTask(Runnable task) {
        executorService.execute(task);
    }

    /**
     * Shuts down the thread pool and waits for completion.
     *
     * @param timeout the maximum time to wait
     * @param unit    the time unit
     * @return true if the executor terminated, false if the timeout elapsed
     */
    public boolean shutdown(long timeout, TimeUnit unit) {
        executorService.shutdown();
        try {
            return executorService.awaitTermination(timeout, unit);
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * Gets the pool size.
     *
     * @return the number of threads in the pool
     */
    public int getPoolSize() {
        return poolSize;
    }

    /**
     * Checks if the executor service is shut down.
     *
     * @return true if the executor service is shut down
     */
    public boolean isShutdown() {
        return executorService.isShutdown();
    }
}