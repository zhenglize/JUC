package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author zhenglize
 * 缓存线程池的源码
    public static ExecutorService newCachedThreadPool() {
            return new ThreadPoolExecutor(0,
            Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
}
 主要特点如下：
 1 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 2 newCachedThreadPool 将corePooISize 设置为0 ，将maximumPoolSize 设置为Integer.MAX_VALUE .使用的
 SynchronousQueue,也就是说来了任务就创建线程运行,当线程空闲超过60秒,就销毁线程
 缺点:
 当创建非常多的线程的时候会导致OOM内存溢出
*/
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行了。。。");
                    /**
                     来了一个任务就创建一个线程
                     print
                     pool-1-thread-1执行了。。。
                     pool-1-thread-2执行了。。。
                     pool-1-thread-3执行了。。。
                     pool-1-thread-4执行了。。。
                     pool-1-thread-5执行了。。。
                    */
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
