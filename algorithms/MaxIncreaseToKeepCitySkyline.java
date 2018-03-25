package algorithms;

/**
 * In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there. We are
 * allowed to increase the height of any number of buildings, by any amount (the amounts can be different for different
 * buildings). Height 0 is considered to be a building as well.
 * 
 * At the end, the "skyline" when viewed from all four directions of the grid, i.e. top, bottom, left, and right, must
 * be the same as the skyline of the original grid. A city's skyline is the outer contour of the rectangles formed by
 * all the buildings when viewed from a distance. See the following example.
 * 
 * What is the maximum total sum that the height of the buildings can be increased?
 * 
 * Example:
 * Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
 * Output: 35
 * Explanation:
 * The grid is:
 * [ [3, 0, 8, 4],
 * [2, 4, 5, 7],
 * [9, 2, 6, 3],
 * [0, 3, 1, 0] ]
 * 
 * The skyline viewed from top or bottom is: [9, 4, 8, 7]
 * The skyline viewed from left or right is: [8, 7, 9, 3]
 * 
 * The grid after increasing the height of buildings without affecting skylines is:
 * gridNew = [ [8, 4, 8, 7],
 * [7, 4, 7, 7],
 * [9, 4, 8, 7],
 * [3, 3, 3, 3] ]
 * 
 * Notes:
 * 1 < grid.length = grid[0].length <= 50.
 * All heights grid[i][j] are in the range [0, 100].
 * All buildings in grid[i][j] occupy the entire grid cell: that is, they are a 1 x 1 x grid[i][j] rectangular prism.
 */
public class MaxIncreaseToKeepCitySkyline {

    public static void main(String[] args) {
        int[][] grid = { { 3, 0, 8, 4 }, { 2, 4, 5, 7 }, { 9, 2, 6, 3 }, { 0, 3, 1, 0 } };
        int maxIncrease = new MaxIncreaseToKeepCitySkyline().new Solution().maxIncreaseKeepingSkyline(grid);
        System.out.println(maxIncrease);
    }

    /**
     * A naive solution. In order to maintain the skyline, the building located at (r, c) must not exceed the highest
     * building on the same row/column. So we can first find the highest building for each row/column, then scan the
     * grid again to calculate the result.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int maxIncreaseKeepingSkyline(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[] rowSkyLine = new int[m];
            int[] columnSkyLine = new int[n];

            for (int r = 0; r < m; r++) {
                int max = 0;
                for (int c = 0; c < n; c++) {
                    max = Math.max(max, grid[r][c]);
                }
                rowSkyLine[r] = max;
            }

            for (int c = 0; c < n; c++) {
                int max = 0;
                for (int r = 0; r < m; r++) {
                    max = Math.max(max, grid[r][c]);
                }
                columnSkyLine[c] = max;
            }

            int ans = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    ans += Math.min(rowSkyLine[r], columnSkyLine[c]) - grid[r][c];
                }
            }

            return ans;
        }
    }

}
