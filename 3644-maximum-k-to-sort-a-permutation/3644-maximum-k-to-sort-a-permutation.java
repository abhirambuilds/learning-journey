class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        boolean isSorted = true;
        
        for (int i = 0; i < nums.length; ++i) {
            // An element is misplaced if its value doesn't match its index
            if (nums[i] != i) {
                ans &= nums[i];
                isSorted = false;
            }
        }
        
        // If the array is already sorted, return 0. Otherwise, return the accumulated AND value.
        return isSorted ? 0 : ans;
    }
}