package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a directed, acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any
 * order.
 * 
 * The graph is given as follows: the nodes are 0, 1, ..., graph.length - 1. graph[i] is a list of all nodes j for which
 * the edge (i, j) exists.
 * 
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * 
 * Note:
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class AllPathsFromSourceToTarget {

    public static void main(String[] args) {
        int[][] graph = { { 1, 2 }, { 3 }, { 3 }, {} };
        List<List<Integer>> paths = new AllPathsFromSourceToTarget().new Solution().allPathsSourceTarget(graph);
        System.out.println(paths);
    }

    /**
     * A DFS recursive solution. To solve this problem, let's start with the 3 principles of recursion:
     * 
     * 1. definition: find all possible paths to destination start from current path and add to results
     * 2. breakdown: for all unvisited neighbors, add one at a time then repeat
     * 3. exit: return when destination has been reached
     * 
     * Time complexity: O(n 2^n), for 2^n possible paths and O(n) to copy each path
     */
    class Solution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            if (graph == null || graph.length == 0) {
                return new ArrayList<>();
            }

            List<List<Integer>> paths = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();

            path.add(0);
            visited.add(0);

            dfs(graph, paths, path, visited, graph.length - 1);

            return paths;
        }

        // goal: find all possible paths to destination start from current path and add to results
        private void dfs(int[][] graph, List<List<Integer>> results, List<Integer> path, Set<Integer> visited,
                int dest) {
            if (path.get(path.size() - 1) == dest) {
                results.add(new ArrayList<>(path));
                return;
            }

            for (int neighbor : graph[path.get(path.size() - 1)]) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                path.add(neighbor);
                visited.add(neighbor);
                dfs(graph, results, path, visited, dest);
                path.remove(path.size() - 1);
                visited.remove(neighbor);
            }
        }
    }

}
