import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPriorityQueue {
    static class Mycom implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            //如果认为o1的优先级比o2高，先出o1，让compare返回<0的整数
            //如果认为o2的优先级比o1高，先出2，让compare返回>0的整数
            //如果认为o1和o2一样高，返回0
            //先随便写一种，测试一下，看结果对不对，如果不对再修改
            return o1 - o2;
        }
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Mycom());
        queue.offer(9);
        queue.offer(5);
        queue.offer(2);
        queue.offer(7);
        queue.offer(3);
        queue.offer(6);
        queue.offer(8);
        while (!queue.isEmpty()){
            Integer cur = queue.poll();
            System.out.println(cur);
            //标准库中的优先队列默认是一个小堆
        }
    }
}
