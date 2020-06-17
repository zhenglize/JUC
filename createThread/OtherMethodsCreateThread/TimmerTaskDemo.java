package createThread.OtherMethodsCreateThread;

import java.util.Timer;
import java.util.TimerTask;

/**
*@author zhenglize
  使用定时器的方式来创建线程
*/
public class TimmerTaskDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
