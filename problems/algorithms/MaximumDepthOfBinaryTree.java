package algorithms;

import java.util.Stack;

/**
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaximumDepthOfBinaryTree {

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

        int result = new MaximumDepthOfBinaryTree().maxDepth(root);
        System.out.println(result);
    }

    /**
     * A solution using recursive & divide and conquer. Divide the original problem to 2 sub-problems (left sub-tree and
     * right sub-tree), then merge the result and return.
     *
     * Time complexity: O(n)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * A recursive solution. Recursively travels the tree and compare with the global variable.
     * 
     * Time complexity: O(n)
     */
    public int maxDepth2(TreeNode root) {
        maxDepth = 0;
        helper(root, 1);

        return maxDepth;
    }

    private int maxDepth;

    private void helper(TreeNode node, int currentDepth) {
        if (node == null) {
            return;
        }

        maxDepth = Math.max(maxDepth, currentDepth);
        helper(node.left, currentDepth + 1);
        helper(node.right, currentDepth + 1);
    }

    /**
     * An iterative solution. Preorder traverse the tree and record max value.
     * 
     * Time complexity: O(n)
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<Box> stack = new Stack<Box>();
        int maxDepth = 1;

        stack.push(new Box(root, 1));
        while (!stack.isEmpty()) {
            Box box = stack.pop();
            maxDepth = Math.max(maxDepth, box.depth);

            if (box.node.right != null) {
                stack.push(new Box(box.node.right, box.depth + 1));
            }
            if (box.node.left != null) {
                stack.push(new Box(box.node.left, box.depth + 1));
            }
        }

        return maxDepth;
    }

    static class Box {
        TreeNode node;
        int depth;

        Box(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

}
