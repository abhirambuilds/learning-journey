class Solution {
    public int sortableIntegers(int[] nums) {
    int n = nums.length;
        long totalSum = 0;

        // Check every divisor k of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            if (isSortable(nums, k)) {
                totalSum += k;
            }
        }

        return (int) totalSum;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;
        int prevMax = Integer.MIN_VALUE;

        // Process each block of size k
        for (int i = 0; i < n; i += k) {
            int drops = 0;
            int minVal = Integer.MAX_VALUE;
            int maxVal = Integer.MIN_VALUE;

            // Analyze the current block from i to i + k - 1
            for (int j = 0; j < k; j++) {
                int curr = nums[i + j];
                int next = nums[i + (j + 1) % k]; // Circular check within the block

                if (curr > next) {
                    drops++;
                }
                minVal = Math.min(minVal, curr);
                maxVal = Math.max(maxVal, curr);
            }

            // A block can be rotated into sorted order if it has <= 1 drop
            if (drops > 1) {
                return false;
            }

            // The minimum of the current block must be >= the maximum of the previous block
            if (minVal < prevMax) {
                return false;
            }

            prevMax = maxVal;
        }

        return true;
    }
}