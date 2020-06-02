package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
*@author zhenglize
演示AtomicInteger的基本用法，对比非原子类的线程安全问题，使用了原子类之后，不需要加锁，也可以保证线程安全。
*/
public class AtomicIntegerDemo1 implements Runnable {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public void incrementAtomic() {
        atomicInteger.getAndAdd(1);
    }

    private static volatile int basicCount = 0;
    /**
      普通变量需要使用Volatile并且加锁
    */
    public synchronized void incrementBasic() {
        basicCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        //两个线程对原子类和普通变量进行加10000次
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子类的结果：" + atomicInteger.get());
        System.out.println("普通变量的结果：" + basicCount);
        /**
         原子类的结果：20000
         普通变量的结果：20000
         */
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }
}
