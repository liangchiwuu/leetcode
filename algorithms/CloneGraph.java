package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * 
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 * 
 *    1
 *   / \
 *  /   \
 * 0 --- 2
 *      / \
 *      \_/
 */
public class CloneGraph {

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };

    public static void main(String[] args) {
        UndirectedGraphNode node = new UndirectedGraphNode(1);
        UndirectedGraphNode result = new CloneGraph().new Solution().cloneGraph(node);
        System.out.println(result.label);
    }

    /**
     * A BFS solution, the key here is to break down the problem into 3 steps:
     * 
     * 1. find all nodes
     * 2. clone all nodes
     * 3. clone all edges
     * 
     * Once these sub-problems are defined, it is easy to implement them one by one.
     * 
     * Time complexity: O(V + E) for V vertices and E edges
     */
    public class Solution {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) {
                return null;
            }

            List<UndirectedGraphNode> nodes = getAllNodes(node);
            Map<UndirectedGraphNode, UndirectedGraphNode> mapping = cloneNodes(nodes);
            cloneEdges(nodes, mapping);

            return mapping.get(node);
        }

        private List<UndirectedGraphNode> getAllNodes(UndirectedGraphNode node) {
            Queue<UndirectedGraphNode> queue = new LinkedList<>();
            Set<UndirectedGraphNode> set = new HashSet<>();

            queue.offer(node);
            set.add(node);
            while (!queue.isEmpty()) {
                UndirectedGraphNode n = queue.poll();
                for (UndirectedGraphNode neighbor : n.neighbors) {
                    if (!set.contains(neighbor)) {
                        queue.offer(neighbor);
                        set.add(neighbor);
                    }
                }
            }

            return new ArrayList<UndirectedGraphNode>(set);
        }

        private Map<UndirectedGraphNode, UndirectedGraphNode> cloneNodes(List<UndirectedGraphNode> nodes) {
            Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
            for (UndirectedGraphNode node : nodes) {
                mapping.put(node, new UndirectedGraphNode(node.label));
            }
            return mapping;
        }

        private void cloneEdges(List<UndirectedGraphNode> nodes,
                Map<UndirectedGraphNode, UndirectedGraphNode> mapping) {
            for (UndirectedGraphNode node : nodes) {
                for (UndirectedGraphNode neighbor : node.neighbors) {
                    mapping.get(node).neighbors.add(mapping.get(neighbor));
                }
            }
        }
    }

    /**
     * A recursive DFS solution.
     * 
     * Time complexity: O(V + E) for V vertices and E edges
     */
    public class Solution2 {
        private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) {
                return null;
            }

            if (map.containsKey(node.label)) {
                return map.get(node.label);
            }

            UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
            map.put(clone.label, clone);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                clone.neighbors.add(cloneGraph(neighbor));
            }

            return clone;
        }
    }

}
