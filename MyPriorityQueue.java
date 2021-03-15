//优先队列
public class MyPriorityQueue {
    private int[] array = new int[100];
    private int size = 0;

    //入队列依赖于向上调整
    public void offer(int x) {
        //1.把x放到数组元素末尾
        array[size] = x;
        size++;
        //2.把x进行向上调整
        //第一个参数表示用来承载堆的数组
        //第二个参数表示数组中有效元素的个数
        //第三个参数表示从哪个位置进行向上调整
        shiftUp(array,size,size-1);
    }


    private void shiftUp(int[] array,int size,int index) {
        int child = index;
        int parent = (child-1)/2;
        //如果child为0，说明child已经是根结点，根结点就没有父节点
        //调整到这里就已经到顶
        while (child > 0) {
            if(array[parent]<array[child]){
                int tmp = array[parent];
                array[parent] = array[child];
                array[child] = tmp;
            }else{
                break;
            }
            child = parent;
            parent = (child-1)/2;
        }
    }

    //出队列依赖于向下调整
    public Integer poll() {
        if(size<=0){
            return null;
        }
        int ret = array[0];
        array[0] = array[size-1];
        size--;
        shiftDown(array,size,0);
        return ret;
    }


    private void shiftDown(int[] array,int size,int index) {
        int parent = index;
        int child = 2 * parent + 1;
        while (child < size) {
            if (child + 1 < size && array[child + 1] > array[child]) {
                child = child + 1;
            }
            if (array[child] > array[parent]) {
                int tmp = array[child];
                array[child] = array[parent];
                array[parent] = tmp;
            } else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }
    }

//取队首元素
    public Integer peek() {
        if(size==0) {
            return null;
        }
        return array[0];
    }


    public boolean isEmpty() {
        return size==0;
    }


    public static void main(String[] args) {
        MyPriorityQueue queue = new MyPriorityQueue();
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
        }
    }
}