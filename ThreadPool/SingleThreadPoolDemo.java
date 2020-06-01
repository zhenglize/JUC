package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author zhenglize
 *
 * singleThreadPool源码
public static ExecutorService newSingleThreadExecutor() {
         return new FinalizableDelegatedExecutorService
                (new ThreadPoolExecutor(1, 1,
                 0L, TimeUnit.MILLISECONDS,
               new LinkedBlockingQueue<Runnable>()));
}
主要特点如下：
1 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行。
2 newSingIeThreadExecutor将corePooISize和maximumPooISize都设置为1，它使用的LinkedBlockingQueue
 缺点:当请求堆积的时候会占用大量的内存从而导致OOM，内存溢出
*/
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService singleThreadPool= Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行了");
                    /**
                     只有一个线程执行任务
                     print
                     pool-1-thread-1执行了
                     pool-1-thread-1执行了
                     pool-1-thread-1执行了
                     pool-1-thread-1执行了
                     pool-1-thread-1执行了
                    */
                }
            });
        }
        singleThreadPool.shutdown();
    }
}
