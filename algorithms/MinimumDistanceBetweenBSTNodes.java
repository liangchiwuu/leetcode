package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two
 * different nodes in the tree.
 * 
 * Example :
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 * 
 *     4
 *    / \
 *   2   6
 *  / \
 * 1   3
 * 
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 * 
 * Note:
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 */
public class MinimumDistanceBetweenBSTNodes {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);

        int minDist = new MinimumDistanceBetweenBSTNodes().new Solution().minDiffInBST(root);
        System.out.println(minDist);
    }

    /**
     * Simply use preorder traversal to find all the nodes in the tree, then sort and find minimum distance between
     * adjacent elements.
     * 
     * Time complexity: O(n log n)
     */
    class Solution {
        public int minDiffInBST(TreeNode root) {
            List<Integer> vals = preorderTraversal(root);
            Collections.sort(vals);

            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < vals.size() - 1; i++) {
                minDist = Math.min(minDist, vals.get(i + 1) - vals.get(i));
            }

            return minDist;
        }

        private List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            if (root == null) {
                return result;
            }

            result.add(root.val);
            result.addAll(preorderTraversal(root.left));
            result.addAll(preorderTraversal(root.right));

            return result;
        }
    }

}
