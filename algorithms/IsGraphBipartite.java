package algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a graph, return true if and only if it is bipartite.
 * 
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that
 * every edge in the graph has one node in A and another node in B.
 * 
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j
 * exists. Each node is an integer between 0 and graph.length - 1. There are no self edges or parallel edges: graph[i]
 * does not contain i, and it doesn't contain any element twice.
 * 
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * 
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 * 
 * Note:
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 */
public class IsGraphBipartite {

    public static void main(String[] args) {
        int[][] graph = { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } };
        boolean isBipartite = new IsGraphBipartite().new Solution().isBipartite(graph);
        System.out.println(isBipartite);
    }

    /**
     * A BFS solution. We can check if a graph is bipartite as follow:
     * 
     * 0. a node can be colored either red or blue
     * 1. first mark origin as blue, then mark all the neighbors around origin as red
     * 2. continue using BFS to traverse and mark all nodes
     * 3. if any adjacent nodes are in same color, the graph is not bipartite
     * 
     * The key here is that this graph might be disconnected, so we need to check if all components are bipartite.
     * 
     * Time complexity: O(N + E), where N is the # of nodes and E is the # of edges
     */
    class Solution {
        private static final int UNKNOWN = 0;
        private static final int BLUE = 1;
        private static final int RED = 2;

        public boolean isBipartite(int[][] graph) {
            int[] color = new int[graph.length];
            Arrays.fill(color, UNKNOWN);

            // graph might be disconnected, need to check each unvisited node
            for (int i = 0; i < graph.length; i++) {
                if (color[i] == UNKNOWN && !bfs(graph, i, color)) {
                    return false;
                }
            }

            return true;
        }

        // check if a component is bipartite
        private boolean bfs(int[][] graph, int origin, int[] color) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(origin);
            color[origin] = BLUE;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : graph[node]) {
                    if (color[neighbor] == color[node]) {
                        return false;
                    }
                    if (color[neighbor] == UNKNOWN) {
                        queue.offer(neighbor);
                        color[neighbor] = color[node] == RED ? BLUE : RED;
                    }
                }
            }

            return true;
        }
    }

    /**
     * A DFS solution using stack.
     * 
     * Time complexity: O(N + E), where N is the # of nodes and E is the # of edges
     */
    class Solution2 {
        private static final int UNKNOWN = 0;
        private static final int BLUE = 1;
        private static final int RED = 2;

        public boolean isBipartite(int[][] graph) {
            int[] color = new int[graph.length];
            Arrays.fill(color, UNKNOWN);

            // graph might be disconnected, need to check each unvisited node
            for (int origin = 0; origin < graph.length; origin++) {
                if (color[origin] == UNKNOWN) {
                    // use dfs to traverse this component
                    Stack<Integer> stack = new Stack<>();
                    stack.push(origin);
                    color[origin] = BLUE;

                    while (!stack.isEmpty()) {
                        int node = stack.pop();
                        for (int neighbor : graph[node]) {
                            if (color[neighbor] == color[node]) {
                                return false;
                            }
                            if (color[neighbor] == UNKNOWN) {
                                stack.push(neighbor);
                                color[neighbor] = color[node] == RED ? BLUE : RED;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    /**
     * A DFS solution using recursion.
     * 
     * Time complexity: O(N + E), where N is the # of nodes and E is the # of edges
     */
    class Solution3 {
        private static final int UNKNOWN = 0;
        private static final int BLUE = 1;
        private static final int RED = 2;

        public boolean isBipartite(int[][] graph) {
            int[] color = new int[graph.length];
            Arrays.fill(color, UNKNOWN);

            // graph might be disconnected, need to check each unvisited node
            for (int i = 0; i < graph.length; i++) {
                if (color[i] == UNKNOWN) {
                    color[i] = BLUE;
                    if (!dfs(graph, i, color)) {
                        return false;
                    }
                }
            }

            return true;
        }

        // check if a component is bipartite
        private boolean dfs(int[][] graph, int current, int[] color) {
            for (int neighbor : graph[current]) {
                if (color[neighbor] == color[current]) {
                    return false;
                }
                if (color[neighbor] == UNKNOWN) {
                    color[neighbor] = color[current] == RED ? BLUE : RED;
                    if (!dfs(graph, neighbor, color)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
