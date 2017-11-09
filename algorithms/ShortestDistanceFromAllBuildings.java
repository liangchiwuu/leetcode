package algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can
 * only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * 
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So
 * return 7.
 * 
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return
 * -1.
 */
public class ShortestDistanceFromAllBuildings {

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
        int distance = new ShortestDistanceFromAllBuildings().new Solution().shortestDistance(grid);
        System.out.println(distance);
    }

    /**
     * Simply loop through every point in grid, for possible locations to build the house (empty land), use BFS with
     * layers to calculate the sum of distances to all buildings.
     * 
     * Time complexity: O(m^2 * n^2)
     */
    public class Solution {
        private static final int EMPTY = 0;
        private static final int BUILDING = 1;
        private static final int OBSTACLE = 2;

        private int[] dx = { 0, 0, -1, 1 };
        private int[] dy = { -1, 1, 0, 0 };

        class Coordinate {
            public int x;
            public int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int shortestDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            int numOfBuildings = 0;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (grid[x][y] == BUILDING) {
                        numOfBuildings++;
                    }
                }
            }

            int shortestDistance = Integer.MAX_VALUE;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    int distanceSum = bfs(grid, numOfBuildings, new Coordinate(x, y));
                    if (distanceSum == -1) {
                        continue;
                    }
                    shortestDistance = Math.min(shortestDistance, distanceSum);
                }
            }

            return shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance;
        }

        // get the sum of distances to each building if we build a house here
        private int bfs(int[][] grid, int numOfBuildings, Coordinate start) {
            if (grid[start.x][start.y] == BUILDING || grid[start.x][start.y] == OBSTACLE) {
                return -1;
            }

            boolean[][] visited = new boolean[grid.length][grid[0].length];
            Queue<Coordinate> queue = new LinkedList<>();
            queue.offer(start);
            visited[start.x][start.y] = true;

            int findings = 0;
            int distance = 0;
            int distanceSum = 0;
            while (!queue.isEmpty()) {
                distance++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Coordinate coord = queue.poll();
                    for (int direction = 0; direction < 4; direction++) {
                        Coordinate adj = new Coordinate(coord.x + dx[direction], coord.y + dy[direction]);
                        if (!inBound(grid, adj) || visited[adj.x][adj.y]) {
                            continue;
                        }
                        if (grid[adj.x][adj.y] == BUILDING) {
                            findings++;
                            distanceSum += distance;
                        }
                        if (grid[adj.x][adj.y] == EMPTY) {
                            queue.offer(adj);
                        }
                        visited[adj.x][adj.y] = true;
                    }
                }

            }

            // check if all buildings are reachable
            if (findings != numOfBuildings) {
                return -1;
            }
            return distanceSum;
        }

        private boolean inBound(int[][] grid, Coordinate coord) {
            if (coord.x < 0 || coord.x >= grid.length) {
                return false;
            }
            if (coord.y < 0 || coord.y >= grid[0].length) {
                return false;
            }
            return true;
        }
    }

    /**
     * Instead of looping through all empty lands, this approach loop through every buildings and accumulate sum of
     * distances to each empty land. Then we can loop through the matrix again to find the minimum distance sum. This
     * solution is faster than the previous one when the # of buildings are less than the # of empty lands (and vice
     * versa).
     * 
     * Time complexity: O(m^2 * n^2)
     */
    public class Solution2 {
        private static final int EMPTY = 0;
        private static final int BUILDING = 1;
        @SuppressWarnings("unused")
        private static final int OBSTACLE = 2;

        private int[] dx = { 0, 0, -1, 1 };
        private int[] dy = { -1, 1, 0, 0 };

        class Coordinate {
            public int x;
            public int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int shortestDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            int row = grid.length;
            int col = grid[0].length;

            int[][] visitCount = new int[row][col];
            int[][] distanceSum = new int[row][col];
            int numOfBuildings = 0;

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (grid[r][c] == BUILDING) {
                        numOfBuildings++;

                        // use BFS to accumulate the distance to each empty land from this house
                        boolean[][] visited = new boolean[row][col];
                        Queue<Coordinate> queue = new LinkedList<>();
                        queue.offer(new Coordinate(r, c));
                        int distance = 0;
                        while (!queue.isEmpty()) {
                            int size = queue.size();
                            for (int i = 0; i < size; i++) {
                                Coordinate coord = queue.poll();
                                distanceSum[coord.x][coord.y] += distance;
                                visitCount[coord.x][coord.y]++;
                                for (int direction = 0; direction < 4; direction++) {
                                    Coordinate adj = new Coordinate(coord.x + dx[direction], coord.y + dy[direction]);
                                    if (!inBound(grid, adj) || visited[adj.x][adj.y]) {
                                        continue;
                                    }
                                    if (grid[adj.x][adj.y] == EMPTY) {
                                        queue.offer(adj);
                                    }
                                    visited[adj.x][adj.y] = true;
                                }
                            }
                            distance++;
                        }

                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (grid[r][c] == EMPTY && visitCount[r][c] == numOfBuildings) {
                        result = Math.min(result, distanceSum[r][c]);
                    }
                }
            }

            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private boolean inBound(int[][] grid, Coordinate coord) {
            if (coord.x < 0 || coord.x >= grid.length) {
                return false;
            }
            if (coord.y < 0 || coord.y >= grid[0].length) {
                return false;
            }
            return true;
        }
    }

}
