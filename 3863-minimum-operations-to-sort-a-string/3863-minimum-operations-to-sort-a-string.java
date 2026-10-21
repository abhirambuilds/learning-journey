class Solution {
    public int minOperations(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        
        // 1. Check if the string is already sorted
        boolean isSorted = true;
        char mn = cs[0];
        char mx = cs[0];
        
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }
        
        if (isSorted) {
            return 0;
        }
        
        // 2. An unsorted string of length 2 cannot be sorted without full string operations
        if (n == 2) {
            return -1;
        }
        
        // 3. Minimum is at the start OR Maximum is at the end -> 1 operation
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        
        // 4. Minimum or Maximum exists in the middle -> 2 operations
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        
        // 5. Inverted boundaries (e.g., mx at index 0 and mn at index n-1) -> 3 operations
        return 3;
    }
}