package InterviewImportant.DS;

import java.util.Stack;

public class MyQueue {

    //两个栈实现一个队列
    private Stack<Integer> stack1 ;
    private Stack<Integer> stack2 ;

    public MyQueue() {

        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }


    public void push(int x) {

        stack1.push(x);
    }


    public Integer pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
       return stack2.pop();
    }


    public Integer peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }


    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }




}
