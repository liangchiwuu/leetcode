package algorithms;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum
 * to n.
 * 
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class PerfectSquares {

    public static void main(String[] args) {
        int n = 12;
        int ans = new PerfectSquares().new Solution().numSquares(n);
        System.out.println(ans);
    }

    /**
     * A top-down dynamic programming solution. Define f[i] as the solution for integer i. Now the question is that, how
     * can we calculate f[i] based on f[0] ~ f[i-1]? Think this way: for any integer i, there exist multiple (m, n) pair
     * such that i = m + n^2. For each (m, n) pair, f[i] will equal to f[m] + f[n^2], and since f[n^2] equals to 1, we
     * have the equation f[i] = f[m] + 1. From this equation, we just need to loop through all possible (m, n) pairs and
     * find the minimum.
     * 
     * Time complexity: O(n log n)
     */
    class Solution {
        public int numSquares(int n) {
            int[] f = new int[n + 1];

            // initialization
            Arrays.fill(f, Integer.MAX_VALUE);
            for (int i = 0; i * i <= n; i++) {
                f[i * i] = 1;
            }

            for (int i = 0; i <= n; i++) {
                // loop through all possible (m, n) pairs where m + n^2 = i
                for (int j = 1; j * j <= i; j++) {
                    // n <- j
                    // m <- i - j * j
                    f[i] = Math.min(f[i], f[i - j * j] + 1);
                }
            }

            return f[n];
        }
    }

    /**
     * Another top-down dynamic programming solution. The idea is identical to the previous one, just looping with a
     * different order.
     * 
     * Time complexity: O(n log n)
     */
    class Solution2 {
        public int numSquares(int n) {
            int[] f = new int[n + 1];

            // initialization
            Arrays.fill(f, Integer.MAX_VALUE);
            for (int i = 0; i * i <= n; i++) {
                f[i * i] = 1;
            }

            for (int i = 0; i <= n; i++) {
                for (int j = 0; i + j * j <= n; j++) {
                    f[i + j * j] = Math.min(f[i] + 1, f[i + j * j]);
                }
            }

            return f[n];
        }
    }

}
