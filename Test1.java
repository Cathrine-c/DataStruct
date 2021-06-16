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
}
