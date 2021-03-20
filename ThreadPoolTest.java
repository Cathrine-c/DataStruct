package java_0115;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,//核心线程数
                10,//最大线程数:正式员工+临时工
                60,
                TimeUnit.SECONDS,//idle线程的空闲时间：临时工最大的存活时间,超过时间就解雇
                new LinkedBlockingDeque<>(),//阻塞队列：存放任务的地方
                new ThreadFactory(){
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(new Runnable() {
                            @Override
                            public void run() {
                                long start = System.currentTimeMillis();
                                r.run();
                                long end = System.currentTimeMillis();
                                System.out.println("任务执行了"+(end-start));
                            }
                        });

                    }
                },//创建线程的工厂类：线程池创建线程时，调用该工厂
                new ThreadPoolExecutor.AbortPolicy()//拒绝策略：如果线程已满，且阻塞队列也满了，就采取拒绝策略

                /**
                 * 4种拒绝策略：
                 * AbortPolicy直接抛RejectExecutionException
                 * CallRunPolicy：谁（某个线程）交给我（线程池）任务，我拒绝执行
                 * DiscardPolicy:交给我的任务，直接丢弃
                 * DiscardOldestPolicy:丢弃阻塞队列中最旧的任务
                 *
                 */

        );
        //线程池创建之后，只要有任务就自动执行

        for(int i =0;i<1000;i++){
            //线程池执行任务：execute、submit-->提交执行一个任务
           pool.execute(new Runnable() {
               @Override
               public void run() {

               }
           });

        }
    }


}
