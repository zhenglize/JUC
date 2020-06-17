package createThread;
/**
*@author zhenglize
  使用实现Runnable接口的方法，创建线程
*/
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
