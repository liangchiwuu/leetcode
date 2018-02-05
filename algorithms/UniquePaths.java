package algorithms;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * Example
 * Given m = 3 and n = 3, return 6.
 * Given m = 4 and n = 5, return 35.
 * 
 * Note: m and n will be at most 100.
 */
public class UniquePaths {

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        int uniquePaths = new UniquePaths().new Solution().uniquePaths(m, n);
        System.out.println(uniquePaths);
    }

    /**
     * A dynamic programming solution with top-down structure.
     * 
     * Time complexity: O(m*n)
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            if (m == 0 || n == 0) {
                return 0;
            }

            int[][] f = new int[m][n];

            // initialization
            f[0][0] = 1;
            for (int row = 1; row < m; row++) {
                f[row][0] = 1;
            }
            for (int col = 1; col < n; col++) {
                f[0][col] = 1;
            }

            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    f[row][col] = f[row - 1][col] + f[row][col - 1];
                }
            }

            return f[m - 1][n - 1];
        }
    }
}
