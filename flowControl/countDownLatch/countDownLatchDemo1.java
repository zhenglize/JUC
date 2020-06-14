package flowControl.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author zhenglize
 演示countdownLatch的例子，一个线程等待所有线程执行完之后再运行
*/

public class countDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        //五个线层执行完之后，才执行
        CountDownLatch latch=new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int num=i+1;
            service.submit( new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random()*10000));
                        System.out.println("No:"+num+"检修完成");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        //每执行完一个线程计数减1
                        latch.countDown();
                    }
                }
            });
        }
        System.out.println("等待检修完成");
        //主线程等待子线程全部运行完成再执行
        latch.await();
        System.out.println("全部检修完成");
    }
}
