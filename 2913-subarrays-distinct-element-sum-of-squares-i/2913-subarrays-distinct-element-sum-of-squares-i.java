class Solution {
    public int sumCounts(List<Integer> nums) {
                int totalSum = 0;
        int n = nums.size();
        
        // Loop over each possible starting index of the subarray
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            
            // Expand the subarray from index i to j
            for (int j = i; j < n; j++) {
                // Add the current element to the set (duplicates are handled automatically)
                distinctElements.add(nums.get(j));
                
                // Get the count of distinct elements in the current subarray nums[i...j]
                int distinctCount = distinctElements.size();
                
                // Square the count and add it to the total sum
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}