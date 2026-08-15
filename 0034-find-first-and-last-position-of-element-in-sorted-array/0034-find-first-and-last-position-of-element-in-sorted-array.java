class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstIdx=-1;
        int secondIdx=-1;
        firstIdx=firstIdx(nums,target);
        secondIdx=secondIdx(nums,target);
        return new int []{firstIdx,secondIdx};
    }
    private int firstIdx(int nums[],int target){
        int ans=-1;
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                ans=mid;
                right=mid-1;
            }else if(nums[mid]<=target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return ans;
    }
    private int secondIdx(int nums[],int target){
        int ans=-1;
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                ans=mid;
                left=mid+1;
            }else if(nums[mid]<=target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return ans;
    }
}