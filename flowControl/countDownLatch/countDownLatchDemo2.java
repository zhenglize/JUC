package flowControl.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
*@author zhenglize
 模拟运动员听到裁判的发令枪响，一起跑步的例子，通过CountDownLatch进行并发流程的控制
 */
public class countDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int num =i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(num+"号运动员准备完毕。等待发令枪响");
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(num+"号运动员，开始跑步了");
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响、、、、、");
        latch.countDown();
        service.shutdown();
        /**
         3号运动员准备完毕。等待发令枪响
         4号运动员准备完毕。等待发令枪响
         2号运动员准备完毕。等待发令枪响
         1号运动员准备完毕。等待发令枪响
         5号运动员准备完毕。等待发令枪响
         发令枪响、、、、、
         3号运动员，开始跑步了
         1号运动员，开始跑步了
         5号运动员，开始跑步了
         2号运动员，开始跑步了
         4号运动员，开始跑步了
        */
    }
}
