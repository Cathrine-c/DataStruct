package InterviewImportant.MySingleton;

//懒汉模式,线程不安全模式，如果是多个线程情况下，它会同时创建多个实例
public class SingletonDemo2 {


//    private static SingletonDemo2 instance;
//
//    private SingletonDemo2(){};
//
//    public static SingletonDemo2 getInstance(){
//        if (instance == null) {
//
//            instance = new SingletonDemo2();
//
//        }
//        return instance;
//    }


    //解决方式1，直接加锁
//    private static SingletonDemo2 instance;
//
//    private SingletonDemo2(){};
//
//    public static synchronized SingletonDemo2 getInstance(){
//        if (instance == null) {
//
//            instance = new SingletonDemo2();
//
//        }
//        return instance;
//    }



    //解决方式2；双重if做两次判断，提高系统处理速度
//    private static SingletonDemo2 instance;
//
//    private SingletonDemo2(){};
//
//    public static  SingletonDemo2 getInstance(){
//        if (instance == null) {
//            synchronized (SingletonDemo2.class) {
//                if (instance == null) {
//                    instance = new SingletonDemo2();
//                }
//            }
//        }
//        return instance;
//    }


    //在java指令中创建对象和赋值操作是分开进行的，也就是说instance=new SingletonDemo（）；语句是分两步执行的
    //加volatile禁止指令重排+保证内存可见性
    private static volatile SingletonDemo2 instance;

    private SingletonDemo2(){};

    public static SingletonDemo2 getInstance(){
        if (instance == null) {
            synchronized (SingletonDemo2.class){
                if (instance == null) {
                    instance = new SingletonDemo2();

                }
            }

        }
        return instance;
    }


}
