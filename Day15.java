package 蓝桥;

import java.math.BigInteger;
import java.util.Scanner;

public class Day15 {



    static int[] dx = { 1, 1, 1, -1, -1, -1, 0, 0 };
    static int[] dy = { -1, 1, 0, -1, 0, 1, -1, 1 };

    public static void main2(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[][] chars = new char[35][55];

        for (int i=1;i<=30;i++){
            String str = sc.next();

            for (int j=0;j<50;j++){

                chars[i][j+1] = str.charAt(j);

            }
        }


        int count =0;

        for (int i=1;i<=30;i++){
            for (int j=1;j<=50;j++){

                char tem = chars[i][j];
                for (int t=0;t<8;t++){

                    for (int x=i+dx[t], y=j+dy[t];x>=1&&y>=1&&x<=30&&y<=50;x+=dx[t],y+=dy[t]){
                        if (chars[x][y]>tem &&(x>i||y>j)){
                            count++;

                        }

                    }
                }
            }
        }

        System.out.println(count);

    }



    //X 是 2019 的整倍数；
    //X 的每一位数字都是奇数。

    public static void main1(String[] args) {

        jiShu();

    }

    static int count=2;
    public static void jiShu(){

        int n =0;

        String s = "";

        s = String.valueOf(count*2019);

        for (int i=0;i<s.length();i++){

            if (s.charAt(i) % 2 == 0) {
                count++;
                jiShu();
                break;
            }else {
               n++;

            }
        }

        if (n == s.length()) {
            System.out.println(s);
        }
    }


    public static void main(String[] args) {

        int n=7;
        int[][] memo=new int[n+1][n+1];

        System.out.println(dfs(0, 0, n, memo));

    }


    static int counts =0;
    private static int dfs(int x, int y, int n, int[][] memo) {

        if (x + y == n) {
            counts++;
            return 0;
        }

        if (x + 1 <= n && memo[x + 1][y] != 1) {
            memo[x][y] = 1;

            dfs(x+1,y,n,memo);
            memo[x][y] = 0;
        }


        if (y+1<=n&&memo[x][y+1]!=1){

            memo[x][y] = 1;
            dfs(x,y+1,n,memo);
            memo[x][y] =0;
        }


        if (x - 1 > 0 && memo[x - 1][y] != 1) {
            memo[x][y] = 1;
            dfs(x-1,y,n,memo);
            memo[x][y] =0;

        }


        if (y - 1 > 0 && memo[x][y - 1] != 1) {
            memo[x][y] = 1;
            dfs(x,y-1,n,memo);
            memo[x][y] =0;
        }

        return counts;

    }
}
