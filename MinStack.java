package java_0124;

import java.util.Stack;

/**
 * 实现一个最小栈
 */
public class MinStack {


    Stack<Integer> A = new Stack<>();
    Stack<Integer> B = new Stack<>();//B保存当前元素中的最小值

    public void push(int x){

        A.push(x);
        if (B.isEmpty()) {
            B.push(x);
            return;
        }
        int min = B.peek();
        if (x < min) {
            min = x;

        }
        B.push(min);
    }

    public Integer pop(){
        if (A.isEmpty()) {
            return null;
        }
        A.pop();
        return B.pop();

    }


    public Integer top(){

        if (A.isEmpty()) {
            return null;
        }
        return A.peek();

    }

    public Integer getMin(){

        if (B.isEmpty()) {
            return null;
        }
        return B.peek();
    }


}
