package algorithms;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * 
 *      6
 *    /   \
 *   2     8
 *  / \   / \
 * 0   4 7   9
 *    / \
 *   3   5
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
 * since a node can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorOfABinarySearchTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);

        TreeNode p = root.left.left;
        TreeNode q = root.left.right;

        TreeNode result = new LowestCommonAncestorOfABinarySearchTree().new Solution().lowestCommonAncestor(root, p, q);
        if (result != null) {
            System.out.println(result.val);
        } else {
            System.out.println("No LCA.");
        }
    }

    /**
     * An recursive solution to solve this problem. The key is that in a BST, you can easily tell whether a node is in
     * left or right sub-tree by comparing with root.
     * 
     * If p/q both on right side -> LCA is on right side.
     * If p/q both on left side -> LCA on left side.
     * If p/q on different side -> LCA is root.
     * 
     * Time complexity: O(log n)
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) {
                return lowestCommonAncestor(root.left, p, q);
            }

            if (root.val < p.val && root.val < q.val) {
                return lowestCommonAncestor(root.right, p, q);
            }

            return root;
        }
    }

    /**
     * An iterative solution to solve this problem. The key is that in a BST, you can easily tell whether a node is in
     * left or right sub-tree by comparing with root.
     * 
     * 1. If p/q are on different side -> root is LCA.
     * 2. If p/q are on same side -> move root one level down and repeat.
     * 
     * Time complexity: O(log n)
     */
    class Solution2 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            while ((root.val - p.val) * (root.val - q.val) > 0) {
                root = p.val > root.val ? root.right : root.left;
            }

            return root;
        }
    }

}
