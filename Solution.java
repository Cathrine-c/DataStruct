package java_0317;

import java.util.Scanner;

public class Solution {

    public static int calcSimilarity(String name1, String name2) {
        if(name1.length()==0&&name2.length()==0){
            return 0;
        }

        String[] str1 = name1.split(" ");
        String[] str2 = name2.split(" ");

        int i=0;
        int j=0;
        String s = "";
        while(i<str1.length&&j<str2.length){
            String ss = shareLongestString1(str1[i],str2[j]);
            if (ss!=null){
                s+=ss;
            }
            i++;
            j++;
        }

        int k =0;
        int l= 0;

        for(int m =0;m<name1.length();m++){
            if(name1.charAt(m)==' '){
                continue;

            }else{
                k+=name1.charAt(m);
            }
        }

        for(int n =0;n<name2.length();n++){
            if(name2.charAt(n)==' '){
                continue;

            }else{
                l+=name2.charAt(n);
            }
        }

        int c = 0;
        for(int t =0;t<s.length();t++){
            if(s.charAt(t)==' '){
                continue;

            }else{
                c+=s.charAt(t);
            }
        }

        return k+l-2*c;


    }

    public static String shareLongestString1(String s1,String s2){

        int Lindex1 = 0;
        int LMax = 0;
        for(int i=0;i<s1.length();i++){
            for(int j=0;j<s2.length();j++){
                int m = i;
                int n = j;
                int l=0;
                while(m<s1.length()&&n<s2.length()&&s1.charAt(m)==s2.charAt(n)&&m==n){
                    m++;
                    n++;
                    l++;

                }

                LMax = LMax>l?LMax:l;
                Lindex1 = m;
            }
        }
        if(LMax==0){
            return null;
        }


        return s1.substring(Lindex1-LMax,Lindex1);
    }



    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name1 = in.nextLine();
            String name2 = in.nextLine();

            int sum = calcSimilarity(name1, name2);
            System.out.println(sum);
        }
    }



}
