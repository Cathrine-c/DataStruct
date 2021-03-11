package java_0124;

import java.util.LinkedList;
import java.util.Queue;

public class MyStackBy2Queue {
    /**
     * 两个队列实现栈：
     * 入栈：把元素往A中放
     * 出栈：把A中的元素往B中倒，当只剩一个元素时，把这个元素出队列，交换A和B
     */

    Queue<Integer> A = new LinkedList<>();//入栈的栈
    Queue<Integer> B = new LinkedList<>();//出栈的栈

    public void push(int x){
        A.offer(x);
    }


    public Integer pop(){
        //若两个队列都为空
        if (empty()) {
            return null;
        }

        while (A.size() > 1) {
            Integer ret = A.poll();
            B.offer(ret);
        }

        Integer res = A.poll();
        swapAB();

        return res;

    }

    private void swapAB() {
        Queue<Integer> tmp = A;
        A = B;
        B = tmp;
    }

    public Integer peek(){
        if (empty()) {
            return null;
        }

        while (A.size() > 1) {
            Integer ret = A.poll();
            B.offer(ret);
        }

        Integer res = A.poll();
        B.offer(res);
        swapAB();
        return res;
    }


    public boolean empty(){
        return A.isEmpty();
    }
}
