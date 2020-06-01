package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
*@author zhenglize
支持定时及周期性任务执行的线程池
*/
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 5; i++) {
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行了。。");
                }
            },5000, TimeUnit.MILLISECONDS);
        }
        /**
          将任务延时指定的时间执行
         print
         pool-1-thread-1执行了。。
         pool-1-thread-2执行了。。
         pool-1-thread-1执行了。。
         pool-1-thread-2执行了。。
         pool-1-thread-1执行了。。
        */
        scheduledThreadPool.shutdown();
    }
}
