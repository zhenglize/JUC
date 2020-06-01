package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author zhenglize
    FixedThreadPool源码
        public static ExecutorService newFixedThreadPool(int nThreads) {
            return new ThreadPoolExecutor(nThreads, nThreads,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
}
 主要特点如下
 1.创建-一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 2.newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue;
 缺点:
   由于传进去的LinkedBlockingQueue是没有容量上限的,所以当请求数越来越多,并且无法及时处理完毕的时候,也就是
    请求堆积的时候,会容易造成占用大量的内存,可能会导致OOM。
*/
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行了。。。");
                    /**
                     有三个固定的线程来执行任务
                     print
                     pool-1-thread-1执行了。。。
                     pool-1-thread-1执行了。。。
                     pool-1-thread-1执行了。。。
                     pool-1-thread-2执行了。。。
                     pool-1-thread-3执行了。。。
                     */
                }
            });
        }
        fixedThreadPool.shutdown();
    }
}
