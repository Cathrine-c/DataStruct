package InterviewImportant.DP;

import java.util.Arrays;

public class Test4Easy {


    //最大子序和
    //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] maxSum = new int[nums.length+1];

        int max = nums[0];

        for (int i=0;i<nums.length;i++){

            maxSum[i+1] = Math.max(nums[i],maxSum[i]+nums[i]);
            max = max>maxSum[i+1]?max:maxSum[i+1];

        }
        return max;

    }

    public static void main1(String[] args) {
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        System.out.println(maxSubArray(nums));
        int n= 3;
        System.out.println(climbStairs(n));
    }


    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;

        for (int i=2;i<n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n-1];

    }

    //给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    //你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
    // 设计一个算法来计算你所能获取的最大利润。返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润,返回 0 。
    public static int maxProfit(int[] prices) {
        if (prices.length == 1 || prices == null) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = Integer.MIN_VALUE;

        int min = prices[0];

        for (int i=1;i<prices.length;i++){
            min = min<prices[i]?min:prices[i];

            dp[i] = Math.max(dp[i-1],prices[i]-min);


        }
        return dp[prices.length-1];

    }


    //给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    //
    //字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0 || s == null) {
            return true;
        }
        int index =0;
        for (int i=0;i<t.length();i++){
            if (s.charAt(index)==t.charAt(i)){
                index ++;

                if (index == s.length()) {
                    return true;
                }

            }else {
                continue;
            }

        }
        return false;
    }


    //动态规划法
    public static boolean isSubsequence1(String s, String t) {
        int m = s.length();
        int n = t.length();

        boolean[][] dp = new boolean[m+1][n+1];

        Arrays.fill(dp[0],true);
        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                dp[i][j] = dp[i][j-1];
                dp[i][j] |= dp[i-1][j-1]&&s.charAt(i-1)==t.charAt(j-1);

            }
        }

        return dp[m][n];

    }


    //数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
    //每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，
    // 你就可以选择向上爬一个阶梯或者爬两个阶梯。
    //请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i=2;i<cost.length;i++){
            dp[i] = Math.min(dp[i-1],dp[i-2])+cost[i];

        }

        return Math.min(dp[cost.length-1],dp[cost.length-2]);

    }



    //输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    public int maxSubArray1(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        int max = nums[0];

        for (int i=1;i<nums.length;i++){
            dp[i] = Math.max(nums[i]+dp[i-1],nums[i]);
            max = max>dp[i]?max:dp[i];
        }

        return max;
    }


    //一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，
    // 因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1],nums[0]);

        for (int i=2;i<nums.length;i++){

            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);

        }
        return dp[nums.length-1];

    }

    public static void main(String[] args) {
//        int[] arr = {7,1,5,3,6,4};
//        System.out.println(maxProfit(arr));
//        String s ="abc";
//        String t = "ahbgdc";
//        System.out.println(isSubsequence1(s, t));
        int[] cost = {10,15,20};

        System.out.println(minCostClimbingStairs(cost));


    }
}
