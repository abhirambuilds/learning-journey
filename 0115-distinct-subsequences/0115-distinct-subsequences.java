class Solution {
    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        if (m < n) {
            return 0;
        }

        // dp[j] = number of ways to form t[0...j-1]
        // using the characters of s processed so far
        int[] dp = new int[n + 1];

        // One way to form empty string: choose nothing
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {

            // MUST go backwards
            for (int j = n; j >= 1; j--) {

                // If current characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    // Don't use s[i-1] + use s[i-1]
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}