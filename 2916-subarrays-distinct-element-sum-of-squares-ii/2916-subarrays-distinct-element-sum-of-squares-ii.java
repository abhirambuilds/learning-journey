class Solution {
    private static final int MOD = 1000000007;

    private static class SegmentTree {
        private int n;
        private long[] sum;
        private long[] lazy;
        private long[] sqSum;

        public SegmentTree(int n) {
            this.n = n;
            this.sum = new long[4 * n];
            this.sqSum = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        private void push(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = (l + r) / 2;
                apply(node * 2, l, mid, lazy[node]);
                apply(node * 2 + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        private void apply(int node, int l, int r, long val) {
            long len = r - l + 1;
            sqSum[node] = (sqSum[node] + 2 * sum[node] % MOD * val % MOD + val * val % MOD * len % MOD) % MOD;
            sum[node] = (sum[node] + val * len % MOD) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        public void update(int node, int l, int r, int ql, int qr, long val) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            push(node, l, r);
            int mid = (l + r) / 2;
            update(node * 2, l, mid, ql, qr, val);
            update(node * 2 + 1, mid + 1, r, ql, qr, val);
            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sqSum[node] = (sqSum[node * 2] + sqSum[node * 2 + 1]) % MOD;
        }

        public long getSqSum() {
            return sqSum[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;
        long ans = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.getOrDefault(nums[r], -1) + 1;
            tree.update(1, 0, n - 1, l, r, 1);
            lastSeen.put(nums[r], r);
            ans = (ans + tree.getSqSum()) % MOD;
        }

        return (int) ans;
    }
}