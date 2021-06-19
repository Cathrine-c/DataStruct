package InterviewImportant.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {


    public static void main1(String[] args) {

        int[] nums={1,3,5,4,2,3,4,5};
        System.out.println(lengthOfLIS(nums));
    }


     /*
    子序列问题
     */


    //给定一个未排序的整数数组，求最长的连续递增子序列。
    public static int lengthOfLIS(int[] nums) {
        int max_len = 0;
        int len = 1;

        for (int i=0;i<nums.length-1;i++){
            if (nums[i]<nums[i+1]){
                len++;
                max_len = max_len>len?max_len:len;

            }else {
               len=1;
            }
        }

        return max_len;

    }


    //给定一个未排序的整数数组，求最长的递增子序列。
    public static int lengthOfLIS1(int[] nums) {
        int max_len =0;
        int[] dp = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            dp[i] = 1;
        }
        for (int i=0;i<nums.length;i++){
            for (int j=0;j<i;j++){

                if (nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);

                }
            }
            max_len = Math.max(max_len,dp[i]);
        }

        return max_len;

    }


    //最长递增子序列的个数
    public static int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;

        }

        int[] dp = new int[nums.length];
        int[] counter = new int[nums.length];

        Arrays.fill(dp,1);
        Arrays.fill(counter,1);

        int max = 1,res =0;

        for (int i=1;i<dp.length;i++){
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]){
                    if (dp[j]+1>dp[i]){
                        dp[i] = dp[j]+1;
                        counter[i] = counter[j];

                    }else if (dp[j]+1==dp[i]){
                        counter[i] +=counter[j];

                    }
                }
            }
            max = Math.max(max,dp[i]);
        }

        for (int i=0;i<nums.length;i++){

            if (dp[i]==max)res +=counter[i];

        }

        return res;
    }


    public static void main(String[] args) {
        int[] numms = {1,3,5,4,7};
        System.out.println(findNumberOfLIS(numms));

    }





}
