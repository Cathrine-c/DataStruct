package InterviewImportant.Arithmiy.Grandy;

import java.util.Arrays;
import java.util.Comparator;



public class Main1 {


    //思想：采用贪心的策略，保证每次操作都是局部最优的，从而使最
    //后得到的结果是全局最优的
    /**
     *  分配问题
     */

    /*
    有一群孩子和一堆饼干，每个孩子有一个饥饿度，每个饼干都有一个大小。每个孩子只能吃
    最多一个饼干，且只有饼干的大小大于孩子的饥饿度时，这个孩子才能吃饱。求解最多有多少孩
    子可以吃饱。*/

    //策略：给剩余孩子里最小饥饿度的孩子分配最小的能饱腹的饼干。
    public int findContentChildren(int[] children,int[] cookies){
        //两个数组，一个是孩子的饥饿度，一个是饼干的大小
        Arrays.sort(children);
        Arrays.sort(cookies);
        int child = 0;
        int cookie = 0;
        while (child < children.length && cookie < cookies.length) {
            if (children[child]<=cookies[cookie]){
                child++;
                cookie++;
            }
        }
        return child;


    }



    /*一群孩子站成一排，每一个孩子有自己的评分。现在需要给这些孩子发糖果，规则是如果一
    个孩子的评分比自己身旁的一个孩子要高，那么这个孩子就必须得到比身旁孩子更多的糖果；所
    有孩子至少要有一个糖果。求解最少需要多少个糖果*/
    //策略:把所有孩子的糖果数初始化为 1；
    //先从左往右遍历一遍，如果右边孩子的评分比左边的高，则右边孩子的糖果数更新为左边孩子的糖果数加 1；
    // 再从右往左遍历一遍，如果左边孩子的评分比右边的高，且左边孩子当前的糖果数不大于右边孩子的糖果数，则左边孩子的糖果数更新为右边孩子的糖果数加 1。

    public static int candy(int[] score){
        int[] candy= new int[score.length];

        for (int i=0;i<candy.length;i++){
            candy[i] = 1;

        }


        for (int i=1;i<score.length;i++){
            if (score[i]>score[i-1]){
                candy[i] = candy[i-1]+1;

            }
        }

        for (int i=score.length-1;i>=1;i--){
            if (score[i]<score[i-1]){
                candy[i-1] = candy[i]+1;
            }
        }
        int sum=0;
        for (int i=0;i<candy.length;i++){
            sum+=candy[i];

        }

        return sum;
    }


    public static void main1(String[] args) {
        int[] a = {1,0,2};
        System.out.println(candy(a));

    }


    //给一个target值，在一个有序数组里找到两个数相加等于target，并返回两个数所在的位置
    public int[] twoSum(int[] arr,int target){

        int begin=0;
        int end = arr.length-1;

        while (begin < end) {
            int sum = arr[begin]+arr[end];
            if (target==sum)break;
            if (target > sum) {
                end--;
            }else {
                begin++;
            }
        }

        return new int[]{begin+1,end+1};
    }



    //一个原本增序的数组被首尾相连后按某个位置断开（如 [1,2,2,3,4,5] → [2,3,4,5,1,2]，在第一
    //位和第二位断开），我们称其为旋转数组。给定一个值，判断这个值是否存在于这个为旋转数组中。
    /*策略：即使数组被旋转过，我们仍然可以利用这个数组的递增性，使用二分查找。对于当前的中点，
    如果它指向的值小于等于右端，那么说明右区间是排好序的；反之，那么说明左区间是排好序的。
    如果目标值位于排好序的区间内，我们可以对这个区间继续二分查找；反之，我们对于另一半区
    间继续二分查找。*/
    public boolean search(int[] nums, int target) {

        int start =0;
        int end =nums.length-1;

        while (start <= end) {

            int mid = (start+end)/2;
            if (target==nums[mid]){
                return true;
            }

            if (nums[mid]==nums[start]){
                start++;

            }else if (nums[mid]<nums[end]){
                if (target<=nums[end]&&target>nums[mid]){
                    start = mid+1;
                }else {
                    end = mid-1;
                }
            }else {

                if (target>=nums[start]&&target<nums[mid]){
                    end = mid-1;
                }else {
                    start = mid+1;
                }
            }


        }
        return false;

    }



}

