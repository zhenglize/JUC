package atomic.CompareAtomicLongAndLongAdder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 验证atomicLong和LongAdder的运行效率的对比
 * */
public class AtomicLongDemo {
    public static void main(String[] args) {
        //创建AtomicLong对象
        AtomicLong atomicLong=new AtomicLong(0);
        //创建20个线程池来执行任务
        ExecutorService service = Executors.newFixedThreadPool(20);
        //记录开始时间
        int start= (int) System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(atomicLong));
        }
        //让线程池停止操作
        service.shutdown();
        //等待线程池队列中的任务全部执行完成
        while (!service.isTerminated()){

        }
        //记录结束时间
        int end= (int) System.currentTimeMillis();
        System.out.println("最后的结果是:"+atomicLong.get());
        System.out.println("执行时间为:"+((end-start)/1000)+"秒");
        /**
          print 2s
        */
    }
    //定义一个runnable对象来验证性能
    static class Task implements Runnable{
        AtomicLong atomicLong;

        public Task(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicLong.incrementAndGet();
            }
        }
    }
}
