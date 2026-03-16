class Solution {
    public void Merge(int nums[],int l,int m,int r){
        int n1=m-l+1;
        int n2=r-m;
        int L[]=new int[n1];
        int R[]=new int[n2];
        for(int i=0;i<n1;i++){
            L[i]=nums[l+i];
        }
        for(int j=0;j<n2;j++){
            R[j]=nums[m+j+1];
        }
        int i=0,j=0;
        int k=l;
        while(i<n1 && j<n2){
            if(L[i]<=R[j])
            {   nums[k]=L[i];
                i++;
                k++;
            }else{
                nums[k]=R[j];
                j++;
                k++;
            }
        }
        while(i<n1){
            nums[k]=L[i];
            i++;
            k++;
        }
        while(j<n2){
            nums[k]=R[j];
            j++;
            k++;
        }
    }
    public void MergeSort(int nums[],int l,int r)
    {
        if(l<r){
            int m=l+(r-l)/2;
            MergeSort(nums,l,m);
            MergeSort(nums,m+1,r);
            Merge(nums,l,m,r);
        }
    }
    public int[] sortArray(int[] nums) {
        MergeSort(nums,0,nums.length-1);
        return nums;
    }
}