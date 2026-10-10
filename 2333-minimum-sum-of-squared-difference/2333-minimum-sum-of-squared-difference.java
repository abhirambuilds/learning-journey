class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
               int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Find the absolute differences and track the maximum difference
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        // If max difference is already 0, no adjustments needed
        if (maxDiff == 0) return 0;
        
        // Create a frequency bucket array for the differences
        int[] bucket = new int[maxDiff + 1];
        for (int d : diffs) {
            bucket[d]++;
        }
        
        // Greedily reduce the largest differences down to smaller differences
        for (int d = maxDiff; d > 0; d--) {
            if (bucket[d] > 0) {
                // Determine how many elements at difference 'd' we can reduce
                long take = Math.min(totalK, (long) bucket[d]);
                
                bucket[d] -= take;
                bucket[d - 1] += take; // Shifting 'take' elements from 'd' to 'd - 1'
                totalK -= take;
                
                if (totalK == 0) {
                    break;
                }
            }
        }
        
        // Calculate the minimum sum of squared differences
        long minSumSquare = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (bucket[d] > 0) {
                minSumSquare += (long) bucket[d] * d * d;
            }
        }
        
        return minSumSquare;
    } 
    
}