class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute Pascal's Triangle for Combination C(n, k) calculations
        table = new int[n][n];
        for (int i = 0; i < n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 to exclude the original array ordering
        return (getWays(list) - 1 + MOD) % MOD;
    }

    private int getWays(List<Integer> list) {
        // Base case: arrays with 2 or fewer elements have only 1 valid arrangement
        if (list.size() <= 2) {
            return 1;
        }

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partioning the array elements into left and right subtrees
        for (int i = 1; i < list.size(); i++) {
            int current = list.get(i);
            if (current < root) {
                left.add(current);
            } else {
                right.add(current);
            }
        }

        // total combinations = C(left.size() + right.size(), left.size())
        long totalSlots = table[list.size() - 1][left.size()];
        
        // Recursively compute ways for subtrees and apply modulo arithmetic
        long leftWays = getWays(left);
        long rightWays = getWays(right);

        return (int) (((totalSlots * leftWays) % MOD * rightWays) % MOD);
    }
}