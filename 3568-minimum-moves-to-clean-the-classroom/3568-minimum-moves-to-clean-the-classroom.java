import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        int litterCount = 0;

        // Give every litter cell a bit number.
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        int allMask = (1 << litterCount) - 1;

        // state = {row, col, energy, mask, moves}
        Queue<int[]> queue = new LinkedList<>();

        /*
         * visited[r][c][mask][energy]
         *
         * energy ranges from 0 to energy.
         */
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        queue.offer(new int[]{startR, startC, energy, 0, 0});
        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int currEnergy = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            // All litter collected.
            if (mask == allMask) {
                return moves;
            }

            // If energy is 0, we cannot make another move.
            if (currEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid.
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle.
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int newEnergy = currEnergy - 1;

                // Collect litter if this cell contains one.
                int newMask = mask;

                if (litterId[nr][nc] != -1) {
                    newMask |= (1 << litterId[nr][nc]);
                }

                // Reset energy when entering R.
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                if (!visited[nr][nc][newMask][newEnergy]) {
                    visited[nr][nc][newMask][newEnergy] = true;

                    queue.offer(new int[]{
                            nr,
                            nc,
                            newEnergy,
                            newMask,
                            moves + 1
                    });
                }
            }
        }

        return -1;
    }
}