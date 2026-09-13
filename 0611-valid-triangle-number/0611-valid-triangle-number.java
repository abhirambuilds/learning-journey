class Solution {
    public int triangleNumber(int[] nums) {
        int count=0;
        Arrays.sort(nums);
        for(int k=nums.length-1;k>=2;k--){
            int left=0;
            int right=k-1;
            while(left<right){
                if(nums[left]+nums[right]>nums[k]){
                    count+=right-left;
                    right--;
                }else{
                    left++;
                }
            }
        }
        return count;
    }
}