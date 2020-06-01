package ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author zhenglize
 Threadlocal的使用方法
 使用ThreadLocal独享一个对象的时候需要创建ThreadLocal对象，并且重写它的initialValue()方法，
  将需要共享的对象进行创建，使用的时候通过get方法进行获取
◆强调的是同一个请求内(同一个线程内)不同方法间的共享
◆不需重写initialValue()方法,但是必须手动调用set()方法

 ThreadLocal共享对象例子
在ThreadLocal第一次get的时候把对象给初始化出来,对象的初始化时机可以由我们控制

*/
//利用ThreadLocal，给每个线程分配自己的dateFormat对象，保证了线程安全，高效利用内存
public class ThreadLocalUsage01 {
    public static ExecutorService threadPool= Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int finalI=i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage01().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
    public String date(int seconds){
        Date date=new Date(1000*seconds);
          //通过ThreadLocal获取date对象
        SimpleDateFormat dateFormat = ThreadLocalDate.localDate.get();
        //将时间返回
        return dateFormat.format(date);
    }
}
//使用ThreadLocal，封装date对象
class ThreadLocalDate {
    public static ThreadLocal<SimpleDateFormat> localDate=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        }
    };
}
