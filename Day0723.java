package InterviewImportant.jianzhi;

import java.lang.reflect.Array;
import java.util.*;

public class Day0723 {


    //层序遍历，第一从层从左至右，第二层从右至左，从上到下打印
    public List<List<Integer>> levelOrder(TreeNode root){

        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int count = 1;

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();

            count++;
            while (size-- > 0) {
                TreeNode node = queue.poll();

                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }


            }
            if (count % 2 == 0) {
                res.add(list);

            }else {
                Collections.reverse(list);
                res.add(list);
            }
        }

        return res;
    }


    public int[] singleNumbers(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){

            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);

            }else{

                map.put(nums[i],map.get(nums[i])+1);
            }
        }

        int[] arr = new int[2];
        int x = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()==1){
                arr[x]  = entry.getKey();
                x++;
            }
        }
        return arr;
    }


    //方法2
    public static int[] singleNumbers1(int[] nums) {

        int sum = 0;

        for (int num:nums) {

            sum ^= num;
        }

            int flag = (-sum)&sum;
            int[] arr= new int[2];
            for (int num:nums){

                if ((flag & num)==0) {

                    arr[0] ^=num;

                }else {

                    arr[1] ^=num;
                }

            }
            return arr;

    }



    public static int num(int[] a){
        int s = 0;

        for (int n:a){
            s^=n;
        }
        return s;
    }


    public static void main(String[] args) {
        int[] ar = {4,4,2,1,5,5,2,6};
        System.out.println(num(ar));

        Queue<Integer> queue = new LinkedList<>();
       

    }



}
