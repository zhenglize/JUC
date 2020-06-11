package collections.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

/**
*@author zhenglize
 当使用组合操作的时候，使用HashMap的注意点
*/
public class concurrentHashMapDemo implements Runnable{
    private static ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
    public static void main(String[] args) throws InterruptedException {
        map.put("小明",0);
        new Thread(new concurrentHashMapDemo()).start();
        new Thread(new concurrentHashMapDemo()).start();
        Thread.sleep(500);
        System.out.println(map.get("小明"));
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            //concurrentHashMap只能保证get ，put方法是线程安全的并不能保证I++这样的操作是线程安全的
           while (true) {
               Integer score = map.get("小明");
               Integer newScore = score + 1;
               //   map.put("小明",newScore);
               //若想要得到预期值concurrentHashMap为我们提供了组合操作，来保证线程安全
               boolean b = map.replace("小明", score, newScore);
               //若没有修改成功则一直尝试修改
               if (b){
                   break;
               }
           }
        }
    }
}
