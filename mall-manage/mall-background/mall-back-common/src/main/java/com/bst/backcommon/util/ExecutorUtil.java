package com.bst.backcommon.util;

import java.util.concurrent.*;

/**
 * 不会丢弃任务的常用线程池创建
 *
 * @author wanna
 */
public class ExecutorUtil {

    public static ExecutorService newArrayBlockingThreadPool(int coreThread, int queueSize) {
        return newArrayBlockingThreadPool(coreThread, coreThread, queueSize);
    }

    public static ExecutorService newArrayBlockingThreadPool(int coreThread, int maxThread, int queueSize) {
        return newArrayBlockingThreadPool(coreThread, maxThread, queueSize, TimeUnit.SECONDS);
    }

    public static ExecutorService newArrayBlockingThreadPool(int coreThread, int maxThread, int queueSize, TimeUnit unit) {
        return newArrayBlockingThreadPool(coreThread, maxThread, 0L, unit, new ArrayBlockingQueue<>(queueSize));
    }

    /**
     * 创建一个不会抛弃任何任务的线程池
     *
     * @param coreThread 核心线程数
     * @param maxThread  最大线程数
     * @param alive      存活时间
     * @param unit       存活时间的单位
     * @param queue      任务队列
     * @return
     */
    public static ExecutorService newArrayBlockingThreadPool(int coreThread, int maxThread, long alive, TimeUnit unit, BlockingQueue<Runnable> queue) {
        return new ThreadPoolExecutor(coreThread, maxThread, alive, unit, queue, new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
