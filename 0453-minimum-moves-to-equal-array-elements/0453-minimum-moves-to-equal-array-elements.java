class Solution {
    public int minMoves(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int min = nums[0];
        long sum = 0; // Use long to prevent integer overflow during sum accumulation
        
        for (int num : nums) {
            sum += num;
            if (num < min) {
                min = num;
            }
        }
        
        return (int) (sum - (long) min * nums.length);
    }
}