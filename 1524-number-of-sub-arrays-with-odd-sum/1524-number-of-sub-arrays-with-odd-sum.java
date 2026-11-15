class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        
        // base case: a prefix sum of 0 before starting is even
        int evenCount = 1; 
        int oddCount = 0;
        
        int currentPrefixSum = 0;
        int totalOddSubarrays = 0;
        
        for (int num : arr) {
            currentPrefixSum += num;
            
            // Check if current prefix sum is odd or even
            if (currentPrefixSum % 2 != 0) {
                // Odd prefix sum: pairs up with all previous even prefix sums
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Even prefix sum: pairs up with all previous odd prefix sums
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }
        
        return totalOddSubarrays;
    }
}
