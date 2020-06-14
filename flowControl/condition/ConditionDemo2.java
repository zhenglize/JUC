package flowControl.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
*@author zhenglize
使用condution实现生产者消费者模式
*/
public class ConditionDemo2 {
    int queueSize=10;
    private PriorityQueue<Integer> queue=new PriorityQueue<Integer>();
    private ReentrantLock lock=new ReentrantLock();
    Condition notFull=lock.newCondition();
    Condition notEmpty=lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2=new ConditionDemo2();
        consumer consumer=conditionDemo2.new consumer();
        producer producer=conditionDemo2.new producer();
        consumer.start();
        producer.start();
    }
    class consumer extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    lock.lock();
                    while (queue.size() == 0){
                        System.out.println("队列是空的，请生产数据");
                        try {
                            //队列中数据，线程被停止
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //从队列中取出一个数据
                    queue.poll();
                    //唤醒不满的条件
                    notFull.signalAll();
                    System.out.println("消费了一个元素.队列中剩余"+queue.size()+"个元素");
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    class producer extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    lock.lock();
                    while (queue.size() == queueSize){
                        System.out.println("队列是满的，请消费数据");
                        try {
                            //队列满了。线程等待
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //添加一个数据
                    queue.offer(1);
                    //唤醒不满的条件
                    notEmpty.signalAll();
                    System.out.println("生产了一个元素.队列中还能放"+(queueSize-queue.size())+"个元素");
                }finally {
                    lock.unlock();
                }

            }
        }
    }
}
