package InterviewImportant.DP;

public class Test7Medium {


    //给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    //丑数 就是只包含质因数 2、3 和/或 5 的正整数。
    public int nthUglyNumber(int n) {

        int[] dp = new int[n];
        int n2 = 0, n3 = 0, n5 = 0;
        dp[0] = 1;

        for (int i=1;i<n;i++){

            dp[i] = Math.min(2*dp[n2],Math.min(3*dp[n3],5*dp[n5]));

            if (dp[i]==2*dp[n2])n2++;
            if (dp[i]==3*dp[n3])n3++;
            if (dp[i]==5*dp[n5])n5++;

        }

        return dp[n-1];
    }


    //编写一段程序来查找第 n 个超级丑数。
    //超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。

    
}
