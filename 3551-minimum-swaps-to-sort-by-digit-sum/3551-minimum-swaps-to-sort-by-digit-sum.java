class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // 1. Create a companion array of Integer objects to sort
        Integer[] sortedNums = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[i];
        }
        
        // 2. Sort the array based on the custom rules:
        //    Primary: Increasing order of digit sums
        //    Secondary (Tie-breaker): Smaller actual number comes first
        Arrays.sort(sortedNums, (a, b) -> {
            int sumA = getDigitSum(a);
            int sumB = getDigitSum(b);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(a, b);
        });
        
        // 3. Map each unique number to its targeted index in the sorted array
        Map<Integer, Integer> targetPos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetPos.put(sortedNums[i], i);
        }
        
        // 4. Cycle Decomposition to calculate minimum swaps
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            // Skip if the element is already visited or already in its correct spot
            if (visited[i] || targetPos.get(nums[i]) == i) {
                continue;
            }
            
            // Trace the length of the current cycle
            int cycleSize = 0;
            int currentIdx = i;
            
            while (!visited[currentIdx]) {
                visited[currentIdx] = true;
                // Move to the index where the current element *should* go
                currentIdx = targetPos.get(nums[currentIdx]);
                cycleSize++;
            }
            
            // A cycle of size 'k' requires 'k - 1' swaps
            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }
        
        return totalSwaps;
    }
    
    // Helper function to calculate the sum of digits of a positive number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}