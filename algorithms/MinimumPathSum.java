package algorithms;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the
 * sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example 1:
 * [[1,3,1],
 * [1,5,1],
 * [4,2,1]]
 * 
 * Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        int minSum = new MinimumPathSum().new Solution().minPathSum(grid);
        System.out.println(minSum);
    }

    /**
     * A dynamic programming solution using top-down structure.
     * 
     * Time complexity: O(m*n)
     */
    class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] f = new int[m][n];
            // initialize 1st row & 1st col
            f[0][0] = grid[0][0];
            for (int row = 1; row < m; row++) {
                f[row][0] = f[row - 1][0] + grid[row][0];
            }
            for (int col = 1; col < n; col++) {
                f[0][col] = f[0][col - 1] + grid[0][col];
            }

            // top down
            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    f[row][col] = Math.min(f[row - 1][col], f[row][col - 1]) + grid[row][col];
                }
            }

            return f[m - 1][n - 1];
        }
    }

    /**
     * A dynamic programming solution using memorized search (divide and conquer + state reuse). The idea is the same as
     * divide and conquer, but we store all previously calculated results so we can reuse them later.
     * 
     * Time complexity: O(m*n)
     */
    class Solution2 {
        private int[][] grid;
        private int m;
        private int n;
        private int[][] f;

        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
                return 0;
            }

            this.grid = grid;
            this.m = grid.length;
            this.n = grid[0].length;

            f = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    f[i][j] = Integer.MAX_VALUE;
                }
            }

            return helper(0, 0);
        }

        // find the minimum path from (x, y) to bottom right
        private int helper(int x, int y) {
            if (x == m - 1 && y == n - 1) {
                return grid[x][y];
            }

            if (f[x][y] != Integer.MAX_VALUE) {
                return f[x][y];
            }

            if (x == m - 1) {
                f[x][y] = helper(x, y + 1) + grid[x][y];
            } else if (y == n - 1) {
                f[x][y] = helper(x + 1, y) + grid[x][y];
            } else {
                f[x][y] = Math.min(helper(x + 1, y), helper(x, y + 1)) + grid[x][y];
            }

            return f[x][y];
        }
    }

}
