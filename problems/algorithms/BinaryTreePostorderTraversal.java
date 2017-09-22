package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [3,2,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

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

        List<Integer> result = new BinaryTreePostorderTraversal().postorderTraversal(root);
        System.out.println(result);
    }

    /**
     * A solution using recursive & divide and conquer. Divide the original problem to 2 sub-problems (left sub-tree and
     * right sub-tree), then merge the result and return.
     *
     * Time complexity: O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        if (root == null) {
            return result;
        }

        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.val);

        return result;
    }

    /**
     * A solution using DFS. Recursively traverse the tree while carrying the result.
     * 
     * Time complexity: O(n)
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        traverse(root.left, result);
        traverse(root.right, result);
        result.add(root.val);
    }

    /**
     * A non-recursive solution based on preorder traversal. Consider:
     * preorder -> root, left, right
     * postorder -> left, right, root
     * 
     * You can see that if you switch the 'left' and 'right' in preorder, what you get is a reverse postorder. Then we
     * just have to output reversely.
     * 
     * (This is neither really efficient, nor space friendly. Just a very interesting way of solving it.)
     * 
     * Time complexity: O(n)
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> preorder = new LinkedList<Integer>();
        List<Integer> postorder = new LinkedList<Integer>();

        if (root == null) {
            return postorder;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        for (int node : preorder) {
            postorder.add(0, node);
        }

        return postorder;
    }

    /**
     * A non-recursive solution use stack. (The logic...)
     * 
     * Time complexity: O(n)
     */
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // previously traversed node
        TreeNode prev = null;
        TreeNode curr = root;

        if (root == null) {
            return result;
        }

        stack.push(root);
        while (!stack.empty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                // traverse down the tree
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                // traverse up the tree from the left
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else {
                // traverse up the tree from the right
                result.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }

        return result;
    }

}
