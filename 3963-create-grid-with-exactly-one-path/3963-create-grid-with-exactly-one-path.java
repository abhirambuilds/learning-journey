class Solution {
    public String[] createGrid(int m, int n) {
                char[][] grid = new char[m][n];
        
        // Step 1: Fill the entire grid with obstacles ('#')
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Step 2: Open up the entire first row (index 0)
        Arrays.fill(grid[0], '.');
        
        // Step 3: Open up the entire last column (index n - 1)
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Step 4: Convert the character array into an array of Strings
        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }
        
        return ans;
    }
}