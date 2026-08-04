class Solution {
    public int firstMissingPositive(int[] nums) {
      int i=0;
      while(i<nums.length){
        if(nums[i]>0&&nums[i]<=nums.length){
            int correctIndex=nums[i]-1;
            if(nums[i]!=nums[correctIndex]){
                int temp=nums[correctIndex];
                nums[correctIndex]=nums[i];
                nums[i]=temp;
            }else i++;
        }else i++;
      }
      for(int j=0;j<nums.length;j++){
        if(nums[j]!=j+1) return j+1;
      }
      return nums.length+1;
    }
}