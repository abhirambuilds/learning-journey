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
    public boolean isBalanced(TreeNode root) {
        // If the helper function returns -1, the tree is unbalanced
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode root) {
        // Base case: An empty tree has a height of 0
        if (root == null) {
            return 0;
        }

        // Recursively check the height of the left subtree
        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1; // Early exit: left subtree is already unbalanced
        }

        // Recursively check the height of the right subtree
        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1; // Early exit: right subtree is already unbalanced
        }

        // If the current node is unbalanced, return -1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Otherwise, return the true height of the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }
}