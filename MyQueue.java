//package java_0124;
//
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class MyQueue {
//    static class Node{
//        int val;
//        Node next;
//
//
//        public Node(int val) {
//            this.val = val;
//        }
//    }
//
//    /**
//     * 基于链表实现队列
//     */
//
//    private Node head = null;//表头
//    private Node tail = null;//表尾
//
//
//    //入队列
//    public void offer(int val){
//
//        Node newNode = new Node(val);
//        if (head == null) {
//            head = newNode;
//            tail = newNode;
//            return;
//        }
//
//        tail.next = newNode;
//        tail = tail.next;
//
//    }
//
//    //出队列
//    public Integer poll(){
//        //如果当前队列为空
//        if (head == null) {
//            return null;
//        }
//
//        int ret = head.val;
//        head = head.next;
//        if (head == null) {
//            tail=null;
//        }
//        return ret;
//
//    }
//
//    //取队首元素
//    public Integer peek(){
//        if (head == null) {
//            return null;
//        }
//        return head.val;
//    }
//
//
//    Queue<Integer> queue = new ArrayDeque<>();
//
//
//
//
//
//
//}
