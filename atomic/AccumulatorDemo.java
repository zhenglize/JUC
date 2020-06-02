package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;

/**
*@author zhenglize
   Accumulator累加器
◆Accumulator和Adder非常相似, Accumulator就是一个更通用版本的Adder
*/
public class AccumulatorDemo {
    public static void main(String[] args) {
        //创建accumulator的加法运算，给x的初始值设为0
        LongAccumulator accumulator=new LongAccumulator((x,y)->x+y,0);
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i <= 10; i++) {
            int finalI = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    accumulator.accumulate(finalI);
                }
            });
        }
        service.shutdown();
        while (!service.isTerminated()){}
        System.out.println(accumulator.getThenReset());
    }
}
