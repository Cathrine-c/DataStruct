package InterviewImportant.DS;

import java.util.Arrays;

public class MySort {

    public static void main(String[] args) {
        int[] arr = {2,3,4,3,3,8,0,9};

        //insertSort(arr);
        //shellSort(arr);

        //bubbleSort(arr);
        //selectSort(arr);
       // quickSort(arr);
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    //插入排序:时间复杂度O（N^2）,空间复杂度：O（1） 稳定
    //把数组分为无序区间和有序区间,有序区间[0,i],无序区间[i+1,arr.length]
    //每次从无序区间选一个数和有序区间中的数比较，大于它就往后放
    public static void insertSort(int[] arr){
       for (int i=1;i<arr.length;i++){
           int cur = i-1;

           int currrent = arr[i];

           for (;cur>=0;cur--){
               if (currrent<arr[cur]){
                   arr[cur+1] =arr[cur];
               }else {
                   break;
               }
           }

           arr[cur+1] = currrent;
       }
    }


    //希尔排序：复杂度o(N^1.3)  空间复杂度o（1） 不稳定
    public static void shellSort(int[] arr){
        int gap = arr.length/2;
        while (gap > 1) {
            insertSortWithGap(arr,gap);
            gap/=2;

        }
        insertSortWithGap(arr,1);

    }

    private static void insertSortWithGap(int[] arr, int gap) {

        for (int i=gap;i<arr.length-1;i++){
            int current = arr[i];
            int cur = i-gap;

            for (;cur>=0;cur-=gap){
                if (current<arr[cur]){
                    arr[cur+gap] = arr[cur];
                }else {
                    break;
                }

            }

            arr[cur+gap] = current;
        }
    }


    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i]=  arr[j];
        arr[j] = tmp;
    }

    //冒泡排序：时间复杂度o（N^2） ，空间复杂度o（1） 稳定
    public static void bubbleSort(int[] arr){

        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }

            }
        }
    }


    //选择排序：时间复杂度o(N^2)  空间复杂度o(1) 不稳定
    //每次从无序区间选择最大或者最小的元素放在数组末尾，有序区间[arr.length-i,arr.length] 无序区间[0,arr.length-i]
    public static void  selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int maxIndex = 0;

            for (int j=1;j<arr.length-i;j++){

                if (arr[j]>arr[maxIndex]){
                    maxIndex = j;
                }
            }

            swap(arr,maxIndex,arr.length-i-1);
        }
    }


    //快速排序:时间复杂度o（NlogN）  空间复杂度o（1），不稳定
    public static void quickSort(int[] arr){

        quickSortHelper(arr,0,arr.length-1);

    }

    private static void quickSortHelper(int[] arr, int left, int right) {

        if (left>=right) {
            return;
        }

        int mid = partition(arr,left,right);
        quickSortHelper(arr,left,mid-1);
        quickSortHelper(arr,mid+1,right);
    }


    private static int partition(int[] arr, int left, int right) {

        int i = left;
        int j= right;

        int key = arr[left];

        while (i<j){

            while (i<j&&arr[j]>=key){
                j--;
            }

            while (i<j&&arr[i]<=key){
                i++;
            }

            swap(arr,i,j);

        }

        swap(arr,i,left);
        return i;

    }

    //归并排序:时间复杂度o（NlogN） 空间复杂度o(n)
    //采用分治思想，将数组不停二分，直到最小区间，然后比较合并，向上合并，类似于合并两个有序数组
    public static void mergeSort(int[] arr){
        mergeSortHelper(arr,0,arr.length);
    }

    private static void mergeSortHelper(int[] arr, int low, int high) {

        if (low >= high-1) {
            return;
        }

        int mid = (high+low)/2;
        mergeSortHelper(arr,low,mid);
        mergeSortHelper(arr,mid,high);
        merge(arr,low,mid,high);


    }

    private static void merge(int[] arr, int low, int mid, int high) {

        int i=low;
        int j=mid;
        int len = high-low;
        int[] newArr = new int[len];
        int k = 0;

        while (i < mid && j < high) {
            if (arr[i]<=arr[j]){
                newArr[k++] = arr[i++];

            }else {
                newArr[k++] = arr[j++];
            }

        }

        while (i < mid) {
            newArr[k++]= arr[i++];
        }

        while (j < high) {
            newArr[k++] = arr[j++];
        }

        for (int t=0;t<len;t++){
            arr[low+t] = newArr[t];
        }

    }


    public static void createHeap(int[] arr){
        

    }


}
