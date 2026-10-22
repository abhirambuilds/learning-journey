class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        // Find the index of 0
        int idx0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        // Case 1: Check if sorted in increasing cyclic order (step = 1)
        if (check(nums, idx0, 1)) {
            // Option 1: Just rotate left until 0 is at index 0
            ans = Math.min(ans, idx0);
            // Option 2: Reverse, rotate left from the other side, reverse back
            ans = Math.min(ans, n - idx0 + 2);
        }
        
        // Case 2: Check if sorted in decreasing cyclic order (step = -1)
        if (check(nums, idx0, -1)) {
            // Option 1: Reverse, then rotate left
            ans = Math.min(ans, idx0 + 2);
            // Option 2: Rotate left first, then reverse
            ans = Math.min(ans, n - idx0);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private boolean check(int[] nums, int idx0, int step) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            // Calculate index manually wrapping around cyclic boundaries safely
            int prevIdx = (idx0 + (i - 1) * step % n + n) % n;
            int currIdx = (idx0 + i * step % n + n) % n;
            
            if (nums[prevIdx] > nums[currIdx]) {
                return false;
            }
        }
        return true;
    }
}