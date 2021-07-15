package InterviewImportant.jianzhi;

import java.util.Arrays;

public class Day0715 {




    //输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。


    public int[] topK(int[] arr,int k){

        for (int i=arr.length/2-1;i>=0;i--){
            minK(arr,i,arr.length);
        }

        for (int i=arr.length-1;i>=0&&k>0;i--){

            swap(arr,0,i);
            minK(arr,0,i);

        }
        return arr;

    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }

    private void minK(int[] arr, int index, int length) {

        int rootValue = arr[index];

        for (int k = index*2+1;k<length;k = k*2+1){
            if (k+1<length&&arr[k]>arr[k+1]) {
                k = k + 1;
            }

            if (arr[k] < rootValue) {
                arr[index] = arr[k];

                index = k;

            }else {

                break;

            }

        }
        arr[index] = rootValue;
    }


    public static void main(String[] args) {

        int[] arr = {4,3,2,8,7,5,6};
        heapSort(arr);
      //  System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){

        for (int i=arr.length-1;i>=0;i--){
            //建堆
            adjustDown(arr,arr.length-i-1,i);
            System.out.println(Arrays.toString(arr));
        }


        for (int i=0;i<arr.length;i++){

            //交换
            swap1(arr,0,arr.length-i-1);

            adjustDown(arr,arr.length-i-1,0);

        }
    }

    private static void swap1(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;

    }


    private static void adjustDown(int[] arr, int size, int index) {
        int parent = index;
        int child = 2*parent+1;

        while (child < size) {
            if (child+1<size&&arr[child]<arr[child+1]){

                child = child+1;

            }

            if (arr[child]>arr[parent]){

                swap1(arr,child,parent);
            }else {
                break;
            }

            parent = child;
            child = 2*parent+1;

        }

    }


}
