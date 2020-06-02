package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
*@author zhenglize
 ◆AtomicIntegerFieldUpdater对普通变量进行升级
  使用场景:偶尔需要一个原子get-set操作
AtomicIntegerFieldUpdater注意点
◆可见范围    被Updater修饰的变量的权限不能是private
◆不支持static  被Updater修饰的变量不能为static
  演示AtomicIntegerFieldUpdater的用法
*/
public class AtomicIntegerFieldUpdaterDemo implements Runnable{

    static Candidate tom;
    static Candidate peter;
    //将普通变量升级成原子类型，运用了反射
    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater
            .newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static class Candidate {
        //变量不能为static，或者private的
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        tom=new Candidate();
        peter=new Candidate();
        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量："+peter.score);
        System.out.println("升级后的结果"+ tom.score);
        /**
         普通变量：18801
         升级后的结果20000
        */
    }
}
