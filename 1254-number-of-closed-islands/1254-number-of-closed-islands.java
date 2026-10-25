class Solution {
    public int closedIsland(int[][] grid) {
             int m = grid.length;
        int n = grid[0].length;
        
        // Step 1: Sink all land (0) connected to the 4 boundaries (turned to 1)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (grid[i][j] == 0) {
                        dfs(grid, i, j);
                    }
                }
            }
        }
        
        // Step 2: Count the remaining closed islands
        int closedIslandsCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    closedIslandsCount++;
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark as visited/water
        grid[r][c] = 1;
        
        // Explore 4-directionally
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}