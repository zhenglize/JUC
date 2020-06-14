package flowControl.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
*@author zhenglize
  使用cyclicBarrier实现当有5个线程来到之后，一起执行剩下的逻辑、（相当于一个集结点）
*/
public class cyclicBarrierDemo {
    private CyclicBarrier cyclicBarrier=new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("所有线程都到齐了，大家一起出发");
        }
    });

    public static void main(String[] args) {
        cyclicBarrierDemo cyclierBarrierDemo=new cyclicBarrierDemo();
        for (int i = 0; i <10 ; i++) {
            new Thread(new Task(i,cyclierBarrierDemo.cyclicBarrier)).start();
        }
        /**
         print
         线程0准备出发
         线程1准备出发
         线程2准备出发
         线程3准备出发
         线程6准备出发
         线程5准备出发
         线程4准备出发
         线程8准备出发
         线程7准备出发
         线程9准备出发
         线程9准备好了。等待其他线程
         线程0准备好了。等待其他线程
         线程1准备好了。等待其他线程
         线程2准备好了。等待其他线程
         线程6准备好了。等待其他线程
         所有线程都到齐了，大家一起出发
         线程6出发了
         线程9出发了
         线程1出发了
         线程0出发了
         线程2出发了
         线程8准备好了。等待其他线程
         线程7准备好了。等待其他线程
         线程5准备好了。等待其他线程
         线程4准备好了。等待其他线程
         线程3准备好了。等待其他线程
         所有线程都到齐了，大家一起出发
         线程3出发了
         线程8出发了
         线程7出发了
         线程4出发了
         线程5出发了
        */
    }
    static class Task implements Runnable{
        int id;
        CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+id+"准备出发");
            try {
                Thread.sleep((long) (Math.random()*10000));
                System.out.println("线程"+id+"准备好了。等待其他线程");
                cyclicBarrier.await();
                System.out.println("线程"+id+"出发了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
