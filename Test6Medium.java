package InterviewImportant.DP;

import java.util.ArrayList;
import java.util.List;

public class Test6Medium {


    public static void main(String[] args) {
        String s = "aab";

        System.out.println(partition(s));

    }


    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] dp = new boolean[len][len];
        for (int i=0;i<len;i++){

            for (int j=0;j<=i;j++){

                if (chars[i]==chars[j]){
                    if (i - j < 3) {
                        dp[i][j] = true;

                    }else {
                        dp[i][j] = dp[i-1][j+1];

                    }
                }
            }
        }
        dfs1(s,dp,0,res,new ArrayList<>());

        return res;
    }


    private static void dfs1(String s, boolean[][] dp, int start, List<List<String>> res, ArrayList<String> chain) {
        //如果字符串为空，直接返回空
        if (start == s.length()) {
            res.add(new ArrayList<>(chain));

            return;
        }

        for (int i=start;i<s.length();i++) {

            if (dp[i][start]) {
                chain.add(s.substring(start, i + 1));

                dfs1(s, dp, i + 1, res, chain);
                chain.remove(chain.size() - 1);
            }
        }

    }


    /**
     *
     给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

     s = s1 + s2 + ... + sn
     t = t1 + t2 + ... + tm
     |n - m| <= 1
     交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        if (len1 + len2 != len3) {
            return false;

        }

        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;

        for (int i=1;i<=len1&&(dp[i-1][0]&&s1.charAt(i-1)==s3.charAt(i-1));i++)dp[i][0]= true;
        for (int i=1;i<=len2&&(dp[0][i-1]&&s2.charAt(i-1)==s3.charAt(i-1));i++)dp[0][i] = true;

        for (int i=1;i<=s1.length();i++){
            for (int j=1;j<=s2.length();j++){

                dp[i][j] = (dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1))
                        ||dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1);

            }
        }

        return dp[len1][len2];


    }


    //给你一个整数 n ，请你找出并返回第 n 个 丑数 。
    //丑数 就是只包含质因数 2、3 和/或 5 的正整数。
    public int nthUglyNumber(int n) {
        

    }

}
