package algorithms;

/**
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 */
public class UniquePathsII {

    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        int uniquePaths = new UniquePathsII().new Solution().uniquePathsWithObstacles(obstacleGrid);
        System.out.println(uniquePaths);
    }

    /**
     * A dynamic programming solution with top-down structure. f[i][j] here stands for # of possible unique paths to
     * reach (i, j). The only difference is to add some constrains to skip obstacles. Remember: DO NOT try to change the
     * overall structure at the beginning when the requirement changed just a bit. In most cases we should first try to
     * adapt the specification change with minimum cost!
     * 
     * Time complexity: O(m*n)
     * Space complexity: O(m*n)
     */
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0
                    || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
                return 0;
            }

            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;

            // when origin or destination has obstacle
            if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
                return 0;
            }

            // initialization
            int[][] f = new int[m][n];
            f[0][0] = 1;
            for (int row = 1; row < m; row++) {
                if (obstacleGrid[row][0] == 1) {
                    break;
                }
                f[row][0] = 1;
            }
            for (int col = 1; col < n; col++) {
                if (obstacleGrid[0][col] == 1) {
                    break;
                }
                f[0][col] = 1;
            }

            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    if (obstacleGrid[row][col] == 1) {
                        continue;
                    }
                    f[row][col] = f[row - 1][col] + f[row][col - 1];
                }
            }

            return f[m - 1][n - 1];
        }
    }

    /**
     * The idea and logic of this solution is exactly the same as the previous one. The only difference is that we
     * improved the space complexity form O(m*n) to O(n). But how? Notice that sine the robot is only allowed to move
     * either right or down, if we calculate f from top-left to bottom-right, any results are discardable after they
     * have been used once (robots can never go back). By overwriting/replacing old states with new states, we can
     * compress the f matrix from size m*n to just size n.
     * 
     * Time complexity: O(m*n)
     * Space complexity: O(n)
     */
    class Solution2 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0
                    || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
                return 0;
            }

            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[] f = new int[n];

            f[0] = 1;
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (obstacleGrid[row][col] == 1) {
                        f[col] = 0;
                    } else if (col > 0) {
                        f[col] += f[col - 1];
                    }
                }
            }

            return f[n - 1];
        }
    }

}
