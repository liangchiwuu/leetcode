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
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][] { { 1, 0 }, { 0, 1 } };
        boolean isPossible = new CourseSchedule().new Solution().canFinish(numCourses, prerequisites);
        System.out.println(isPossible);
    }

    /**
     * A BFS solution that divides the problem into 5 steps:
     * 
     * 1. build graph (in adjacency list form) from prerequisites
     * 2. calculate indegree
     * 3. find start nodes
     * 4. get topological sorting
     * 5. check if the topological sorting contains all courses
     * 
     * Note that this solution is relatively long since every task is divided into separate functions. You can
     * easily squash the code by putting everything in the main function, but it will be less readable.
     * 
     * Time complexity: O(V + E)
     */
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, Set<Integer>> graph = buildGraph(numCourses, prerequisites);
            Map<Integer, Integer> indegree = getIndegree(graph);
            List<Integer> startNodes = getStartNodes(graph, indegree);
            List<Integer> topSort = bfs(graph, indegree, startNodes);

            return topSort.size() == numCourses;
        }

        private Map<Integer, Set<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                graph.put(i, new HashSet<>());
            }
            for (int[] prerequisite : prerequisites) {
                graph.get(prerequisite[1]).add(prerequisite[0]);
            }
            return graph;
        }

        private Map<Integer, Integer> getIndegree(Map<Integer, Set<Integer>> graph) {
            Map<Integer, Integer> indegree = new HashMap<>();
            for (int node : graph.keySet()) {
                indegree.put(node, 0);
            }
            for (int node : graph.keySet()) {
                for (int neighbor : graph.get(node)) {
                    indegree.put(neighbor, indegree.get(neighbor) + 1);

                }
            }
            return indegree;
        }

        private List<Integer> getStartNodes(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> indegree) {
            List<Integer> startNodes = new ArrayList<>();
            for (int node : graph.keySet()) {
                if (indegree.get(node) == 0) {
                    startNodes.add(node);
                }
            }
            return startNodes;
        }

        private List<Integer> bfs(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> indegree,
                List<Integer> startNodes) {
            List<Integer> topSort = new ArrayList<>(startNodes);
            Queue<Integer> queue = new LinkedList<>(startNodes);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neightbor : graph.get(node)) {
                    indegree.put(neightbor, indegree.get(neightbor) - 1);
                    if (indegree.get(neightbor) == 0) {
                        topSort.add(neightbor);
                        queue.offer(neightbor);
                    }
                }
            }
            return topSort;
        }
    }
}
