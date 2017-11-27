package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
 * other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 * 
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [
 * [".Q..", // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.", // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class NQueens {

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> results = new NQueens().new Solution().solveNQueens(n);
        System.out.println(results);
    }

    /**
     * A DFS recursive solution. the key to solve this problem is to represent a chessboard by a list of integer. Each
     * number in the list represents the position (row) of the queen in a column (since there can only be 1 queen in a
     * column). For instance, solutions to the 4-queens puzzle can be represented as [1, 3, 0, 2] and [2, 0, 3, 1].
     * Applying this step immediately turns this problem into a permutation question. The goal now is to travel through
     * each permutation consists of n numbers (0 ~ n-1) and find the valid ones.
     * 
     * Time complexity: O(n!), since T(n) = n*T(n-1) + O(n^2)
     */
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> results = new ArrayList<>();

            dfs(results, new ArrayList<>(), n);

            return results;
        }

        private void dfs(List<List<String>> results, List<Integer> cols, int n) {
            if (cols.size() == n) {
                results.add(drawChessboard(cols));
            }

            for (int i = 0; i < n; i++) {
                if (!isValid(cols, i)) {
                    continue;
                }
                cols.add(i);
                dfs(results, cols, n);
                cols.remove(cols.size() - 1);
            }
        }

        private boolean isValid(List<Integer> cols, int column) {
            int row = cols.size();
            for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
                // test if on same column
                if (cols.get(rowIndex) == column) {
                    return false;
                }
                // test if on diagonal
                if (rowIndex + cols.get(rowIndex) == row + column) {
                    return false;
                }
                if (rowIndex - cols.get(rowIndex) == row - column) {
                    return false;
                }
            }
            return true;
        }

        private List<String> drawChessboard(List<Integer> cols) {
            List<String> chessboard = new ArrayList<>();
            // build each row
            for (int col : cols) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cols.size(); i++) {
                    sb.append(col == i ? 'Q' : '.');
                }
                chessboard.add(sb.toString());
            }
            return chessboard;
        }
    }
}
