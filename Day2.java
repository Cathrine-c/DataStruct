package InterviewImportant.Shuati;

import java.util.Scanner;
import java.util.Stack;

public class Day2 {

    public static void main(String[] args) {
        String s = "we are";
        System.out.println(replaceSpace1(s));

    }


    /*
    在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix == null) {
            return false;
        }
        int rows = matrix.length;

        int row = 0;
        int col = matrix[0].length - 1;

        while (row < rows && col >= 0) {

            if (matrix[row][col] > target) {
                row++;
            } else if (matrix[row][col] == target) {
                return true;
            } else {
                col--;
            }
        }
        return false;
    }


    //请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
    public static String replaceSpace(String s) {
        String str = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ' ') {
                str += "%20";


            } else {

                str += s.charAt(i);
            }

        }

        return str;
    }


    public static String replaceSpace1(String s) {

        return s.replace(" ", "%20");
    }


    public static String replaceSpace2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == ' ') sb.append("%20");
            else sb.append(c);

        }
        return sb.toString();

    }



    //输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
    public int[] reversePrint(ListNode head) {
        int size =0;
        ListNode node = head;

        while (node != null) {
            size++;
            node = node.next;

        }

        int[] arr = new int[size];

        node = head;
        for (int i=size-1;i>=0;i--){
            arr[i] = node.val;
            node = node.next;
        }

        return arr;

    }


    //利用栈先进后出的特点
    public int[] reversePrint1(ListNode head) {
        Stack<ListNode> stack = new Stack<>();

        ListNode node = head;

        while (node != null) {
            //将元素入栈
            stack.push(node);
            node = node.next;

        }

        int size = stack.size();

        int[] arr = new int[size];

        for (int i=0;i<size;i++){
            //取栈顶元素放入数组的第一个索引位置
            arr[i] = stack.peek().val;

            stack.pop();

        }
        return arr;
    }


//    //输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
//    // 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//
//
//    }




}
