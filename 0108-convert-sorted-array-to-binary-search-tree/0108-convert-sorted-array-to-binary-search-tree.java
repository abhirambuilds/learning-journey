/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    private TreeNode build(int[] a, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(a[m]);
        root.left = build(a, l, m - 1);
        root.right = build(a, m + 1, r);
        return root;
    }
}
