package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
*@author zhenglize
ReentrantLock的其他方法介绍
◆isHeldByCurrentThread可以看出锁是否被当前线程持有
◆getQueueLength可以返回当前正在等待这把锁的队列有多长,一般这两个方法是开发和调试时候使用，上线后用到的不多
*/
public class ReentrantLockOtherMethod {
     static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) {
        System.out.println("查看被重入的次数:"+lock.getHoldCount());
        lock.lock();
        System.out.println("查看被重入的次数:"+lock.getHoldCount());
        System.out.println("是否被当前线程持有:"+lock.isHeldByCurrentThread());
        lock.unlock();
        System.out.println("查看被重入的次数:"+lock.getHoldCount());
        System.out.println("是否被当前线程持有:"+lock.isHeldByCurrentThread());
        System.out.println("等待这把锁的队列有多长:"+lock.getQueueLength());
        /**
         查看被重入的次数:0
         查看被重入的次数:1
         是否被当前线程持有:true
         查看被重入的次数:0
         是否被当前线程持有:false
         等待这把锁的队列有多长:0
        */
    }
}
