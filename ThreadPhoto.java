package InterviewImportant.Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPhoto {


    /**
     * 线程间的通信方式
     */


    //方式1：使用volatile,设置共享变量
    /**
     * 基于 volatile 关键字来实现线程间相互通信是使用共享内存的思想，大致意思就是多个线程同时监听一个变量，
     * 当这个变量发生变化的时候 ，线程能够感知并执行相应的业务。这也是最简单的一种实现方式
     */
    static volatile boolean notice = false;

    public static void main1(String[] args) {
        List<String> list = new ArrayList<>();
        Thread threadA = new Thread(()->{
            for (int i=1;i<=10;i++){
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数个数为："+list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size()==5) notice = true;
            }
        });


        Thread threadB = new Thread(()->{

            while (true) {
                if (notice) {
                    System.out.println("线程B收到通知，开始执行自己的业务...");
                    break;
                }
            }
        });
        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }


//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//
//        Thread threadA = new Thread(()->{
//            for (int i=0;i<10;i++){
//                list.add("abc");
//                System.out.println("线程A向list里添加了一个元素,此时list中的元素为:"+list.size());
//
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                if (list.size()==5)notice = true;
//            }
//
//        });
//        Thread threadB = new Thread(()->{
//
//            while (true) {
//                if (notice) {
//                    System.out.println("threadB已经知晓，立马执行自己的业务");
//                    break;
//                }
//
//            }
//        });
//        threadB.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        threadA.start();
//    }


    //方式2：使用Object的wait()和notify()方法
    public static void main2(String[] args) {
        Object lock = new Object();

        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(()->{
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    list.add("abc");
                    System.out.println("线程A向list中添加一个元素，此时list中的元素个数个数为：" + list.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (list.size() == 5) lock.notify();

                }
            }
        });


        Thread threadB = new Thread(()->{

            while (true) {

                synchronized (lock){
                    if (list.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("线程B收到通知，开始执行自己的任务！");
                }

            }
        });

        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }


    //方式3：使用JUC工具类 CountDownLatch
    /**
     * jdk1.5之后在java.util.concurrent包下提供了很多并发编程相关的工具类，简化了我们的并发编程代码的书写，
     * ***CountDownLatch***基于AQS框架，相当于也是维护了一个线程间共享变量state
     */
    public static void main3(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(()->{
                for (int i = 1; i <= 10; i++) {
                    list.add("abc");
                    System.out.println("线程A向list中添加一个元素，此时list中的元素个数个数为：" + list.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (list.size() == 5) countDownLatch.countDown();


            }
        });


        Thread threadB = new Thread(()->{

            while (true) {

                    if (list.size() != 5) {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("线程B收到通知，开始执行自己的任务！");
                    break;
                }
        });

        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }


    //方式4：使用ReentrantLock结合Condition
    public static void main4(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition =lock.newCondition();

        List<String> list = new ArrayList<>();

        Thread threadA = new Thread(()->{
            lock.lock();
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数个数为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (list.size() == 5) condition.signal();


            }
            lock.unlock();
        });


        Thread threadB = new Thread(()->{
            lock.lock();

                if (list.size() != 5) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程B收到通知，开始执行自己的任务...");
                lock.unlock();
        });

        //需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();

    }


    //方式5：基于LockSupport实现线程间的阻塞和唤醒
    /**
     * LockSupport 是一种非常灵活的实现线程间阻塞和唤醒的工具，使用它不用关注是等待线程先进行还是唤醒线程先运行，但是得知道线程的名字。
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        final Thread threadB = new Thread(()->{

            if (list.size() != 5) {
                LockSupport.park();
            }
            System.out.println("线程B收到通知，开始执行自己的任务...");

        });

        Thread threadA = new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数个数为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (list.size() == 5) LockSupport.unpark(threadB);

            }
        });

        threadA.start();

        threadB.start();

    }


}
