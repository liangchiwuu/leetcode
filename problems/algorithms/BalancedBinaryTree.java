package algorithms;

/*
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of
 * every node never differ by more than 1.
 */
public class BalancedBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        boolean result = new BalancedBinaryTree().isBalanced(root);
        System.out.println(result);
    }

    /**
     * A divide and conquer solution. The key here is that, since any sub-tree has to be balanced as well, we can verify
     * this in the process of getting max depth. Here we use 'depth = -1' to represent that a binary tree is NOT
     * balanced. So, a binary tree is 'unbalanced' if the left sub-tree is unbalanced OR the right sub-tree is
     * unbalanced OR the depth difference between left/right sub-tree is greater than 1.
     * 
     * This is actually not the vest solution although it's shorter.
     * 1. the function maxDepth() returns something that is 'not depth'
     * 2. use -1 to represent unbalanced is kind of tricky
     * To declare a custom small class is much more readable.
     * 
     * Time complexity: O(n)
     */
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != UNBALANCED;
    }

    final int UNBALANCED = -1;

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        if (leftDepth == UNBALANCED || rightDepth == UNBALANCED || Math.abs(leftDepth - rightDepth) > 1) {
            return UNBALANCED;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Another approach of divide and conquer. The key is to use a custom 'Result' class to track both the depth and
     * balanced or not of tree.
     * 
     * Time complexity: O(n)
     */
    public boolean isBalanced2(TreeNode root) {
        return helper(root).isBalanced;
    }

    class Result {
        boolean isBalanced;
        int depth;

        Result(boolean isBalanced, int depth) {
            this.isBalanced = isBalanced;
            this.depth = depth;
        }
    }

    private Result helper(TreeNode root) {
        if (root == null) {
            return new Result(true, 0);
        }

        Result left = helper(root.left);
        Result right = helper(root.right);

        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.depth - right.depth) <= 1;
        int depth = Math.max(left.depth, right.depth) + 1;

        return new Result(isBalanced, depth);
    }

}
