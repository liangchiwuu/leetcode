package algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 * 
 * Example 1:
 * 
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * 
 * Example 2:
 * 
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = { { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };
        int result = new NumberOfIslands().new Solution().numIslands(grid);
        System.out.println(result);
    }

    /**
     * A solution using BFS. The idea is to loop through each coordinate and mark the whole island with BFS when
     * encounter a land. One thing to note is that this solution DO alter the input. A better practice is to make a deep
     * copy of the grid.
     * 
     * Time complexity: O(m * n)
     */
    class Solution {
        class Coordinate {
            int x, y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        static final char LAND = '1';
        static final char WATER = '0';
        int numOfIslands;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int x = grid.length;
            int y = grid[0].length;
            numOfIslands = 0;

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (grid[i][j] == LAND) {
                        numOfIslands++;
                        sinkLand(grid, i, j);
                    }
                }
            }

            return numOfIslands;
        }

        private void sinkLand(char[][] grid, int x, int y) {
            Queue<Coordinate> queue = new LinkedList<Coordinate>();
            queue.offer(new Coordinate(x, y));

            int[] directionX = { 0, 1, -1, 0 };
            int[] directionY = { 1, 0, 0, -1 };

            while (!queue.isEmpty()) {
                Coordinate coord = queue.poll();
                grid[coord.x][coord.y] = WATER;

                for (int i = 0; i < 4; i++) {
                    Coordinate adj = new Coordinate(coord.x + directionX[i], coord.y + directionY[i]);
                    if (inBound(grid, adj) && grid[adj.x][adj.y] == LAND) {
                        queue.offer(adj);
                    }
                }
            }
        }

        private boolean inBound(char[][] grid, Coordinate coord) {
            int x = grid.length;
            int y = grid[0].length;
            return coord.x >= 0 && coord.x < x && coord.y >= 0 && coord.y < y;
        }
    }

    /**
     * A DFS solution. It's basically the same idea as BFS solution, just we sink the lands using DFS. Note that the
     * space taken here might be high if the island is huge. BFS is generally a better approach.
     * 
     * Time complexity: O(m * n)
     */
    public class Solution2 {
        static final char LAND = '1';
        static final char WATER = '0';

        private int m;
        private int n;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            m = grid.length;
            n = grid[0].length;
            int numOfIslands = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == LAND) {
                        numOfIslands++;
                        sinkLand(grid, i, j);
                    }
                }
            }

            return numOfIslands;
        }

        public void sinkLand(char[][] grid, int i, int j) {
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return;
            }

            if (grid[i][j] == LAND) {
                grid[i][j] = WATER;
                sinkLand(grid, i - 1, j);
                sinkLand(grid, i + 1, j);
                sinkLand(grid, i, j - 1);
                sinkLand(grid, i, j + 1);
            }
        }
    }

    /**
     * A solution using Union-Find.
     * 
     * Time complexity: log(m n log* mn)
     */
    public class Solution3 {
        class UnionFind {
            private int[] father = null;
            private int count;

            private int find(int x) {
                if (father[x] == x) {
                    return x;
                }
                return father[x] = find(father[x]);
            }

            public UnionFind(int n) {
                father = new int[n];
                for (int i = 0; i < n; i++) {
                    father[i] = i;
                }
            }

            public void connect(int a, int b) {
                int rootA = find(a);
                int rootB = find(b);
                if (rootA != rootB) {
                    father[rootA] = rootB;
                    count--;
                }
            }

            public int query() {
                return count;
            }

            public void setCount(int total) {
                count = total;
            }
        }

        static final char LAND = '1';
        static final char WATER = '0';

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int n = grid.length;
            int m = grid[0].length;
            UnionFind unionFind = new UnionFind(n * m);

            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == LAND) {
                        total++;
                    }
                }
            }

            unionFind.setCount(total);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == LAND) {
                        if (i > 0 && grid[i - 1][j] == LAND) {
                            unionFind.connect(i * m + j, (i - 1) * m + j);
                        }
                        if (i < n - 1 && grid[i + 1][j] == LAND) {
                            unionFind.connect(i * m + j, (i + 1) * m + j);
                        }
                        if (j > 0 && grid[i][j - 1] == LAND) {
                            unionFind.connect(i * m + j, i * m + j - 1);
                        }
                        if (j < m - 1 && grid[i][j + 1] == LAND) {
                            unionFind.connect(i * m + j, i * m + j + 1);
                        }
                    }
                }
            }

            return unionFind.query();
        }
    }

}
