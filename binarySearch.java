package InterviewImportant.BinarySearch;

public class binarySearch {


    //二分查找，时间复杂度O（logN），空间复杂度O（1）
    public static boolean bianrySearch(int[] arr,int target){
        int left =0;
        int right = arr.length-1;

        while (left < right) {
            int mid = (left+right)/2;

            if (target == arr[mid]) {
                return true;
            }else if (target>arr[mid]){
                left = mid+1;

            }else {
                right = mid-1;

            }
        }
        return false;
    }


}
