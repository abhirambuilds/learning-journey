class Solution {
    public long maxAlternatingSum(int[] nums) {
                int n = nums.length;
        
        // Step 1: Replace each element with its square
        for (int i = 0; i < n; ++i) {
            nums[i] *= nums[i];
        }
        
        // Step 2: Sort the squared values in ascending order
        Arrays.sort(nums);
        
        // Step 3: Subtract the smaller half and add the larger half
        long ans = 0;
        int m = n >> 1; // equivalent to n / 2
        for (int i = 0; i < n; ++i) {
            ans += (i < m) ? -nums[i] : nums[i];
        }
        
        return ans;
    }
}