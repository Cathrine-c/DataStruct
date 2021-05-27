package java_0115;

/**
 * 阻塞队列的实现:用于实现定时器
 * 放元素：put
 * 取元素：take
 * size：获取队列中元素的个数
 * 循环队列有的属性：存放元素的索引，取元素的索引，当前的元素的数量
 */

public class MyBlockingQueue<T> {

    //循环数组方式实现队列
    private Object[] queue ;

    //存放元素的索引
    private int putIndex;
    //取元素的索引
    private int takeIndex;
    //当前的元素的数量
    private int size;


    public MyBlockingQueue(int len){
        queue = new Object[len];

    }

    //需要考虑1.putIndex超过数组长度，2.size达到数组最大长度

    public void put(T e) throws InterruptedException {

        while (size == queue.length) {
            this.wait();
        }

        queue[putIndex] = e;

        putIndex = (putIndex+1)%queue.length;

        size++;
        notifyAll();
    }


    public T take() throws InterruptedException {

        while (size == 0) {
            this.wait();
        }

        T t = (T) queue[takeIndex];
        queue[takeIndex] = null;
        takeIndex = (takeIndex+1)%queue.length;
        size--;
        notifyAll();

        return t;
    }


    public int size(){
        return size;
    }

    //多线程的调试方式：1.打印语句  2.jconsole
    public static void main(String[] args) {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(5);
        for (int i =0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 1000; j++) {

                            myBlockingQueue.put(j);
                        }
                    }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }).start();
        }


        for (int i =0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (;;) {

                            int i =  myBlockingQueue.take();
                            System.out.println(Thread.currentThread().getName()+":"+i);
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }


}
