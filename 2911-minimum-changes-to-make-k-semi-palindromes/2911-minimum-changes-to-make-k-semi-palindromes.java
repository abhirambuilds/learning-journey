import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Precompute proper divisors for all possible substring lengths
        List<Integer>[] divisors = new List[n + 1];
        for (int len = 1; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getCost(s, i, j, divisors[j - i + 1]);
            }
        }

        // dp[i][p] = min changes to partition prefix s[0..i-1] into p semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        int INF = 1_000_000;
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int i = 2; i <= n; i++) {
            for (int p = 1; p <= k; p++) {
                // Previous split point j must leave at least 2 chars for the last part
                for (int j = (p - 1) * 2; j <= i - 2; j++) {
                    if (dp[j][p - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    private int getCost(String s, int start, int end, List<Integer> divs) {
        int len = end - start + 1;
        int minChanges = Integer.MAX_VALUE;

        for (int d : divs) {
            int changes = 0;
            // Check each residue group modulo d
            for (int offset = 0; offset < d; offset++) {
                int left = start + offset;
                int right = end - (d - 1 - offset);
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }

        return minChanges;
    }
}
