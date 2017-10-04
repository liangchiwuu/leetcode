package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *   3
 *  / \
 * 9  20
 *    / \
 *   15  7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = new BinaryTreeLevelOrderTraversal().levelOrder(root);
        System.out.println(result);
    }

    /**
     * A BFS solution. The idea is to use a dummy node to represent level change. Whenever a dummy node is polled, add a
     * new level and offer dummy back to queue. Otherwise, add node.val to last level.
     *
     * Time complexity: O(n)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(null);
        queue.offer(root);

        while (queue.size() > 1) {
            TreeNode node = queue.poll();

            // run into dummy node, move to next level
            if (node == null) {
                result.add(new ArrayList<Integer>());
                queue.offer(null);
                continue;
            }

            result.get(result.size() - 1).add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return result;
    }

    /**
     * Another BFS solution. The difference between this solution and the previous one is that instead of dummy node, we
     * first get the # of nodes in current level, then process one level at a time.
     *
     * Time complexity: O(n)
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int numOfNodes = queue.size();

            for (int i = 0; i < numOfNodes; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
        }

        return result;
    }

    /**
     * A DFS solution. The idea is, for a N-level tree, perform N times of search (from level 0 to level N-1). Each
     * search we target a specific level and add all the nodes on that level.
     * 
     * Time complexity: O(n log n)
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (root == null) {
            return results;
        }

        int targetLevel = 0;
        while (true) {
            List<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, targetLevel);
            if (level.size() == 0) {
                break;
            }
            results.add(level);
            targetLevel++;
        }

        return results;
    }

    private void dfs(TreeNode root, List<Integer> level, int curtLevel, int targetLevel) {
        if (root == null || curtLevel > targetLevel) {
            return;
        }

        if (curtLevel == targetLevel) {
            level.add(root.val);
            return;
        }

        dfs(root.left, level, curtLevel + 1, targetLevel);
        dfs(root.right, level, curtLevel + 1, targetLevel);
    }

}
