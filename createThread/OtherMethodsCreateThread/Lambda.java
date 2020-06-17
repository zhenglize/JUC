package createThread.OtherMethodsCreateThread;
/**
*@author zhenglize
 使用Lambda表达式来创建线程
*/
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
