package InterviewImportant.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Test {


    //方格游戏
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int a = 0; a < t; a++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[][] arr = new int[n][m];
            long[][] dp = new long[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = scan.nextInt();
                }
            }
            dp[n - 1][m - 1] = 1;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    for (int k = 1; k <= arr[i][j]; k++) {
                        for (int l = 0; l <= k; l++) {
                            int d = k - l;
                            if (i + l < n && j + d < m)
                                dp[i][j] = (dp[i][j] + dp[i + l][j + d] % 10000) % 10000;
                        }
                    }
                }
            }
            System.out.println(dp[0][0] % 10000);
        }

    }



    //有个牛牛一起去朋友家吃糖果，第个牛牛一定要吃块糖果.
    //而朋友家一共只有块糖果，可能不会满足所有的牛牛都吃上糖果。
    //同时牛牛们有个约定，每一个约定为一个牛牛的编号对，表示第个和第个牛牛是好朋友，他俩要么一起都吃到糖果，要么一起都不吃。
    //保证每个牛牛最多只出现在一个编号对中。
    //您可以安排让一些牛牛吃糖果，一些牛牛不吃。
    //要求使能吃上糖果的牛牛数量最多（吃掉的糖果总量要小于等于），并要满足不违反牛牛们的个约定。
    public static void main1(String[]args){
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n+1];
        int[] count = new int[n+1];
        Arrays.fill(count,1);
        for (int i = 1; i <=n; i++) {
            arr[i]=sc.nextInt();
        }
        int k = sc.nextInt();
        for (int i = 1; i <=k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[x]+=arr[y];
            count[x]++;
            count[y]--;
        }
        int[] dp = new int[m+1];
        for(int i=1;i<=n;i++){
            if(count[i]==0) continue;
            for(int j=m;j>=arr[i];j--){
                dp[j]=Math.max(dp[j],dp[j-arr[i]]+count[i]);
            }
        }
        System.out.println(dp[m]);
    }


    //完美对的个数
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] input = new int[n][k];
        int res = 0;
        HashMap<String, Integer> map = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i ++) {
            String s = "";
            String[] temp = scanner.nextLine().split(" ");
            for (int j = 0; j < k; j ++) {
                input[i][j] = Integer.parseInt(temp[j]);
                if (j != 0) {
                    if (input[i][j - 1] - input[i][j] > 0) {
                        s += "+";
                    }
                    s += input[i][j - 1] - input[i][j];
                }
            }
            String temp_s = s.replace("+", "*").replace("-", "+").replace("*", "-");
            if (map.containsKey(temp_s)) {
                res += map.get(temp_s);
            }
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        System.out.println(res);
    }


    /**
     * 小强今天体检，其中有一个环节是测视力
     * 小强看到的视力表是一张的表格，但是由于小强视力太差，他无法看清表格中的符号。
     * 不过热爱数学的他给自己出了这样一个问题：假设现在有a个向上的符号，b个向下的符号，c个向左的符号，d个向右的符号，
     * 把这些符号填到视力表中，总共有多少种可能的情况呢？
     */
    static int mod=998244353;
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int all=n*n;
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i]=sc.nextInt();
        }
        long[] fac = new long[all+1];
        fac[1]=1;
        fac[0]=1;
        for(int i=2;i<=all;i++){
            fac[i]=fac[i-1]*i%mod;
        }
        long res=1;
        int remain=all;
        for (int i = 0; i < 4; i++) {
            if(arr[i]==0) continue;
            long cur=fac[remain]*niyuan(fac[arr[i]],mod)%mod*niyuan(fac[remain-arr[i]],mod)%mod;
            remain-=arr[i];
            res=res*cur%mod;
        }
        System.out.println(res);
    }

    public static long niyuan(long x,int mod){
        return power(x,mod-2,mod);
    }

    public static long power(long  x,long y,int mod){
        if(y==1){
            return x;
        }
        long tmp=power(x,y/2,mod);
        long res=tmp*tmp%mod;
        if(y%2==1){
            res=res*x%mod;
        }
        return res;
    }
}
