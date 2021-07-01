package InterviewImportant.MySingleton;


//单例模式
public class SingletonDemo1 {

    //饿汉模式，线程安全，运用在StringBuffer多线程中
    public static SingletonDemo1 instance = new SingletonDemo1();

    //私有构造方法，防止被实例化
    private SingletonDemo1(){}


    public static SingletonDemo1 getInstance(){
        return instance;
    }





}
