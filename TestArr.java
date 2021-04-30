public class TestArr {
	public static void main1(String[] args){
		
		
	int arr[]={1,2,3,4,5,6,7};
	System.out.println(max(arr));
	}
	public static int max(int[] arr) {
		int max=arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i]>max){
				max = arr[i];
			}
		}
		return max;
	}

	public static void main2(String[] args){
		int i=1;
		int sum=0;
		for(i=1; i<=100;i++){
		if(i % 2==0){
			sum +=i;
			i++;
		}
		else{
			sum +=i;
			i++;
		}
			
		System.out.println("sum ="+ sum);
	}
}


	public static void main(String[] args){
           int sum = 0; 
           for (int i = 1; i <= n; i++) { 
           int t = 1; 
           for (int j = 1; j <= i; j++) { 
           t *= j; 
 } 
           sum += t; 
} 
           System.out.println("sum = " + sum);
	}
}













