package algorithms;

import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example,
 * Given
 * 
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTreeToLinkedList {

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
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        new FlattenBinaryTreeToLinkedList().new Solution().flatten(root);
        TreeNode curr = root;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.right;
        }
    }

    /**
     * A solution using divide & conquer. Divide the original problem to 2 sub-problems (left sub-tree and right
     * sub-tree), then merge the result and return. The key here is to flatten left and right subtree. In order to
     * append right subtree after left subtree, we need to keep tracking the last node of the result.
     *
     * Time complexity: O(n)
     */
    class Solution {
        public void flatten(TreeNode root) {
            helper(root);
        }

        private TreeNode helper(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeNode leftLast = helper(root.left);
            TreeNode rightLast = helper(root.right);

            // divide and conquer
            if (leftLast != null) {
                leftLast.right = root.right;
                root.right = root.left;
                root.left = null;
            }

            // return 'current last' node
            if (rightLast != null) {
                return rightLast;
            }
            if (leftLast != null) {
                return leftLast;
            }
            return root;
        }
    }

    /**
     * A recursive solution. The key is to make a copy of the root of right subtree, since the left subtree will switch
     * to right after flatten. We also need to keep track of the last node. Whenever the last node exists, the current
     * node should be at the right side of it.
     * 
     * !!! Draw it out to feel the logic !!!
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        private TreeNode lastNode = null;

        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            if (lastNode != null) {
                lastNode.left = null;
                lastNode.right = root;
            }

            lastNode = root;
            TreeNode right = root.right;
            flatten(root.left);
            flatten(right);
        }
    }

    /**
     * An iterative solution. Basically preorder traverse a tree and changing the connections on the way.
     * 
     * Time complexity: O(n)
     */
    class Solution3 {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }

            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }

                // connect
                node.left = null;
                if (stack.empty()) {
                    node.right = null;
                } else {
                    node.right = stack.peek();
                }
            }
        }
    }

}
