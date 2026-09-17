public class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLenUpTo[i] stores the minimum length of a valid subarray 
        // with sum == target found within the prefix arr[0...i]
        int[] minLenUpTo = new int[n];
        // Initialize with a large value representing infinity
        Arrays.fill(minLenUpTo, Integer.MAX_VALUE / 2); 
        
        int minTotalLength = Integer.MAX_VALUE;
        int currentWindowSum = 0;
        int left = 0;
        
        // Sliding window
        for (int right = 0; right < n; right++) {
            currentWindowSum += arr[right];
            
            // Shrink the window from the left if the sum exceeds target
            while (currentWindowSum > target && left <= right) {
                currentWindowSum -= arr[left];
                left++;
            }
            
            // If we found a valid subarray summing up to target
            if (currentWindowSum == target) {
                int currentLen = right - left + 1;
                
                // 1. Check if a non-overlapping valid subarray exists before 'left'
                if (left > 0 && minLenUpTo[left - 1] != Integer.MAX_VALUE / 2) {
                    minTotalLength = Math.min(minTotalLength, currentLen + minLenUpTo[left - 1]);
                }
                
                // 2. Update the current index in minLenUpTo with the minimum length found up to 'right'
                if (right > 0) {
                    minLenUpTo[right] = Math.min(minLenUpTo[right - 1], currentLen);
                } else {
                    minLenUpTo[right] = currentLen;
                }
            } else {
                // If the current window doesn't match the target, carry forward the previous best minimum length
                if (right > 0) {
                    minLenUpTo[right] = minLenUpTo[right - 1];
                }
            }
        }
        return minTotalLength >= Integer.MAX_VALUE / 2 ? -1 : minTotalLength;
    }
}