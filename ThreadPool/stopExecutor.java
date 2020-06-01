package ThreadPool;
/**
*@author zhenglize
  停止线程池
  shutdown  这种停止方法会拒绝往线程池中继续提交任务。但对于正在执行的线程和缓存队列里的线程
            会等他们全部执行完成才会停止。
  isShutdown  判断当前的线程池有没有被shutdown
  isTerminated  判断该线程池中有没有正在运行的线程
  awaitTermination 等待一段时间后，查看线程池中有没有正在运行的线程。其作用主要是用在调试时
  shutdownNow  立即停止该线程池，无论该线程池中有没有正在运行的任务。该方法会返回一个list
                用来保存被中断的线程集合
*/
public class stopExecutor {
}
