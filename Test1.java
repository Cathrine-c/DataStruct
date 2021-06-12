package InterviewImportant.Arithmiy.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.swap;

public class Test1 {

    public static void main(String[] args) {

    }

    //回溯法（backtracking）是优先搜索的一种特殊情况，又称为试探法，常用于需要记录节点状
    //态的深度优先搜索。通常来说，排列、组合、选择类问题使用回溯法比较方便。
    //给定一个无重复数字的整数数组，求其所有的排列方式。
    public List<List<Integer>> permuta(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        //借助回溯
        backTracing(nums, 0, list);
        return list;

    }

    private void backTracing(int[] nums, int index, List<List<Integer>> list) {

        if (index == nums.length - 1) {
            List<Integer> list1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
            list.add(list1);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            backTracing(nums, index + 1, list);
            swap(nums, i, index);
        }
    }


    public void swap(int[] nums,int l,int r){
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }



    //给定一个整数 n 和一个整数 k，求在 1 到 n 中选取 k 个数字的所有组合方法。
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int[] com = new int[k];
        //给com设置下标索引count
        int count=0;
        //最开始选出1个，然后一个一个加直到k
        backTracing1(res,com,count,1,n,k);
        return res;
    }

    private void backTracing1(List<List<Integer>> res, int[] com, int count,int pos, int n, int k) {
        if (count == k) {

            //数组和list的转换
            List<Integer> l1 = Arrays.stream(com).boxed().collect(Collectors.toList());
            res.add(l1);
            return;
        }

        for (int i=pos;i<=n;i++){
            com[count++] = i;
            backTracing1(res,com,count,i+1,n,k);

            count--;
        }

    }



    //给定一个字母矩阵，所有的字母都与上下左右四个方向上的字母相连。给定一个字符串，求
    //字符串能不能在字母矩阵中寻找到。
    public boolean exist(int[][] board, String word) {


        if (board.length == 0 || board == null) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;
        //设置访问数组
        boolean[][] visited = new boolean[m][n];
        boolean flag = false;

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){

                backTracing2(i,j,board,word,flag,visited,0);

            }
        }

        return flag;
    }

    private void backTracing2(int i, int j, int[][] board, String word, boolean flag, boolean[][] visited, int pos) {
        if (i<0||i>=board.length||j<0||j>=board[0].length){
            return;
        }

        if (visited[i][j]||flag||board[i][j]!=word.charAt(pos)){
            return;
        }

        if (pos == word.length() - 1) {
            flag=true;
            return;
        }

        visited[i][j] = true;
        backTracing2(i+1,j,board,word,flag,visited,pos);
        backTracing2(i,j+1,board,word,flag,visited,pos);
        backTracing2(i-1,j,board,word,flag,visited,pos);
        backTracing2(i,j-1,board,word,flag,visited,pos);

        visited[i][j] = false;

    }

}
