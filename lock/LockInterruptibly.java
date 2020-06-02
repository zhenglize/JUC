package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
*@author zhenglize

*/
public class LockInterruptibly implements Runnable{
    public static Lock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterruptibly runnable=new LockInterruptibly();
        Thread t0=new Thread(runnable);
        Thread t1=new Thread(runnable);
        t0.start();
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
        /**
          print  说明 LockInterruptibly可以响应中断
         Thread-0	尝试获取锁
         Thread-0	获取到了锁
         Thread-1	尝试获取锁
         获得锁期间被中断了
         Thread-0	释放了锁
        */
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"\t尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName()+"\t获取到了锁");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"\t睡眠期间被中断了");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"\t释放了锁");
            }
        }catch (InterruptedException e){
            System.out.println("获得锁期间被中断了");
        }
    }
}
