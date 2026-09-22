import java.util.Arrays;

class Solution {
    static class Node {
        int prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        this.tree = new Node[4 * n];

        build(nums, 0, 0, n - 1);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Update the value at the given index persistently
            update(0, 0, n - 1, index, value % k);

            // 2. Query the range from 'start' to the end of the array
            Node queryResult = query(0, 0, n - 1, start, n - 1);

            // 3. Collect the count of prefixes with product remainder equal to x
            result[i] = queryResult != null ? queryResult.remain[x] : 0;
        }

        return result;
    }

    private void build(int[] nums, int node, int start, int end) {
        tree[node] = new Node(k);
        if (start == end) {
            int val = nums[start] % k;
            tree[node].prod = val;
            tree[node].remain[val] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(nums, 2 * node + 1, start, mid);
        build(nums, 2 * node + 2, mid + 1, end);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            Arrays.fill(tree[node].remain, 0);
            tree[node].prod = val;
            tree[node].remain[val] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l > end || r < start) {
            return null;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        Node leftNode = query(2 * node + 1, start, mid, l, r);
        Node rightNode = query(2 * node + 2, mid + 1, end, l, r);

        if (leftNode == null) return rightNode;
        if (rightNode == null) return leftNode;

        return merge(leftNode, rightNode);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        // Prefixes entirely within the left child
        for (int i = 0; i < k; i++) {
            res.remain[i] += left.remain[i];
        }

        // Prefixes that cross over into the right child
        for (int j = 0; j < k; j++) {
            if (right.remain[j] > 0) {
                int combinedRemainder = (left.prod * j) % k;
                res.remain[combinedRemainder] += right.remain[j];
            }
        }

        return res;
    }
}
