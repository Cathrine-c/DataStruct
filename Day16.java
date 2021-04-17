package 蓝桥;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day16 {


    public static void main1(String[] args) {
        int count =0;
        for (int i=1;i<=2017;i+=2){
            int x = i;

            if (isPrime(x)&&isPrime(2019-x)){
                count++;

            }

        }

        System.out.println(count);
    }

    private static boolean isPrime(int x) {

        if (x <= 3) {
            return x>1;

        }

        int sqrt = (int) Math.sqrt(x);

        for (int i=3;i<=sqrt;i+=2){

            if (x % 2 == 0 || x % i == 0) {
                return false;
            }

        }
        return true;

    }




    public static void main2(String[] args) {
        boolean [] bs=new boolean[2020];
        Arrays.fill(bs, true);
        for(int i=2;i*i<2020;i++){
            if(!bs[i]) continue;
            for (int j = i*i; j < 2020; j+=i)
                bs[j]=false;
        }
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(0);

        for(int i=2;i<2020;i++) if(bs[i]) a.add(i);

        long[][]dp=new long[a.size()][2020];

        for(int i=0;i<a.size();i++){
            dp[i][a.get(i)]=1;

        }

        for(int i=1;i<a.size();i++){
            for (int j = 0; j < 2020; j++) {
                if(j<a.get(i)) dp[i][j]=dp[i-1][j];

                else dp[i][j]=dp[i-1][j]+dp[i-1][j-a.get(i)];

            }
        }

        System.out.println(dp[a.size()-1][2019]);

    }


    /**
     * 将 2019 拆分为若干个两两不同的完全平方数之和，一共有多少种不同的方法？
     注意交换顺序视为同一种方法，例如 132 + 252 + 352 = 2019 与 132 + 352 +252 = 2019 视为同一种方法。
     */


    static int count =0;
    public static void main3(String[] args) {
        dfs(0, 45, 2019);
        System.out.println(count);

    }

    private static void dfs(int begin, int end, int num) {


        if (num < 0) {
            return ;
        }

        if (num == 0) {
            count++;
            return;
        }

        for (int i=begin;i<end;i++){

            dfs(i+1,end,num-i*i);
        }


    }


    /**
     * 平面切割
     *在 4 × 4 的方格矩阵中画一条直线。则直线穿过的方格集合有多少种不同的可能？
     * 这个里直线穿过一个方格当且仅当直线将该方格分割成面积都大于 0 的两
     * 部分。
     */

    public static void main4(String[] args) {


    }


    /**序列求和
     * 学习了约数后，小明对于约数很好奇，他发现，给定一个正整数 t，总是可以找到含有 t 个约数的整数。小明对于含有 t 个约数的最小数非常感兴趣，并把它定义为 S t。
     * 例如 S 1 = 1, S 2 = 2, S 3 = 4, S 4 = 6，· · · 。
     * 现在小明想知道，前 60 个 S i 的和是多少？即 S 1 + S 2 + · · · + S 60 是多少？
     */

    public static void main5(String[] args) {

        int count=1;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int n = 1;

        for (int i=2;i<100000;i++){
            int x = i;

            if (x-1==list.get(list.size()-1)) {
                n++;

                count++;
                if (count == 60) {
                    break;
                }

            }

            dfs1(n,x,list);

        }

        long sum = 0;

        for (Integer e:list){
            sum+=e;

        }

        System.out.println(sum);

    }


    private static void dfs1(int n,int x, List<Integer> list) {
        int s = 1;
        for (int j=1;j<=x/2;j++){

            if (x % j == 0) {
                s++;
                if (s==n){
                    list.add(x);
                    break;
                }

            }

        }

    }


    /**最长子序列
     * 我们称一个字符串 S 包含字符串 T 是指 T 是 S 的一个子序列，即可以从字符串 S 中抽出若干个字符，它们按原来的顺序组合成一个新的字符串与 T 完全一样。
     * 给定两个字符串 S 和 T，请问 T 中从第一个字符开始最长连续多少个字符被 S 包含？
     */


    public static void main6(String[] args) {

        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        String T = sc.next();

        int i=0;
        int j=0;
        while (j<S.length()&&i<T.length()){
            if (T.charAt(i)==S.charAt(j)){
                i++;
                j++;
            }else {
                j++;
            }

        }

        System.out.println(i);

    }


    /**数正方形
     *在一个 N × N 的点阵上，取其中 4 个点恰好组成一个正方形的 4 个顶点，一共有多少种不同的取法？
     *
     * 由于结果可能非常大，你只需要输出模 109 + 7 的余数。
     */
    public static void main7(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(countMatrix(n));
    }


    private static int countMatrix(int n) {

        if (n == 1) {
            return 1;
        }

        return n*n+countMatrix(n-1);

    }


    static final int mod = 1000000007;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long res = ((n - 1)* (n - 1)) % mod;
        for (int i = 2; i < n; i++)
            res = (res + (((n - i) * (n - i)) % mod) * i) % mod;

        System.out.println(res);

    }



}

