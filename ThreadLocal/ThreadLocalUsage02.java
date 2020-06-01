package ThreadLocal;
/**
*@author zhenglize
 Threadlocal的用法二，在线程中设置全局的变量
   场景二:set
◆如果需要保存到ThreadLocal里的对象的生成时机不由我们随意控制,例如拦截器生成的用户信息，用ThreadLocal.set
  直接放到我们的ThreadLocal中去,以便后续使用。
*/
public class ThreadLocalUsage02 {
    public static void main(String[] args) {
        new Service1().process();
    }
}
//在service1中为ThreadLocal设置值
class Service1{
    public void process(){
        User user=new User("小明");
        UserContextHolder.userInfo.set(user);
        new Service2().process();
    }
}
class Service2{
    public void process(){
        //得到UserInfo设置的值
        User user = UserContextHolder.userInfo.get();
        System.out.println(user.name);
        //print 小明
        new Service3().process();
    }
}
class Service3{
    public void process(){
        //得到UserInfo设置的值
        User user = UserContextHolder.userInfo.get();
        //print 小明
        System.out.println(user.name);
        //当使用完成后一定要进行remove操作，避免内存泄露
        UserContextHolder.userInfo.remove();
    }
}
//首先不为ThreadLocal设置一个全局的对象，而是用到时，通过set方法设置
class UserContextHolder{
    public static ThreadLocal<User> userInfo=new ThreadLocal<>();
}
class User{
    String name;
    public User(String name){
        this.name=name;
    }
}
