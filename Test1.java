package InterviewImportant.DP;

public class Test1 {
    public static void main(String[] args) {

    }


    /**
     * 假如你是一个劫匪，并且决定抢劫一条街上的房子，每个房子内的钱财数量各不相同。如果
     * 你抢了两栋相邻的房子，则会触发警报机关。求在不触发机关的情况下最多可以抢劫多少钱。
     */
    public int rob(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[1] = nums[0];

        for (int i=2;i<=n;i++){
            dp[i] = Math.max(dp[i-1],nums[i-1]+dp[i-2]);
        }
        return dp[n];
    }


    //给定一个 m × n 大小的非负整数矩阵，求从左上角开始到右下角结束的、经过的数字的和最
    //小的路径。每次只能向右或者向下移动。
    public int minPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];

                }else if (i==0){
                    dp[i][j] = dp[i][j-1]+grid[i][j];

                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j]+grid[i][j];

                }else {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];

                }
            }
        }
        return dp[m-1][n-1];
    }



}
