package algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 * 
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should
 * be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Example:
 *       1 → null
 *    /     \
 *   2 ----→ 3 → null
 *  / \       \
 * 4 → 5 ----→ 7 → null
 * 
 * Note:
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersInEachNodeII {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static void main(String[] args) {
        // build tree
        Node root = new Node(1, null, null, null);
        root.left = new Node(2, null, null, null);
        root.right = new Node(3, null, null, null);
        root.left.left = new Node(4, null, null, null);
        root.left.right = new Node(5, null, null, null);
        root.right.right = new Node(7, null, null, null);

        new PopulatingNextRightPointersInEachNodeII().new Solution2().connect(root);

        // level order traverse to print next pointer
        // expect: null, 3, null, 5, 7, null
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.next != null) {
                System.out.println(node.next.val);
            } else {
                System.out.println("null");
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * A level-order traverse solution. The idea is to traverse the tree level by level, and set the next pointer to the
     * node on the right during the process. Note that this solution does not satisfy the constrain of only using
     * constant extra space.
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();

                    // this is to prevent pointing to next level
                    if (i < size - 1) {
                        node.next = queue.peek();
                    }

                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            return root;
        }
    }

    /**
     * An iterative solution with constant extra space. The two keys to solve this problem are:
     * 
     * 1. keep track of the left most node of current level
     * 2. if we solve this problem level by level, we can take advantage of previous level's next pointer
     * 
     * Ref: https://www.cnblogs.com/yuzhangcmu/p/4041345.html
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution2 {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }

            Node leftEnd = root;
            while (leftEnd != null) {

                // process all the nodes in next level
                Node curr = leftEnd;
                while (curr != null) {
                    Node next = getMostLeftChild(curr.next);
                    if (curr.right != null) {
                        curr.right.next = next;
                        next = curr.right;
                    }
                    if (curr.left != null) {
                        curr.left.next = next;
                    }
                    curr = curr.next;
                }

                // move to next level
                leftEnd = getMostLeftChild(leftEnd);
            }

            return root;
        }

        public Node getMostLeftChild(Node node) {
            while (node != null) {
                if (node.left != null) {
                    return node.left;
                }
                if (node.right != null) {
                    return node.right;
                }
                node = node.next;
            }
            return null;
        }
    }

}
