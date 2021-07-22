package InterviewImportant.jianzhi;

import java.util.*;

public class Day0722 {


    //输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    // 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
    // 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();

        int cur = 0;

        for (int i=0;i<pushed.length;i++){

            stack.push(pushed[i]);

            while (!stack.isEmpty()&&stack.peek()==popped[cur]){
                stack.pop();
                cur++;

            }
        }

        return stack.isEmpty();

    }


    //二叉树层序遍历
    public int[] levelOrder(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if(root==null){
            return new int[]{};
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            while(size-->0){
                TreeNode node = queue.poll();
                res.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);

                }

                if(node.right!=null){
                    queue.offer(node.right);

                }
            }

        }

        //链表转数组
        return res.stream().mapToInt(Integer::intValue).toArray();

    }


    public static int findNthDigit(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);

        for(int i=0;i<=n;i++){

            int x =i;

            while(x>0){
                int m = x%10;
                list.add(m);

                x/=10;
                if(list.size()==n){
                    return list.get(n-1);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int n =11;
        System.out.println(findNthDigit(n));
    }



}
