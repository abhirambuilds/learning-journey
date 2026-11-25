import java.util.*;

class Solution {
    private static final long M1 = 1_000_000_007L;
    private static final long M2 = 998_244_353L;
    private long[] h1, h2, p1, p2;
    private int n;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        // Randomized base prevents anti-hash collision test cases
        long base = 100_003L + new Random().nextInt(1_000_000);
        
        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;

        // Precompute prefix hashes and powers
        for (int i = 0; i < n; i++) {
            // Add offset to handle 0 or negative numbers cleanly
            long val = nums[i] + 1_000_000_001L; 
            h1[i + 1] = (h1[i] * base + val) % M1;
            h2[i + 1] = (h2[i] * base + val) % M2;
            p1[i + 1] = (p1[i] * base) % M1;
            p2[i + 1] = (p2[i] * base) % M2;
        }

        // Binary search for the minimum valid subarray length
        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (hasUniqueWindow(mid)) {
                hi = mid; // Valid length found; try finding a shorter one
            } else {
                lo = mid + 1; // No unique subarray of this length; must go longer
            }
        }
        return lo;
    }

    private boolean hasUniqueWindow(int len) {
        // Track frequency of each window hash: 1 = seen once, 2 = seen multiple times
        Map<Long, Integer> seen = new HashMap<>(n - len + 1);

        for (int i = 0; i + len <= n; i++) {
            long hash1 = (h1[i + len] - (h1[i] * p1[len]) % M1 + M1) % M1;
            long hash2 = (h2[i + len] - (h2[i] * p2[len]) % M2 + M2) % M2;
            long combinedHash = (hash1 << 32) | hash2;

            seen.merge(combinedHash, 1, (oldVal, one) -> 2);
        }

        for (int count : seen.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}
