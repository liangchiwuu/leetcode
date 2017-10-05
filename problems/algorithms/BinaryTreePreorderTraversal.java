package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 *  \
 *   2
 *  /
 * 3
 * return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
     * There is a good explanation of time complexity here:
     * https://stackoverflow.com/questions/4547012/complexities-of-binary-tree-traversals
     * 
     * In-order, Pre-order, and Post-order traversals are Depth-First traversals.
     * 
     * For a Graph, the complexity of a Depth First Traversal is O(n + m), where n is the number of nodes, and m is the
     * number of edges.
     * 
     * Since a Binary Tree is also a Graph, the same applies here. The complexity of each of these Depth-first
     * traversals is O(n+m).
     * 
     * Since the number of edges that can originate from a node is limited to 2 in the case of a Binary Tree, the
     * maximum number of total edges in a Binary Tree is n-1, where n is the total number of nodes.
     * 
     * The complexity then becomes O(n + n-1), which is O(n).
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        List<Integer> result = new BinaryTreePreorderTraversal().new Solution().preorderTraversal(root);
        System.out.println(result);
    }

    /**
     * A solution using recursive & divide and conquer. Divide the original problem to 2 sub-problems (left sub-tree and
     * right sub-tree), then merge the result and return.
     *
     * Time complexity: O(n)
     */
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new LinkedList<Integer>();
            if (root == null) {
                return result;
            }

            result.add(root.val);
            result.addAll(preorderTraversal(root.left));
            result.addAll(preorderTraversal(root.right));

            return result;
        }
    }

    /**
     * A solution using DFS. Recursively traverse the tree while carrying the result.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new LinkedList<Integer>();
            traverse(root, result);
            return result;
        }

        private void traverse(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }

            result.add(root.val);
            traverse(root.left, result);
            traverse(root.right, result);
        }
    }

    /**
     * A non-recursive solution use stack.
     * 
     * Time complexity: O(n)
     */
    class Solution3 {
        public List<Integer> preorderTraversal(TreeNode root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            List<Integer> preorder = new LinkedList<Integer>();

            if (root == null) {
                return preorder;
            }

            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                preorder.add(node.val);

                // we want to pop left first, since LIFO, right goes in first
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return preorder;
        }
    }

    /**
     * TODO: Implement Morris Traversal.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution4 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> preorder = new LinkedList<Integer>();
            return preorder;
        }
    }

}