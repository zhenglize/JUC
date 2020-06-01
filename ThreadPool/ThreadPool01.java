package ThreadPool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
*@author zhenglize
  线程池状态的五种状态
1. RUNNING ： 接受新任务并处理排队任务
2. SHUTDOWN ： 不接受新任务， 但处理排队任务
3. STOP ：不接受新任务， 也不处理排队任务， 并中断正在进行的任务
4. TIDYING : 中文是整洁， 理解了中文就容易理解这个状态了：所有任务都已终止， workerCount 为零时， 线程会转换到
TIDYING状态，并将运行terminate()的钩子方法。
5. TERMINATED ：terminate()运行完成
  线程池的七个参数
1.corePoolSize ： 线程池中的常驻核心线程数。线程池在完成初始化后，默认情况下线程池中没有任何线程，线程池会等待
                   任务到来，之后会创建线程执行任务
2.maximumPoolSize ： 线程池能够容纳同时执行的最大线程数， 此值必须大于等于1。(在核心线程的基础上增加的线程)
3.keepAliveTime: 多余的空闲线程的存活时间。当前线程池数量超过corePoolSize时。当空闲时间达到keepAliveTime 值时，
                  多余空闲线程会被销毁直到只剩下corePoolSize个线程为止
4.unit: keepAIiveTime 的单位。
5.workQueue ：任务队列，被提交但尚未被执行的任务。
            三种常见的任务队列
              1. 直接交接: SynchronousQueue
              2. 有界的队列: ArrayBlockingQueue
              3. 无界队列 : LinkedBlockingQueue
6.threadFactory ： 表示生成线程池中工作线程的线程工厂， 用于创建线程一般用默认即可
                   使用Executors.defaultThreadFactory() ,创建出来的线程都在同一个线程组,拥有同样的NORM_ PRIORITY
                    优先级并且都不是守护线程。如果自己指定ThreadFactory ,那么就可以改变线程名、线程组、优先级、
                    是否是守护线程等。
7.handler: 拒绝策略， 表示当队列满了并且工作线程大于等于线程池的最大线程数
            拒绝时机:
            1.当Excutor关闭时，新提交的任务会被拒绝
            2.以及当Executor队列满了并且工作线程大于等于线程池的最大线程数
            四种拒绝策略
AbortPolicy(默认）:直接抛出RejectedExecutionException 异常阻止系统正常运行。
CallerRunsPolicy：" 调用者运行" 一种调节机制， 该策略既不会抛弃任务， 也不会抛出异常，
                而是将某些任务回退到调用者，这样做的好处是形成一个负反馈来争取时间执行自己的任务，做的一个缓冲
DiscardOldestPolicy ： 抛弃队列中等待最久的任务， 然后把当前任务加入队列中尝试再次提交当前任务。
DiscardPolicy ：直接丢弃任务， 不予任何处理也不抛出异常。如果允许任务丢失， 这是最好的一种方案。

*/
public class ThreadPool01 {
    public static void main(String[] args) {

        /**
         * 自定义一个线程池
          核心线程数为1
          最大线程数为10
        当没有任务执行时存活时间为30s
        使用无界阻塞队列
         使用默认的创建线程的方法
         使用调用者运行的拒绝策略
        */
        ExecutorService threadPool=new ThreadPoolExecutor(1,
                10,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
    }
}
