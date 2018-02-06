package algorithms;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Note: Given n will be a positive integer.
 * 
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation:
 * There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 3;
        int res = new ClimbingStairs().new Solution().climbStairs(n);
        System.out.println(res);
    }

    /**
     * A top-down dynamic programming solution. The idea is, from any position (except 0 & 1) on the stairs, we can only
     * either take one or two steps. So if we use f[i] to indicate the # of distinct ways to the top, f[i] must equal to
     * f[i - 1] plus f[i - 2], where i = 0 is the top.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int climbStairs(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }

            int[] f = new int[n + 1];

            f[0] = 1;
            f[1] = 1;

            for (int i = 2; i < n + 1; i++) {
                f[i] = f[i - 1] + f[i - 2];
            }

            return f[n];
        }
    }

}
