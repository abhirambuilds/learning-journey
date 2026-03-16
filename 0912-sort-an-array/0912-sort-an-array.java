class Solution {
    public int Partition(int nums[],int low,int high){
        Swap(nums, (low + high) / 2, high); 
        int pivot=nums[high];
        int i=low-1;
        for(int j=low;j<=high-1;j++){
            if(nums[j] < pivot){
                i++;
                Swap(nums,i,j);
            }
        }
        Swap(nums,i+1,high);
        return i+1;
    }
    public void Swap(int arr[],int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public void QuickSort(int nums[],int low,int high){
        if(low < high){
            int pi=Partition(nums,low,high);
            QuickSort(nums,low,pi-1);
            QuickSort(nums,pi+1,high);
        }
    }
    public int[] sortArray(int[] nums) {
        QuickSort(nums,0,nums.length-1);
        return nums;
    }
}