package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree [1,null,2,3],
 * 1
 *  \
 *   2
 *  /
 * 3
 * return [1,3,2].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

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

        List<Integer> result = new BinaryTreeInorderTraversal().inorderTraversal(root);
        System.out.println(result);
    }

    /**
     * A solution using recursive & divide and conquer. Divide the original problem to 2 sub-problems (left sub-tree and
     * right sub-tree), then merge the result and return.
     *
     * Time complexity: O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        if (root == null) {
            return result;
        }

        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));

        return result;
    }

    /**
     * A solution using DFS. Recursively traverse the tree while carrying the result.
     * 
     * Time complexity: O(n)
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        traverse(root.left, result);
        result.add(root.val);
        traverse(root.right, result);
    }

    /**
     * A non-recursive solution use stack.
     * 
     * Time complexity: O(n)
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> result = new LinkedList<Integer>();
        TreeNode current = root;

        while (current != null || !stack.empty()) {
            // find the bottom left node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }
}
