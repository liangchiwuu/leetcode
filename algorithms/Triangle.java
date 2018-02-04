package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row
 * below.
 * 
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
 * triangle.
 */
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int layer = 0; layer < 4; layer++) {
            triangle.add(new ArrayList<>());
        }
        triangle.get(0).add(2);
        triangle.get(1).add(3);
        triangle.get(1).add(4);
        triangle.get(2).add(6);
        triangle.get(2).add(5);
        triangle.get(2).add(7);
        triangle.get(3).add(4);
        triangle.get(3).add(1);
        triangle.get(3).add(8);
        triangle.get(3).add(3);
        int minimumTotal = new Triangle().new Solution().minimumTotal(triangle);
        System.out.println(minimumTotal);
    }

    /**
     * A naive divide and conquer solution. Simply travel each possible route and return the minimum sum. You will find
     * this solution exceed the time limit because of high time complexity.
     * 
     * Time complexity: O(2^n)
     */
    class Solution {

        private List<List<Integer>> triangle;

        public int minimumTotal(List<List<Integer>> triangle) {
            this.triangle = triangle;
            return divideConquer(0, 0);
        }

        private int divideConquer(int x, int y) {
            if (x == triangle.size()) {
                return 0;
            }

            return triangle.get(x).get(y) + Math.min(divideConquer(x + 1, y), divideConquer(x + 1, y + 1));
        }
    }

    /**
     * If we look at solution 1 carefully, we will find that the previous solution actually treat the triangle as a
     * binary tree, which makes the time complexity to be 2^n (nodes in a binary tree). But since a triangle actually
     * only have n^2 nodes (lots of nodes are sharing same child & parent), there must be a lot of repetitive
     * calculations. We can solve this with dynamic programming, which stores the results previously calculated then
     * reuse them later.
     * 
     * Time complexity: O(n^2)
     */
    class Solution2 {

        private List<List<Integer>> triangle;
        private int size;
        private int[][] minSum;

        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0
                    || triangle.get(0) == null || triangle.get(0).size() == 0) {
                return -1;
            }

            this.triangle = triangle;
            size = triangle.size();

            // initialize state table
            minSum = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    minSum[i][j] = Integer.MAX_VALUE;
                }
            }

            return divideConquer(0, 0);
        }

        private int divideConquer(int x, int y) {
            if (x == size) {
                return 0;
            }

            if (minSum[x][y] != Integer.MAX_VALUE) {
                return minSum[x][y];
            }

            minSum[x][y] = triangle.get(x).get(y) + Math.min(divideConquer(x + 1, y), divideConquer(x + 1, y + 1));

            return minSum[x][y];
        }
    }

    /**
     * Another dynamic programming solution. This solution achieves dynamic programming with bottom-up approach.
     * 
     * Time complexity: O(n^2)
     */
    class Solution3 {

        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0
                    || triangle.get(0) == null || triangle.get(0).size() == 0) {
                return -1;
            }

            int n = triangle.size();
            int[][] state = new int[n][n];

            // initialize bottom layer
            for (int i = 0; i < n; i++) {
                state[n - 1][i] = triangle.get(n - 1).get(i);
            }

            // bottom-up
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    state[i][j] = Math.min(state[i + 1][j], state[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }

            return state[0][0];
        }
    }

}
