package algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 * 
 * For example:
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */
public class GraphValidTree {

    /*
     * In order to solve this problem, we must first give the definition of a tree. In graph theory, a tree is basically
     * an acyclic connected graph. Which implies 2 things when graph G is a tree:
     * 
     * 1. G is connected
     * 2. G has n-1 edges
     * 
     * or
     * 
     * 1. G has no simple cycles
     * 2. G has n01 edges
     * 
     * https://en.wikipedia.org/wiki/Tree_(graph_theory)
     */

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } };
        boolean isTree = new GraphValidTree().new Solution().validTree(n, edges);
        System.out.println(isTree);
    }

    /**
     * A BFS solution. The idea is to first check the # of edges, then do a BFS from node 0 (root). If all the nodes are
     * accessible from node 0, which implies the graph is connected, then this is a valid tree.
     * 
     * Time complexity: O(V * E), when there are V vertices and E edges
     */
    public class Solution {
        public boolean validTree(int n, int[][] edges) {
            // check if there are n-1 edges
            if (n == 0 || edges.length != n - 1) {
                return false;
            }

            Map<Integer, Set<Integer>> adjList = buildAdjacencyList(n, edges);
            Queue<Integer> queue = new LinkedList<Integer>();
            Set<Integer> visited = new HashSet<Integer>();

            // using BFS to travel every possible node from node 0
            queue.offer(0);
            visited.add(0);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : adjList.get(node)) {
                    if (visited.contains(neighbor)) {
                        continue;
                    }
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }

            // check that if every node can be reached from node 0 (connected or not)
            return (visited.size() == n);
        }

        private Map<Integer, Set<Integer>> buildAdjacencyList(int n, int[][] edges) {
            Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new HashSet<Integer>());
            }
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            return graph;
        }
    }

    /**
     * A DFS solution. The idea is basically the same as BFS. One optimization we use here (not necessarily) is to check
     * cycles while performing DFS. If we happened to find a cycle, then we can return the result right away.
     * 
     * Time complexity: O(V * E), when there are V vertices and E edges
     */
    public class Solution2 {
        public boolean validTree(int n, int[][] edges) {
            if (n == 0 || edges.length != n - 1) {
                return false;
            }

            Map<Integer, Set<Integer>> adjList = buildAdjacencyList(n, edges);
            Set<Integer> visited = new HashSet<Integer>();

            if (hasCycle(adjList, 0, visited, -1)) {
                return false;
            }

            return (visited.size() == n);
        }

        private Map<Integer, Set<Integer>> buildAdjacencyList(int n, int[][] edges) {
            Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new HashSet<Integer>());
            }
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            return graph;
        }

        // check if an undirected graph has cycle started from vertex u
        private boolean hasCycle(Map<Integer, Set<Integer>> adjList, int u, Set<Integer> visited, int parent) {
            visited.add(u);
            for (int v : adjList.get(u)) {
                if (visited.contains(v) && parent != v) {
                    return true;
                }
                if (!visited.contains(v) && hasCycle(adjList, v, visited, u)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * A simple Union-Find solution. Note that this easy version of Union-Find uses an array of size n to represent
     * parents, so it will only work when the nodes are continuous.
     * 
     * Time complexity: O(V * E), when there are V vertices and E edges
     */
    public class Solution3 {
        public boolean validTree(int n, int[][] edges) {
            if (n == 0 || edges.length != n - 1) {
                return false;
            }

            return !hasCycle(n, edges);
        }

        private boolean hasCycle(int n, int[][] edges) {
            int[] parents = new int[n];
            Arrays.fill(parents, -1);

            for (int[] edge : edges) {
                int x = find(parents, edge[0]);
                int y = find(parents, edge[1]);

                if (x == y) {
                    return true;
                }

                // union
                parents[x] = y;
            }

            return false;
        }

        private int find(int[] parents, int i) {
            if (parents[i] == -1) {
                return i;
            }
            return find(parents, parents[i]);
        }
    }

    /**
     * A complete Union-Find solution. This solution differs from the previous one in that this uses a more complete
     * UnionFind class which is capable of handle non-continuous nodes. Also, the compress step in compressedFind()
     * method is not necessary, but acts as an optimization to improve performance.
     * 
     * Time complexity: O(V * E), when there are V vertices and E edges
     */
    public class Solution4 {
        class UnionFind {
            Map<Integer, Integer> father = new HashMap<Integer, Integer>();

            UnionFind(int n) {
                for (int i = 0; i < n; i++) {
                    father.put(i, i);
                }
            }

            int compressedFind(int x) {
                int parent = father.get(x);
                while (parent != father.get(parent)) {
                    parent = father.get(parent);
                }

                int temp = -1;
                int fa = father.get(x);
                while (fa != father.get(fa)) {
                    temp = father.get(fa);
                    father.put(fa, parent);
                    fa = temp;
                }

                return parent;
            }

            void union(int x, int y) {
                int xFather = compressedFind(x);
                int yFather = compressedFind(y);
                if (xFather != yFather) {
                    father.put(xFather, yFather);
                }
            }
        }

        public boolean validTree(int n, int[][] edges) {
            if (n == 0 || edges.length != n - 1) {
                return false;
            }

            UnionFind uf = new UnionFind(n);
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                if (uf.compressedFind(u) == uf.compressedFind(v)) {
                    return false;
                }
                uf.union(u, v);
            }

            return true;
        }
    }

}
