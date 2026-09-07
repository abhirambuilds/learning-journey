class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] dp = new int[26];
        int totalSubsequences = 0;
        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            int currentAdded = (totalSubsequences + 1) % MOD;
            int nextTotal = (totalSubsequences + currentAdded) % MOD;
            nextTotal = (nextTotal - dp[charIndex] + MOD) % MOD;
            totalSubsequences = nextTotal;
            dp[charIndex] = currentAdded;
        }
        return totalSubsequences;
    }
}