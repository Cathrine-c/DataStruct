package java_0124;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MyStack  {

    /**
     * 1.用数组实现栈：
     */

    private int[] array = new int[100];
    private int size = 0;

    //入栈
    public void push(int x){
        array[size] = x;
        size++;

    }

    //出栈
    public int pop(){
        int ret = array[size-1];
        size--;
        return ret;
    }


    //取栈顶元素
    public int peek(){

        return array[size-1];
    }


    public static void main1(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(4);
        myStack.push(3);
        myStack.push(2);
        myStack.push(1);

        int ret1 = myStack.pop();
        System.out.println(ret1);
        int ret2 = myStack.pop();
        System.out.println(ret2);
        int ret3 = myStack.pop();
        System.out.println(ret3);
        int ret4 = myStack.pop();
        System.out.println(ret4);


    }

    //判断括号是否匹配
    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();

        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');

        for (int i =0;i<s.length();i++){
            char c = s.charAt(i);

            if (c=='('||c=='['||c=='{'){
                stack.push(c);
                continue;
            }
            if(stack.empty()){
                return false;
            }

            char top = stack.pop();
//            if (top=='('&&c==')'){
//                continue;
//            }
//            if (top=='['&&c==']'){
//                continue;
//            }
//            if (top=='{'&&c=='}'){
//                continue;
//            }

            if (map.get(top)==c){
                continue;
            }
            return false;
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;

    }



    public static void main(String[] args) {
        String s = "({[]})";
        System.out.println(isValid(s));
    }



}
