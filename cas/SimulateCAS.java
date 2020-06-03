package cas;
/**
*@author zhenglize
  模拟CAS的实现
*/
public class SimulateCAS implements Runnable{
    volatile int value;
    public synchronized int compareAndSwap(int expected,int newValue){
        int oldValue=value;
        if (oldValue == expected){
            value=newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }

    public static void main(String[] args) throws InterruptedException {
        SimulateCAS r=new SimulateCAS();
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        r.value=0;
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }
}
