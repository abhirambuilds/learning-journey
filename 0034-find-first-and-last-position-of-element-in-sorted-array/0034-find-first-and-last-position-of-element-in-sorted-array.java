class Solution {
    public int[] searchRange(int[] nums, int target) {
        return getIdx(nums,target,nums.length - 1,new int[]{-1, -1});
    }
    public int[] getIdx(int nums[], int target, int idx, int res[]) {
        if (idx < 0) return res;
         if (nums[idx] == target) {       
            if(res[1] == -1) res[1] = idx; 
            res[0] = idx;         
        }
        return getIdx(nums, target, idx - 1, res);
    }
}