class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                // Increment depth first for the opening bracket, then assign based on parity
                depth++;
                ans[i] = depth % 2;
            } else {
                // Assign closing bracket to match its opening bracket's group, then decrement depth
                ans[i] = depth % 2;
                depth--;
            }
        } 
        return ans;
    }
}