package algorithms;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”*
 * 
 *      3
 *    /   \
 *   5     1
 *  / \   / \
 * 6   2 0   8
 *    / \
 *   7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorOfABinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
     * It is relatively complicate to solve this problem with traversal. Just in case you're interested, search for
     * "Tarjan's algorithm."
     */

    /*
     * This problem will become very easy if the TreeNode class has additional 'parent' attribute. If the parent node is
     * accessible, we just need to first find the path to the 2 target nodes from root, then find the first different
     * node in top-down order.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(1);

        TreeNode p = root.left.left;
        TreeNode q = root.left.right;

        TreeNode result = new LowestCommonAncestorOfABinaryTree().new Solution().lowestCommonAncestor(root, p, q);
        if (result != null) {
            System.out.println(result.val);
        } else {
            System.out.println("No LCA.");
        }
    }

    /**
     * An divide and conquer solution. Since this is not a BST, there is no way to tell whether a node is in left or
     * right sub-tree just by comparing with root. The idea is to separately search p/q in left and right sub-tree.
     * 
     * 1. If found p/q on both side -> return root as LCA.
     * 2. If p/q only exist on one side -> return p/q.
     * 3. If p/q does not exist in either -> return null.
     * 
     * In other words:
     * 
     * p & q -> LCA
     * p & !q -> p
     * !p & q -> q
     * !p & !q -> null
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, q, p);
            TreeNode right = lowestCommonAncestor(root.right, q, p);

            // p and q are in different sub-tree -> root is LCA
            if (left != null && right != null) {
                return root;
            }

            // only found p/q in one side
            if (left != null) {
                return left;
            }
            if (right != null) {
                return right;
            }

            // neither left nor right sub-tree contains p/q
            return null;
        }
    }

    /**
     * This solution considers one more constrain: "return null if LCA does not exist." In order to ensure that LCA does
     * exist, both q/p must present in the tree. Here we use a custom class to carry whether q/p exists or not.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        class Result {
            boolean pExist;
            boolean qExist;
            TreeNode node;

            Result(boolean pExist, boolean qExist, TreeNode node) {
                this.pExist = pExist;
                this.qExist = qExist;
                this.node = node;
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            Result result = helper(root, p, q);
            if (result.pExist && result.qExist) {
                return result.node;
            } else {
                return null;
            }
        }

        private Result helper(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return new Result(false, false, null);
            }

            Result left = helper(root.left, p, q);
            Result right = helper(root.right, p, q);

            boolean pExist = left.pExist || right.pExist || root == p;
            boolean qExist = left.qExist || right.qExist || root == q;

            if (root == p || root == q) {
                return new Result(pExist, qExist, root);
            }
            if (left.node != null && right.node != null) {
                return new Result(pExist, qExist, root);
            }
            if (left.node != null) {
                return new Result(pExist, qExist, left.node);
            }
            if (right.node != null) {
                return new Result(pExist, qExist, right.node);
            }

            return new Result(pExist, qExist, null);
        }
    }

}
