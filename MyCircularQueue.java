package InterviewImportant.DS;

public class MyCircularQueue {

    //设计循环队列
    //用数组实现
    private int[] arr;
    private int frontIndex;
    //数组长度
    private int queueSize;
    private int count;

    //k表示数组大小
    public MyCircularQueue(int k) {
        //初始化
        arr = new int[k];
        frontIndex =-1;
        queueSize = k;
        count =0;

    }


    //向循环队列插入一个元素，成功插入返回真
    public boolean enQueue(int value) {
        if (count >= queueSize) {
            return false;
        }

        if (count == 0) {
            frontIndex = 0;
        }

        count++;
        int rearIndex = (frontIndex+count-1)%queueSize;
        arr[rearIndex] = value;
        return true;
    }


    //删除队列中的元素，删除成功返回true
    public boolean deQueue() {
        if (count == 0) {
            return false;
        }

        arr[frontIndex] = 0;
        frontIndex = (frontIndex+1)%queueSize;
        count--;
        return true;

    }


    //获取队首元素
    public int Front() {
        if (count ==0) {
            return -1;
        }
        return arr[frontIndex];
    }


    //获取队尾元素
    public int Rear() {
        if (count == 0) {
            return -1;
        }
        return arr[(frontIndex+count-1)%queueSize];
    }


    //判空
    public boolean isEmpty() {
        return count==0;

    }


    //判满
    public boolean isFull() {

        return count==queueSize;
    }



}
