package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 * 
 *   1
 *  / \
 * 2   3
 *    / \
 *   4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

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
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = new SerializeAndDeserializeBinaryTree().new Codec().serialize(root);
        System.out.println(serialized);
    }

    /**
     * A BFS solution. We use level-order to traverse the tree while serializing.
     * 
     * Time complexity: O(n)
     */
    public class Codec {
        private static final String DELIMITER = ",";
        private static final String EMPTY = "#";

        public String serialize(TreeNode root) {
            if (root == null) {
                return "{}";
            }

            ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
            queue.add(root);

            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.get(i);
                if (node == null) {
                    continue;
                }
                queue.add(node.left);
                queue.add(node.right);
            }

            while (queue.get(queue.size() - 1) == null) {
                queue.remove(queue.size() - 1);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append(queue.get(0).val);
            for (int i = 1; i < queue.size(); i++) {
                sb.append(DELIMITER);
                sb.append(queue.get(i) == null ? EMPTY : queue.get(i).val);
            }
            sb.append("}");

            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            if (data.equals("{}")) {
                return null;
            }

            String[] vals = data.substring(1, data.length() - 1).split(DELIMITER);
            ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
            queue.add(root);

            int index = 0;
            boolean isLeftChild = true;
            for (int i = 1; i < vals.length; i++) {
                if (!vals[i].equals(EMPTY)) {
                    TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                    if (isLeftChild) {
                        queue.get(index).left = node;
                    } else {
                        queue.get(index).right = node;
                    }
                    queue.add(node);
                }

                if (!isLeftChild) {
                    // when !isLeftChild, parent is complete
                    index++;
                }

                // since this tree is (except last leaf) full, we always have left and right
                isLeftChild = !isLeftChild;
            }

            return root;
        }
    }

    /**
     * A DFS solution. Note that the serialize order here is different from previous one. The serialized result here is
     * in pre-order format. Also note that the serialized string will always be a full binary tree (every node has 2
     * child except leaves) with empty nodes.
     * 
     * Time complexity: O(n)
     */
    public class Codec2 {
        private static final String DELIMITER = ",";
        private static final String EMPTY = "#";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(EMPTY);
                sb.append(DELIMITER);
            } else {
                sb.append(node.val);
                sb.append(DELIMITER);

                buildString(node.left, sb);
                buildString(node.right, sb);
            }
        }

        public TreeNode deserialize(String data) {
            Queue<String> nodes = new LinkedList<>();
            nodes.addAll(Arrays.asList(data.split(DELIMITER)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Queue<String> nodes) {
            String val = nodes.poll();
            if (val.equals(EMPTY)) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }

}
