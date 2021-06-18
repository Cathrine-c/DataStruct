package InterviewImportant.DP;

public class Test2 {

    //给定一个二维的 0-1 矩阵，求全由 1 构成的最大正方形面积。
    /**
     * 策略：
     * 对于在矩阵内搜索正方形或长方形的题型，一种常见的做法是定义一个二维 dp 数组，其中
     * dp[i][j] 表示满足题目条件的、以 (i, j) 为右下角的正方形或者长方形的属性。对于本题，则表示
     * 以 (i, j) 为右下角的全由 1 构成的最大正方形面积。如果当前位置是 0，那么 dp[i][j] 即为 0；如果
     * 当前位置是 1，我们假设 dp[i][j] = k 2 ，其充分条件为 dp[i-1][j-1]、dp[i][j-1] 和 dp[i-1][j] 的值必须
     * 都不小于 (k − 1) 2 ，否则 (i, j) 位置不可以构成一个边长为 k 的正方形。同理，如果这三个值中的
     * 的最小值为 k − 1，则 (i, j) 位置一定且最大可以构成一个边长为 k 的正方形。
     *
     */

    public int maximalSquare(char[][] matrix){
        if (matrix.length == 0 || matrix == null) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int max_side = 0;
        int[][] dp = new int[m][n];

        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (matrix[i-1][j-1]=='1'){
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;

                }
                max_side = Math.max(max_side,dp[i][j]);

            }
        }

        return max_side*max_side;

    }


    //给定一个正整数，求其最少可以由几个完全平方数相加构成。
    public static int nSum(int n){
        int[] dp = new int[n+1];
        dp[0]=0;

        for (int i=1;i<=n;i++){
            for (int j=1;j*j<=i;j++){
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);

            }
        }

        return dp[n];
    }

    public static void main2(String[] args) {
//        int n = 13;
//
//        System.out.println(nSum(n));

        System.out.println(numDecodings("5226"));

    }


    //已知字母 A-Z 可以表示成数字 1-26。给定一个数字串，求有多少种不同的字符串等价于这个
    public static int numDecodings(String s) {
        int[] dp = new int[s.length()];
        for (int i=0;i<s.length();i++){
            dp[i] = 1;
        }

        if (s.length() == 0) {
            return 0;
        }

        if (s.length() < 2) {
            return 1;
        }

        for (int i=1;i<s.length();i++){
            if (Integer.parseInt(s.substring(i-1, i+1)) <= 26) {
                dp[i] = dp[i-1]+1;

            }

        }
        return dp[s.length()-1];

    }


    //给定一个字符串和一个字符串集合，求是否存在一种分割方式，使得原字符串分割后的子字
    //符串都可以在集合内找到。
    public static boolean wordBreak(String s, String[] wordDict) {

        boolean[] dp = new boolean[s.length()+1];

        dp[0] = true;
        for (int i=1;i<=s.length();i++){
            for (String word:wordDict){
                int len = word.length();

                if (i >= len && (s.substring(i - len, i).equals(word))) {

                    dp[i] =dp[i]||dp[i-len];
                }
            }
        }

        return dp[s.length()];
    }



    public static void main(String[] args) {
        String s = "dogs";
        String[] wordlist = {"dog","s","gs"};
        System.out.println(wordBreak(s, wordlist));


    }

    /*
    子序列问题
     */


    //给定一个未排序的整数数组，求最长的递增子序列。


}
