import java.util.Arrays;

public class Heap {
    //堆是一个完全二叉树，通常使用数组来表示
    //数组中哪些元素是堆[0,size]
    //index表示从哪个下标出发进行调整
    public static void shiftDown(int[] array,int size,int index){
        int parent = index;
        //根据父节点下标，先找到左子树的下标
        int child = 2*parent+1;
        //循环条件的意思是，如果child对应节点存在，就继续调整，如果超过size说明
        //当前的parent已经是叶子节点，没有子节点了
        while (child < size) {
            //再去找下右子树对应的节点
            if(child+1<size&&array[child+1]>array[child]) {
                child = child+1;
            }
            //child经过上面的条件，已经不知道指向的是parent的左子树还是右子树了
            //child肯定是左右子树中值比较大的那个
            //接下来就可以拿刚才child位置的元素和parent对比
            //如果不符合大堆（child位置的元素比parent位置元素大，就交换parent和child位置的元素
            if(array[child]>array[parent]) {
                int tmp = array[child];
                array[child] = array[parent];
                array[parent] = tmp;
            }else{
                //当前child和parent 的关系已经符合大堆的要求了，说明就调整完了
                break;
            }
            //下次循环前，需要先更新parent和child
            parent = child;
            child = 2*parent+1;
        }
    }

//建堆操作 时间复杂度O(N)
    public static void creatHeap(int[] array,int size){
        for(int i = (size-1-1)/2;i>=0;i--){
            shiftDown(array,size,i);
        }
    }

    public static void main(String[] args) {
        int[] array = {9,5,2,7,3,6,8};
        creatHeap(array,array.length);
        System.out.println(Arrays.toString(array));
    }
}
