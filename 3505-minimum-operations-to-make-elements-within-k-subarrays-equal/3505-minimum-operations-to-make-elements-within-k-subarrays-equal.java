import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] count, sum;

        Fenwick(int n) {
            this.n = n;
            count = new long[n + 1];
            sum = new long[n + 1];
        }

        void add(int i, long c, long s) {
            for (; i <= n; i += i & -i) {
                count[i] += c;
                sum[i] += s;
            }
        }

        long queryCount(int i) {
            long res = 0;
            for (; i > 0; i -= i & -i)
                res += count[i];
            return res;
        }

        long querySum(int i) {
            long res = 0;
            for (; i > 0; i -= i & -i)
                res += sum[i];
            return res;
        }

        int kth(int k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= (int) count[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
        }

        // Step 2: Sliding window + Fenwick Tree
        Fenwick bit = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        for (int i = 0; i < n; i++) {

            bit.add(id[i], 1, nums[i]);
            windowSum += nums[i];

            if (i >= x) {
                bit.add(id[i - x], -1, -nums[i - x]);
                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {

                // Median (lower median)
                int medianIdx = bit.kth((x + 1) / 2);
                long median = sorted[medianIdx - 1];

                long leftCount = bit.queryCount(medianIdx);
                long leftSum = bit.querySum(medianIdx);

                long rightCount = x - leftCount;
                long rightSum = windowSum - leftSum;

                // Minimum cost to make window elements equal
                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;
            }
        }

        // Step 3: Dynamic Programming
        // prev[i] = minimum cost using previous number of windows
        // among first i elements.

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Skip current element
                curr[i] = curr[i - 1];

                // Select window ending at i - 1
                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}