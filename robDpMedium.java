package InterviewImportant.DP;

import java.util.Arrays;

public class robDpMedium {


    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    // 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
    // 一夜之内能够偷窃到的最高金额。
    public static int rob1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        //  int rob_Max = 0;
        if (nums.length == 0||nums.length==1) {
            return dp[0];
        }
        dp[1] = Math.max(nums[1],nums[0]);

        if (nums.length==2){
            return Math.max(nums[0],nums[1]);

        }

        for (int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);

            //  rob_Max = rob_Max>dp[i] ?rob_Max:dp[i];

        }

        return dp[nums.length-1];

    }


    public static void main3(String[] args) {
        int[] nums = {1,7,9,2};
       // System.out.println(rob1(nums));

        System.out.println(rob2(nums));
    }


    //你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
    // 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
    // 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
    public static int rob2(int[] nums) {

        int[] dp = new int[nums.length];
        int[] dp1 = new int[nums.length];

        dp[0] = nums[0];
        if (nums.length == 0||nums.length==1) {
            return dp[0];
        }


        dp[1] = Math.max(nums[0],nums[1]);

        dp1[1] = nums[1];
        if (nums.length==2){
            return Math.max(nums[0],nums[1]);
        }

        dp1[2] = Math.max(nums[1],nums[2]);

        int rob_Max = Math.max(dp[1],dp1[2]);




        for (int i=2;i<nums.length-1;i++){

            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);

        }

        for (int i=3;i<nums.length;i++){

            dp1[i] = Math.max(dp1[i-2]+nums[i],dp1[i-1]);

        }

        rob_Max = dp[nums.length-2]>dp1[nums.length-1]?dp[nums.length-2]:dp1[nums.length-1];

        return rob_Max;

    }




    public int rob(TreeNode root) {
        if (root == null) {

            return 0;
        }

        //思路：定义一个数组res，长度为2，res[0]表示不抢该节点可获得最大值，res[1]表示抢该节点可获得最大值，
        //方法helper(r)意为：在r为根结点的树中，返回抢根结点与不抢根结点可获得的最大值
        int[] res = helper(root);
        return Math.max(res[0],res[1]);

    }

    private int[] helper(TreeNode r) {

        if (r==null)return new int[2];
        int[] left = helper(r.left);
        int[] right = helper(r.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        res[1] = r.val+left[0]+right[0];
        return res;

    }


    //给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
    // 你需要让组成和的完全平方数的个数最少。给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
    //完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
    // 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i=1;i<=n;i++){
            for (int j=1;j*j<=i;j++){

                dp[i] = Math.min(dp[i-j*j]+1,dp[i]);

            }

        }
        return dp[n];

    }


    public static void main(String[] args) {

        int n = 12;

        System.out.println(numSquares(n));

    }



}
