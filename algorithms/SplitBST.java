package algorithms;

/**
 * Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where
 * one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that
 * are greater than the target value. It's not necessarily the case that the tree contains a node with value V.
 * 
 * Additionally, most of the structure of the original tree should remain. Formally, for any child C with parent P in
 * the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.
 * 
 * You should output the root TreeNode of both subtrees after splitting, in any order.
 * 
 * Example 1:
 * 
 * Input: root = [4,2,6,1,3,5,7], V = 2
 * Output: [[2,1],[4,3,6,null,null,5,7]]
 * Explanation:
 * Note that root, output[0], and output[1] are TreeNode objects, not arrays.
 * 
 * The given tree [4,2,6,1,3,5,7] is represented by the following diagram:
 * 
 *      4
 *    /   \
 *   2     6
 *  / \   / \
 * 1   3 5   7
 * 
 * while the diagrams for the outputs are:
 * 
 *      4
 *    /   \
 *   3     6      and    2
 *        / \           /
 *       5   7         1
 *       
 * Note:
 * The size of the BST will not exceed 50.
 * The BST is always valid and each node's value is different.
 */
public class SplitBST {

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
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        TreeNode[] results = new SplitBST().new Solution().splitBST(root, 2);
        for (TreeNode n : results) {
            System.out.println(n.val);
        }
    }

    /**
     * An iterative solution.
     * 
     * Time complexity: O(log n)
     */
    class Solution {
        public TreeNode[] splitBST(TreeNode root, int v) {
            if (root == null) {
                return new TreeNode[] { null, null };
            }

            if (root.val > v) {

                // find where to cut
                TreeNode current = root;
                TreeNode next = root.left;
                while (next != null && next.val > v) {
                    current = next;
                    next = next.left;
                }

                // special case: nothing to cut
                if (next == null) {
                    return new TreeNode[] { root, null };
                }

                // cut between current & next
                if (next.right != null && next.right.val > v) {
                    current.left = next.right;
                    next.right = null;
                } else {
                    current.left = null;
                }

                return new TreeNode[] { root, next };

            } else {

                // find where to cut
                TreeNode current = root;
                TreeNode next = root.right;
                while (next != null && next.val <= v) {
                    current = next;
                    next = next.right;
                }

                // special case: nothing to cut
                if (next == null) {
                    return new TreeNode[] { root, null };
                }

                // cut between current & next
                if (next.left != null && next.left.val <= v) {
                    current.right = next.left;
                    next.left = null;
                } else {
                    current.right = null;
                }

                return new TreeNode[] { root, next };

            }
        }
    }

    /**
     * A recursive solution
     * 
     * Time complexity: O(log n)
     */
    class Solution2 {
        public TreeNode[] splitBST(TreeNode root, int v) {
            if (root == null) {
                return new TreeNode[] { null, null };
            }

            if (root.val <= v) {

                TreeNode[] res = splitBST(root.right, v);
                root.right = res[0];
                res[0] = root;
                return res;

            } else {

                TreeNode[] res = splitBST(root.left, v);
                root.left = res[1];
                res[1] = root;
                return res;

            }
        }
    }
}
