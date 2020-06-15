package AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
*@author zhenglize
  利用AQS。实现一个自己的一次性门闩
*/
public class OneShotLock {
    private final Sync sync=new Sync();
    //让所有线程等待的方法
    public void await(){
        sync.acquireShared(0);
    }
    //打开门闩释放所有线程的方法
    public void single(){
        sync.releaseShared(0);
    }
    //使用sync类继承AQS，重写他的方法
    class Sync extends AbstractQueuedSynchronizer{
        //获取共享锁的方法,当返回的不是1则在队列中等待。返回1则被唤醒
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState()==1 )? 1 : -1;
        }
        //获取释放锁的方法,设置值为1唤醒线程
        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLock lock=new OneShotLock();
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"尝试获取锁。");
                    lock.await();
                    System.out.println(Thread.currentThread().getName()+"获取成功，继续执行剩下的任务");
                }
            }).start();
        }
        Thread.sleep(5000);
         //释放锁
        lock.single();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"尝试获取锁。");
                lock.await();
                System.out.println(Thread.currentThread().getName()+"获取成功，继续执行剩下的任务");
            }
        }).start();
    }
}
