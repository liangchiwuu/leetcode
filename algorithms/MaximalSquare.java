package algorithms;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Return 4.
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = new char[][] {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' } };
        int max = new MaximalSquare().new Solution().maximalSquare(matrix);
        System.out.println(max);
    }

    /**
     * A bottom-up dynamic programming solution. The key here is the state definition. Define f[i][j] as the length of
     * the biggest square whose upper-left corner is at (i, j). Then while matrix[i][j] is not 0, we have the following
     * relationship: f[i][j] = min(f[i + 1][j], f[i][j + 1], f[i + 1][j + 1]) + 1. So here we just have to initialize
     * last row and column, then calculate from bottom-right to upper-left.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0
                    || matrix[0] == null || matrix[0].length == 0) {
                return 0;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            // initialization
            int[][] f = new int[m][n];
            for (int r = 0; r < m; r++) {
                f[r][n - 1] = matrix[r][n - 1] == '1' ? 1 : 0;
            }
            for (int c = 0; c < n; c++) {
                f[m - 1][c] = matrix[m - 1][c] == '1' ? 1 : 0;
            }

            // bottom-up
            for (int r = m - 2; r >= 0; r--) {
                for (int c = n - 2; c >= 0; c--) {
                    if (matrix[r][c] == '0') {
                        f[r][c] = 0;
                    } else {
                        f[r][c] = Math.min(Math.min(f[r + 1][c], f[r][c + 1]), f[r + 1][c + 1]) + 1;
                    }
                }
            }

            // find max
            int max = Integer.MIN_VALUE;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    max = Math.max(f[r][c], max);
                }
            }

            return max * max;
        }
    }

}
