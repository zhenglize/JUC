package ThreadPool;
/**
*@author zhenglize
 线程池数量的设置
  1.耗时IO型(读写数据库、文件、网络读写等) : 最佳线程数一般会大于cpu核心数很多倍,以JVM线程监控显示繁忙情况为
    依据,保证线程空闲可以衔接上。
  2.CPU密集的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行。
    CPU密集任务只有在真正的多核CPU上才可能得到加速(通过多线程)。
    CPU密集型(加密、计算hash等) : 最佳线程数为CPU核心数的1-2倍左右。
*/
public class setThreadPoolNumber {
}
