package InterviewImportant.jianzhi;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;



//队列的最大值
class MaxQueue {
    Queue<Integer> queue ;
    Deque<Integer> maxQueue;



    //初始化
    public MaxQueue() {
        queue = new ArrayDeque();
        maxQueue = new ArrayDeque();
    }


    public int max_value() {
        if(maxQueue.isEmpty()){
            return -1;
        }
        return maxQueue.peek();

    }

    public void push_back(int value) {
        queue.add(value);
        while(!maxQueue.isEmpty()&&value>maxQueue.getLast())

            maxQueue.pollLast();


        maxQueue.add(value);


    }

    public int pop_front() {
        if(queue.isEmpty())
            return -1;

        int ans = queue.poll();

        if(ans==maxQueue.peek())
            maxQueue.poll();

        return ans;

    }
}


public class Day0729 {


    public static void main(String[] args) {

        System.out.println(nthUglyNumber(5));

    }


    /**
     *
     由于丑数只含有2、3、5，所以对于第n+1个丑数一定是来自前n个丑数中某一个乘以2或3或5得到的，
     由于需要保持从小到大,所以取3种乘积情况下的最小值。对于三个乘系数不同可以设置三个指针 (每个指针都从第一个丑数的位置开始)，
     若每次取得的最小值与其中一个或多个相等，这多个指针都会移动(因为会出现由于不同乘积而得到的重复值)。
     */
    public static int nthUglyNumber(int n) {
        int p2=0,p3=0,p5=0;

        int[] dp = new int[n];

        dp[0]=1;

        for (int i=1;i<n;i++){

            int num2 = 2*dp[p2];
            int num3 = 3*dp[p3];
            int num5 = 5*dp[p5];

            dp[i] = Math.min(num2,Math.min(num3,num5));

            if (dp[i]==num2)p2++;
            if (dp[i]==num3)p3++;
            if (dp[i]==num5)p5++;

        }
        return dp[n-1];

    }

}
