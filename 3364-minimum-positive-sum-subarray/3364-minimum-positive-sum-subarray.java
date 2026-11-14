import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        
        // Iterate through all valid window sizes
        for (int windowSize = l; windowSize <= r; ++windowSize) {
            int currentWindowSum = 0;
            
            // Compute the sum of the first window of size 'windowSize'
            for (int i = 0; i < windowSize; ++i) {
                currentWindowSum += nums.get(i);
            }
            
            // Check if the initial window sum is valid
            if (currentWindowSum > 0) {
                minSum = Math.min(minSum, currentWindowSum);
            }
            
            // Slide the window across the rest of the array
            for (int i = windowSize; i < n; ++i) {
                currentWindowSum -= nums.get(i - windowSize); // Remove leftmost element
                currentWindowSum += nums.get(i);              // Add new rightmost element
                
                if (currentWindowSum > 0) {
                    minSum = Math.min(minSum, currentWindowSum);
                }
            }
        }
        
        // If minSum wasn't updated, it means no valid positive subarray was found
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
