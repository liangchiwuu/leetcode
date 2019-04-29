package algorithms;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * A single node tree is a BST
 */
public class ValidateBinarySearchTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);

        boolean result = new ValidateBinarySearchTree().new Solution().isValidBST(root);
        System.out.println(result);
    }

    /**
     * A typical divide and conquer solution. One key to note here is that you must ensure that root.right != null
     * before compare right.min with root.val. The reason is that for null node, the initial max value is
     * Integer.MAX_VALUE, which could possibly be the value of root node.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        class Result {
            boolean isBst;
            int max;
            int min;

            Result(boolean isBst, int max, int min) {
                this.isBst = isBst;
                this.max = max;
                this.min = min;
            }
        }

        public boolean isValidBST(TreeNode root) {
            return helper(root).isBst;
        }

        private Result helper(TreeNode root) {
            if (root == null) {
                return new Result(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }

            Result left = helper(root.left);
            Result right = helper(root.right);

            boolean isBst = left.isBst && right.isBst
                    && (root.right == null || right.min > root.val)
                    && (root.left == null || left.max < root.val);
            int max = Math.max(Math.max(left.max, right.max), root.val);
            int min = Math.min(Math.min(left.min, right.min), root.val);

            return new Result(isBst, max, min);
        }
    }

    /**
     * A traversal recursive solution. The idea is that the in-order traversal of a BST must be in ascending trend
     * (equal is okay). Logic is kinda tricky here.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        private int lastVal = Integer.MIN_VALUE;
        private boolean firstNode = true;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }

            if (!isValidBST(root.left)) {
                return false;
            }

            if (!firstNode && lastVal >= root.val) {
                return false;
            }

            firstNode = false;
            lastVal = root.val;

            if (!isValidBST(root.right)) {
                return false;
            }

            return true;
        }
    }

    /*
     * Another divide and conquer solution. Instead of using a 'Result' type to carry max and min, we pass the valid
     * range of the next node along. Note that we use Long instead of Integer in case the initial root.val is happened
     * to be Integer.MAX_VALUE.
     * 
     * Time complexity: O(n)
     */
    class Solution3 {
        public boolean isValidBST(TreeNode root) {
            return isBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isBst(TreeNode root, long min, long max) {
            if (root == null) {
                return true;
            }

            if (root.val <= min || root.val >= max) {
                return false;
            }

            return isBst(root.left, min, Math.min(max, root.val)) &&
                    isBst(root.right, Math.max(min, root.val), max);
        }
    }
}
