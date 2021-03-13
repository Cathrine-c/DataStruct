package InterviewImportant.DS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyStack {


    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if (c=='['||c=='{'||c=='('){
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }

            char c1 = stack.pop();

            if (c1=='('&&c==')'){
                continue;
            }

            if (c1=='['&&c==']'){
                continue;
            }
            if (c1=='{'&&c1=='}'){
                continue;
            }

            return false;
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }



    //用队列实现栈
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
         queue1 = new LinkedList<>();
         queue2 = new LinkedList<>();

    }


    public void push(int x) {
        queue1.offer(x);
        if (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        //交换queue和queue2，保持queue1始终为空
        Queue q = queue1;
        queue1 = queue2;
        queue2 = q;

    }


    public Integer pop() {

        return queue2.poll();

    }


    public int top() {

        return queue2.peek();
    }


    public boolean empty() {
        return queue2.isEmpty();

    }




}

