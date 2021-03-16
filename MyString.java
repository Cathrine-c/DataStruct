package InterviewImportant.DP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyString {


    public static void main1(String[] args) {
        int[] ar = {1,2,4,-3,2,3,7};

        System.out.println(FindGreatestSumOfSubArray(ar));


    }

    //字符串的所有排列
    public static ArrayList<String> Permutation(String str) {

        ArrayList<String> result = new ArrayList<>();

        if (str.length()==0||str==null) {
            return result;
        }

        resolve(str,"",result);
        return result;

    }



    private static void resolve(String str, String s, ArrayList<String> result) {

        if (str.length() == 0) {
            if (!result.contains(s)){
                result.add(s);
            }
        }

        for (int i=0;i<str.length();i++){
            resolve(str.substring(0,i)+str.substring(i+1,str.length()),s+str.charAt(i),result);

        }

    }


    //连续子数组的最大和
    public static int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;

        }

        int maxSum = array[0];

        for (int i=1;i<array.length;i++){
            array[i] += array[i-1]>0?array[i-1]:0;

            maxSum = maxSum>array[i]?maxSum:array[i];

        }
        return maxSum;
    }


   //最长公共子串，空字符串不是任何字符串的子串
   public static String sharelongestString(String s1,String s2){
        //如果两个字符串有一个为空，则它们没有公共子串
       if (s1.length() == 0 || s2.length() == 0) {
           return "-1";
       }
       //标记最大长度
       int maxL = 0;
       //记录每一次下标
       int Lindex = 0;

       for (int i=0;i<s1.length();i++){
           for (int j=0;j<s2.length();j++){
               if (s1.charAt(i)==s2.charAt(j)){
                   //如果找到了相等的字符串，则对它进行标记，
                   int m = i;
                   int n = j;
                   int l=0;
                   //从相等那个下标往后比较，直到后面的字符不相等
                   while (m<s1.length()&&n<s2.length()&&s1.charAt(m)==s2.charAt(n)){
                       m++;
                       n++;
                       //每次的长度记录下来
                       l++;
                   }

                   maxL = maxL>l?maxL:l;
                   Lindex = m;

               }
           }
       }
       if (maxL == 0) {
           return "-1";
       }

       return s1.substring(Lindex-maxL,Lindex);
   }


   //动态规划解法
    public static String sharelongestString1(String s1,String s2){

        if (s1.length() == 0 || s2.length() == 0) {
            return "-1";
        }

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for (int i=0;i<s1.length()+1;i++){

            dp[i][0] = 0;
            dp[0][i] = 0;
        }

        int Lindex = 0;
        int maxL= 0;
        for (int i=0;i<s1.length();i++){
            for (int j=0;j<s2.length();j++){

                if (s1.charAt(i)!=s2.charAt(j)){
                    dp[i+1][j+1] = 0;
                }else {
                    dp[i+1][j+1] = dp[i][j]+1;

                }
                maxL = maxL>dp[i+1][j+1]?maxL:dp[i+1][j+1];
                Lindex = i;

            }
        }
        if (maxL == 0) {
            return "-1";
        }
        return s1.substring(Lindex-maxL+1,Lindex+1);
    }


    public static void main(String[] args) {
        String s ="wseddesf";

        System.out.println(palindromeString(s));
    }


    //最长回文字符串
    public static String palindromeString(String s){

        if (s == null || s.length() == 0) {
            return null;
        }

        String longest = "";

        for (int i=0;i<s.length();i++){
            //如果字符串长度是奇数，则由中心点向两边扩散
            String pal = palindromeStringHelper(s,i,i);

            if (pal.length() > longest.length()) {
                longest = pal;
            }
            //如果字符串长度为偶数，则由两个中心点向两边扩散
            pal = palindromeStringHelper(s,i,i+1);
            if (pal.length() > longest.length()) {
                longest = pal;
            }
        }
        return longest;
    }



    private static String palindromeStringHelper(String s, int start, int end) {

        while (start>=0&&end<s.length()&&s.charAt(start)==s.charAt(end)){
           start--;
           end++;
        }

        return s.substring(start+1,end);
    }


    //动态规划解法
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }

        int maxLen = 1;
        int begin  = 0;

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();

        for (int j = 1;j < len;j++){
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]){
                    dp[i][j] = false;
                }else {

                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }


                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin,begin + maxLen);
    }



}




