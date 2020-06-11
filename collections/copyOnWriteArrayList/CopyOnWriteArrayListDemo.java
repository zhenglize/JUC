package collections.copyOnWriteArrayList;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
*@author zhenglize
 验证 copyOnWriteArrayList可以迭代过程中可以修改，而ArrayList不行
*/
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        //验证ArrayList迭代过程中不可以被修改
       //  ArrayList<String> list=new ArrayList<>();
        //迭代过程中可以被修改，但是迭代与修改过程是完全分离的，迭代操作不会实时更新集合中的值
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("list is"+list);
            String str=iterator.next();
            System.out.println("current num is   "+str);
            if (str.equals("2")){
                list.remove("5");
            }
            if (str.equals("4")){
                list.add("3 Found");
            }
        }
    }
}
