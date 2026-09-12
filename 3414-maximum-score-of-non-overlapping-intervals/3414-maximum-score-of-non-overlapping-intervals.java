class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];
        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1); 
            a[i][2] = intervals.get(i).get(2); 
            a[i][3] = i;                      
        }
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);
            return Integer.compare(x[3], y[3]);
        });
        long[][] dp = new long[5][n];
        List<Integer>[][] path =new ArrayList[5][n];
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i < n; i++) {
                path[k][i] = new ArrayList<>();
            }
        }
        for (int i = 0; i < n; i++) {
            int j = findPrevious(a, i);
            for (int k = 1; k <= 4; k++) {
                long skip = i == 0 ? 0 : dp[k][i - 1];
                List<Integer> skipPath =i == 0? new ArrayList<>(): path[k][i - 1];
                long take = a[i][2];
                List<Integer> takePath =new ArrayList<>();
                if (j >= 0) {
                    take += dp[k - 1][j];
                    takePath.addAll(path[k - 1][j]);
                }
                takePath.add(a[i][3]);
                if (take > skip) {
                    dp[k][i] = take;
                    path[k][i] = takePath;
                } else if (take < skip) {
                    dp[k][i] = skip;
                    path[k][i] =new ArrayList<>(skipPath);
                } else {
                    dp[k][i] = skip;
                    if (lexicographicallySmaller(takePath, skipPath)) {
                        path[k][i] = takePath;
                    } else {
                        path[k][i] =new ArrayList<>(skipPath);
                    }
                }
            }
        }
        int[] ans =path[4][n - 1].stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(ans);
        return ans;
    }
    private int findPrevious(int[][] a, int i) {
        int low = 0;
        int high = i - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid][1] < a[i][0]) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean lexicographicallySmaller(List<Integer> a, List<Integer> b) {
        List<Integer> x = new ArrayList<>(a);
        List<Integer> y = new ArrayList<>(b);
        Collections.sort(x);
        Collections.sort(y);
        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i);
            }
        }
        return x.size() < y.size();
    }
        
}