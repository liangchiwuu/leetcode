package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to
 * finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 * return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses
 * 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct
 * ordering is[0,2,1,3].
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseScheduleII {

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        int[] order = new CourseScheduleII().new Solution().findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(order));
    }

    /**
     * This problem is very similar to problem #207 Course Schedule, the only difference is that we must give an actual
     * order here. To ensure the order exists, simply find the topological sort and check if it contains all courses.
     * 
     * Although the steps to solve the problem Course Schedule & Course Schedule II are the same, I did apply some
     * refactor here:
     * 
     * 1. all operations are done in one method
     * 2. since the course numbers are continuous from 0 to n-1, an Array or List can be used instead of Map
     * 
     * That's how you can see the code becomes shorter compare to Course Schedule I.
     * 
     * Time complexity: O(V + E)
     */
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // build adjacency list and count indegrees
            List<Set<Integer>> adjList = new ArrayList<>();
            int[] indegree = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                adjList.add(new HashSet<>());
            }
            for (int[] prerequisite : prerequisites) {
                indegree[prerequisite[0]]++;
                adjList.get(prerequisite[1]).add(prerequisite[0]);
            }

            // bfs to find topological sort
            Queue<Integer> queue = new LinkedList<>();
            for (int node = 0; node < adjList.size(); node++) {
                if (indegree[node] == 0) {
                    queue.add(node);
                }
            }

            int[] order = new int[numCourses];
            int count = 0;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                order[count] = node;
                count++;
                for (int neighbor : adjList.get(node)) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            if (count == numCourses) {
                return order;
            }
            return new int[0];
        }
    }

}
