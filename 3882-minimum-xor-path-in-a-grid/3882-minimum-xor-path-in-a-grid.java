class Solution {
    public int minCost(int[][] grid) {
                int m = grid.length;
        int n = grid[0].length;
        
        // Find the maximum element in the grid to determine the upper bound for XOR states
        int maxVal = 0;
        for (int[] row : grid) {
            for (int val : row) {
                maxVal = Math.max(maxVal, val);
            }
        }
        
        // Find the next power of 2 greater than maxVal to safely bound bitwise operations
        int maxXorBound = 1 << (32 - Integer.numberOfLeadingZeros(maxVal));
        if (maxXorBound == 0) {
            maxXorBound = 1; // Safeguard if maxVal is 0
        }

        // f[i][j][k] will be true if an XOR path value of 'k' is possible at cell (i, j)
        boolean[][][] f = new boolean[m][n][maxXorBound];
        
        // Base case: Starting position
        int startVal = grid[0][0];
        if (startVal < maxXorBound) {
            f[0][0][startVal] = true;
        } else {
            // Fallback safety layer: dynamically sizing if grid values can exceed maxVal via XOR accumulation
            // (Standard constraint sizes typically fit within twice the max element's next power of 2)
            return fallbackBfsSolution(grid);
        }

        // Fill out the dynamic programming table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                int currentVal = grid[i][j];
                
                for (int k = 0; k < maxXorBound; k++) {
                    // Check if we can reach the current state from the top neighbor
                    if (i > 0 && f[i - 1][j][k ^ currentVal]) {
                        f[i][j][k] = true;
                    }
                    // Check if we can reach the current state from the left neighbor
                    if (j > 0 && f[i][j - 1][k ^ currentVal]) {
                        f[i][j][k] = true;
                    }
                }
            }
        }

        // Find the smallest possible XOR value that successfully reached the bottom-right cell
        for (int k = 0; k < maxXorBound; k++) {
            if (f[m - 1][n - 1][k]) {
                return k;
            }
        }

        return fallbackBfsSolution(grid);
    }
    
    // A robust fallback using HashSet tracking in case intermediate XOR values blow past the max bitwise bound
    private int fallbackBfsSolution(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        java.util.Set<Integer>[][] dp = new java.util.HashSet[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new java.util.HashSet<>();
            }
        }
        
        dp[0][0].add(grid[0][0]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    for (int val : dp[i - 1][j]) {
                        dp[i][j].add(val ^ grid[i][j]);
                    }
                }
                if (j > 0) {
                    for (int val : dp[i][j - 1]) {
                        dp[i][j].add(val ^ grid[i][j]);
                    }
                }
            }
        }
        
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[m - 1][n - 1]) {
            minXor = Math.min(minXor, val);
        }
        return minXor;
    }
}