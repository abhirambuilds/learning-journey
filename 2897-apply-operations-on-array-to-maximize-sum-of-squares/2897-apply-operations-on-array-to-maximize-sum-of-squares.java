class Solution {
    public int maxSum(List<Integer> nums, int k) {
                final int MOD = 1_000_000_007; // 10^9 + 7
        final int MAX_BIT = 31; // Given nums[i] <= 10^9, 31 bits are sufficient
        
        // Step 1: Count the total frequency of set bits (1s) at each bit position
        int[] bitCounts = new int[MAX_BIT];
        for (int num : nums) {
            for (int i = 0; i < MAX_BIT; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        
        // Step 2: Greedily construct the k largest numbers
        for (int step = 0; step < k; step++) {
            long currentNumber = 0;
            
            // Build the largest possible number from the remaining available bits
            for (int i = 0; i < MAX_BIT; i++) {
                if (bitCounts[i] > 0) {
                    currentNumber |= (1L << i);
                    bitCounts[i]--; // Use up one '1' bit at position i
                }
            }
            
            // Add the square of the constructed number to the total sum
            long square = (currentNumber * currentNumber) % MOD;
            totalSumOfSquares = (totalSumOfSquares + square) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}