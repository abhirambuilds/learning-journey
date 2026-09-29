class Solution {
    private int m, n;
    private char[][] grid;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;

        // Base optimizations / pruning:
        // 1. Total path length (m + n - 1) must be even to form pairs
        // 2. Start must be '(' and end must be ')'
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Memoization array: row, col, and current balance
        // The maximum possible balance cannot exceed the path length (m + n)
        this.memo = new Boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        // Out of bounds check
        if (i >= m || j >= n) {
            return false;
        }

        // Update the balance based on current cell
        k += (grid[i][j] == '(') ? 1 : -1;

        // If balance drops below 0, the parentheses configuration is invalid
        if (k < 0) {
            return false;
        }

        // Target cell reached: valid if balance is perfectly 0
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }

        // If this state (i, j, k) has already been computed, return its result
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }

        // Move right or move down
        boolean pathExists = dfs(i + 1, j, k) || dfs(i, j + 1, k);

        // Store result in memoization table
        return memo[i][j][k] = pathExists;
    }
}
