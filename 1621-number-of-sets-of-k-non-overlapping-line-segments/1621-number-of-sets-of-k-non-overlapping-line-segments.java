class Solution {
        public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        // dp[i][0] means we are at point i with 0 open/active segment start
        // dp[i][1] means we are accumulating the sum of previous choices
        long[][] dp = new long[n + 1][2];
        
        long[] sum = new long[n + 1];
        
        // Using combinatorics or optimized DP:
        // Number of ways is equivalent to choosing k segments from n + k - 1 points: C(n + k - 1, 2k)
        return (int) combination(n + k - 1, 2 * k, MOD);
    }
    
    private long combination(int n, int k, long mod) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        if (k > n / 2) k = n - k;
        
        long[] c = new long[k + 1];
        c[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                c[j] = (c[j] + c[j - 1]) % mod;
            }
        }
        return c[k];
    }
}