package flowControl.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author zhenglize
  对Demo2的优化，增加了后续到达终点的流程
*/
public class countDownLatchDemo2and1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);
        CountDownLatch latch1=new CountDownLatch(5);
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
                    try {
                        Thread.sleep((long) (Math.random()*10000));
                        System.out.println(num+"号。运动员到达终点了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        latch1.countDown();
                    }
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响、、、、、");
        latch.countDown();
        latch1.await();
        System.out.println("所有运动员都跑完了，比赛结束");
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
