package 蓝桥;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

 }



public class Day20 {



    //删除链表倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode newHead = head;
        ListNode fast = head;
        ListNode slow = head;

        while (n>0) {
            fast = fast.next;
            n--;
            

        }


    }


    /**
     * 26*5 = 130
     * 71%130 = 71
     *
     */



    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = sc.nextInt();
        if (n == 0) {
            return;
        }

        int[] arr = new int[n];

        for (int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }


        String s = "";
        int count = 1;
        char cc = 'A';

        for (int i=0;i<26;i++){

           for (int j=0;j<c;j++){
               s +=cc;

               s+=count;
               count++;

           }

           count=1;
           cc +=1;

        }

        int sum = 26*c;

        for (int i=0;i<n;i++){

            int res = arr[i]%sum;
            int zz = res*2;

            System.out.println(s.substring(zz-2,zz));

        }

    }


    static int[][] map = new int[1000][1000];

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        char[][] chars = new char[n][n];
        String s ="";
        for (int i=0;i<n;i++){
            s = sc.next();

            for (int j=0;j<n;j++){
                chars[i][j] = s.charAt(j);
            }

        }


        for (int i=0;i<n;i++){

            for (int j=0;j<n;j++){
                if (chars[i][j]=='#'){
                    continue;
                }else {

                    wfs(chars,chars[i][j], i, j);
                }

            }
        }

        int sum =0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                sum+=map[i][j];

            }
        }

        System.out.println(sum);
    }


    //广度优先搜索
    private static void wfs(char[][] chars,char c, int m, int n) {
        map[m][n]=1;

        if (m == 0 && n == 0) {
            map[m][n]=1;
        } else if (m == 0) {

            if (chars[m][n-1]=='.'){
                map[m][n]+=1;
            }

        } else if (n == 0) {
            if (chars[m-1][n]=='.'){
                map[m][n]+=1;
            }

        }


        if (m > 0 && n > 0) {

            if (chars[m-1][n]=='.'&&chars[m][n-1]=='.'){
                map[m][n] +=2;
            }else if (chars[m-1][n]=='.'){
                map[m][n]+=1;

            }else if (chars[m][n-1]=='.'){
                map[m][n]+=1;

            }

        }


    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Date D;//
        int score1=0;//基本积分
        int score2=0;//额外积分
        String L = "";//会员级别
        double A;//消费金额
        boolean isFlag = true;
        //计算基本积分
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat.setLenient(false);
            String strDate=input.next();
            D=simpleDateFormat.parse(strDate);
            calendar.setTime(D);
            A=input.nextDouble();
            if(calendar.get(Calendar.DATE)==19)
            {
                score1=2*(int)A;

            }
            else
            {
                score1=(int)A;
            }

            L=input.next();
            if(L.equals("M"))
            {
                score2=0;
            }
            else if(L.equals("S"))
            {
                score2=score1;
            }
            else
            {
                isFlag=false;
            }
        } catch (ParseException e) {
            isFlag=false;
        }

        if(isFlag)
        {
            System.out.println(score1+score2);


        }
        else
        {
            System.out.println("N/A");
        }

    }




}
