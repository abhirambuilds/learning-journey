import java.util.*;

class Solution {
    private int n;
    private List<Integer>[] adj;
    private int[] parent, depth, heavy, head, pos;
    private int curPos;
    private SegmentTree segTree;
    private char[] nodeChars;

    @SuppressWarnings("unchecked")
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.nodeChars = s.toCharArray();
        this.adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // HLD state arrays
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);
        curPos = 0;

        // DFS to find heavy edges, depths, and parents
        dfs(0, -1, 0);
        // DFS to decompose into heavy paths
        decompose(0, 0);

        // Build segment tree based on HLD positions
        int[] initialValues = new int[n];
        for (int i = 0; i < n; i++) {
            initialValues[pos[i]] = 1 << (nodeChars[i] - 'a');
        }
        segTree = new SegmentTree(n, initialValues);

        List<Boolean> ans = new ArrayList<>();
        for (String query : queries) {
            String[] parts = query.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                nodeChars[u] = c;
                segTree.update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v);
                // A bitmask can be a palindrome if it's 0 or has exactly 1 bit set
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private int dfs(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtreeSize = 0;
        for (int v : adj[u]) {
            if (v != p) {
                int subtreeSize = dfs(v, u, d + 1);
                size += subtreeSize;
                if (subtreeSize > maxSubtreeSize) {
                    maxSubtreeSize = subtreeSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void decompose(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= segTree.query(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= segTree.query(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }

    // Standard Point Update, Range Query Segment Tree for XOR operations
    private static class SegmentTree {
        int[] tree;

        SegmentTree(int n, int[] initialValues) {
            tree = new int[4 * n];
            build(1, 0, n - 1, initialValues);
        }

        void build(int node, int start, int end, int[] initialValues) {
            if (start == end) {
                tree[node] = initialValues[start];
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid, initialValues);
            build(2 * node + 1, mid + 1, end, initialValues);
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return 0;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, l, r) ^ query(2 * node + 1, mid + 1, end, l, r);
        }
    }
}
