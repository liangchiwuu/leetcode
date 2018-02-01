package algorithms;

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 * 
 * Example 1:
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: True
 * Explanation:
 * 1234
 * 5123
 * 9512
 * In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]", and in each diagonal
 * all elements are the same, so the answer is True.
 * 
 * Example 2:
 * Input: matrix = [[1,2],[2,2]]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 * 
 * Note:
 * matrix will be a 2D array of integers.
 * matrix will have a number of rows and columns in range [1, 20].
 * matrix[i][j] will be integers in range [0, 99].
 */
public class ToeplitzMatrix {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 1, 2, 3 }, { 9, 5, 1, 2 } };
        boolean isToeplitz = new ToeplitzMatrix().new Solution().isToeplitzMatrix(matrix);
        System.out.println(isToeplitz);
    }

    /**
     * The ideal is simple. In order to form a Toeplitz matrix, each element in the matrix must equals to its top-left
     * element (if there is one). So simply loop through each element and check compare the top-left element.
     * 
     * Time complexity: O(m*n)
     */
    class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            if (matrix == null) {
                return false;
            }

            if (matrix.length == 0 || matrix[0].length == 0) {
                return true;
            }

            // compare with the top-left element
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (row > 0 && col > 0 && matrix[row][col] != matrix[row - 1][col - 1]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

}
