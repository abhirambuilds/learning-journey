class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        boolean[] cols = new boolean[n];
        boolean[] leftDiagonal = new boolean[2 * n - 1];
        boolean[] rightDiagonal = new boolean[2 * n - 1];
        backtrack(0, n, board, cols, leftDiagonal, rightDiagonal, res);
        return res;
    }
    private void backtrack(int row, int n, char[][] board, boolean[] cols, 
                           boolean[] leftDiagonal, boolean[] rightDiagonal, 
                           List<List<String>> res) {
        if (row == n) {
            res.add(construct(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            int leftIdx = row + col;
            int rightIdx = row - col + n - 1;
            if (!cols[col] && !leftDiagonal[leftIdx] && !rightDiagonal[rightIdx]) {
                board[row][col] = 'Q';
                cols[col] = true;
                leftDiagonal[leftIdx] = true;
                rightDiagonal[rightIdx] = true;
                backtrack(row + 1, n, board, cols, leftDiagonal, rightDiagonal, res);
                board[row][col] = '.';
                cols[col] = false;
                leftDiagonal[leftIdx] = false;
                rightDiagonal[rightIdx] = false;
            }
        }
    }
    private List<String> construct(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }
}
