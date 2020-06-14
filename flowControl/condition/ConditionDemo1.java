package flowControl.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
*@author zhenglize
 使用condition实现一个线程唤醒另外一个线程
*/
public class ConditionDemo1 {
    private ReentrantLock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    void method1() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("线程一开始等待");
            condition.await();
            System.out.println("线程一被唤醒");
        }finally {
            lock.unlock();
        }

    }
    void method2(){
        try {
            lock.lock();
            System.out.println("唤醒等待的线程");
            condition.signal();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 conditionDemo1=new ConditionDemo1();
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            conditionDemo1.method2();
        }).start();
        conditionDemo1.method1();
        /**
         * print
         线程一开始等待
         唤醒等待的线程
         线程一被唤醒
        */
    }
}
