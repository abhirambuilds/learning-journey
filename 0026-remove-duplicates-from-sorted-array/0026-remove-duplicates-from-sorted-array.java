class Solution {
    public int removeDuplicates(int[] nums) {
        int write=0;
        for(int read=0;read<nums.length;read++){
            if(nums[write]!=nums[read]){
                write++;
                nums[write]=nums[read];
            }
        }
        return write+1;
    }
}