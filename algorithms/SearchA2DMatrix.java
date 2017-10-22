package algorithms;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * 
 * Consider the following matrix:
 * 
 * [
 * [1, 3, 5, 7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */
public class SearchA2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
        int target = 11;
        boolean result = new SearchA2DMatrix().new Solution().searchMatrix(matrix, target);
        System.out.println(result);
    }

    /**
     * Perform 2 binary search. First find the row that possibly contains target, then search for the target in row.
     * 
     * Time complexity: O(log n + log m)
     */
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }

            int start = 0;
            int end = matrix.length - 1;

            // find the row that possibly contains target
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                int[] midArray = matrix[mid];
                if (midArray[0] > target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            int[] row;
            if (matrix[end][0] > target) {
                row = matrix[start];
            } else {
                row = matrix[end];
            }

            start = 0;
            end = row.length - 1;

            // find target in this row
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (row[mid] == target) {
                    return true;
                } else if (row[mid] > target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (row[start] == target || row[end] == target) {
                return true;
            } else {
                return false;
            }
        }
    }

}
