package java_0319;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //某人有A、B两个袋子。里面分别装了一些不同种类的球，如果B中包含的球的种类A中都有，并且同样种类的球的个数A>=B，则输出Yes，否则输出No
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String A = sc.next();
            String B = sc.next();

            char[] A0 = A.toCharArray();
            char[] B0 = B.toCharArray();

            Map<Character,Integer> map1 = new HashMap<>();

            for (int i=0;i<B.length();i++){
                Integer x = map1.get(B0[i]);

                if (!map1.containsKey(B0[i])){
                    map1.put(B0[i],1);
                }else {

                    map1.put(B0[i],x+1);
                }
            }


            for (int j=0;j<A0.length;j++){
                if (map1.containsKey(A0[j])){
                   map1.put(A0[j],map1.get(A0[j])-1);

                }
            }

            for (Map.Entry<Character,Integer> entry:map1.entrySet()){
                if (entry.getValue() > 0) {
                    System.out.println("No");
                    return;
                }
            }

            System.out.println("Yes");

        }
    }


    public static void main2(String[] args) {

        Map<Character,Integer> map = new HashMap<>();
        map.put('c',1);
        map.put('m',2);
        map.put('e',3);
        System.out.println(map);

        if (map.containsKey('c')){
            map.put('c',map.get('c')-2);
        }

        System.out.println(map);
    }
}
