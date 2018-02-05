package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 * 
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another
 * 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim
 * infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * 
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1,
 * N-1)?
 * 
 * Example 1:
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * 
 * Example 2:
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 * 
 * The final route is 0->1->2->3->4->5->16->15->14->13->12->11->10->9->8->7->6.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * 
 * Note:
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInRisingWater {

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                { 0, 1, 2, 3, 4 },
                { 24, 23, 22, 21, 5 },
                { 12, 13, 14, 15, 16 },
                { 11, 17, 18, 19, 20 },
                { 10, 9, 8, 7, 6 } };
        int time = new SwimInRisingWater().new Solution().swimInWater(grid);
        System.out.println(time);
    }

    /**
     * If you read the problem description carefully, you'll see that this problem is equivalent to: find the path which
     * contains the minimum max node. That means we can actually treat this problem as a shortest path search and apply
     * Dijkstra's algorithm to solve it. The only difference is: the cost of a route is now "max value of all nodes"
     * instead of "sum of the value of all nodes."
     * 
     * Time complexity: O(n^2 log n), since there are n^2 nodes and each heap add() takes O(log n)
     */
    class Solution {
        private int[] dx = new int[] { 1, -1, 0, 0 };
        private int[] dy = new int[] { 0, 0, 1, -1 };

        public int swimInWater(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return -1;
            }

            int n = grid.length;
            boolean[][] visited = new boolean[n][n];
            PriorityQueue<Coord> pq = new PriorityQueue<>(new Comparator<Coord>() {
                @Override
                public int compare(Coord c1, Coord c2) {
                    return c1.cost - c2.cost;
                }
            });

            visited[0][0] = true;
            pq.add(new Coord(0, 0, grid[0][0]));

            while (!pq.isEmpty()) {
                Coord c = pq.poll();

                if (c.x == n - 1 && c.y == n - 1) {
                    return c.cost;
                }

                for (int i = 0; i < 4; i++) {
                    int x = c.x + dx[i];
                    int y = c.y + dy[i];
                    if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                        visited[x][y] = true;
                        pq.add(new Coord(x, y, Math.max(grid[x][y], c.cost)));
                    }
                }
            }

            return -1;
        }

        private class Coord {
            public int x;
            public int y;
            public int cost;

            public Coord(int x, int y, int cost) {
                this.x = x;
                this.y = y;
                this.cost = cost;
            }
        }
    }

}
