package algorithms;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 */
public class FactorialTrailingZeroes {

    public static void main(String[] args) {
        int n = 25;
        int ans = new FactorialTrailingZeroes().new Solution().trailingZeroes(n);
        System.out.println(ans);
    }

    /**
     * (Time Limit Exceeded)
     * 
     * A naive solution. Assume for an integer n, we have the following prime factorization: n! = 2^x * 3^y * 5^z *...
     * We immediately know that the # of trailing zeros k = min(x, z), and since that x must grater than z in a
     * factorial, we get the the equation: # of trailing zeros k = # of 5s in n!. With that being said, we can simply
     * walk through each integer until n and count # of 5s in the factors.
     * 
     * Time complexity: O(n log n)
     */
    class Solution {
        public int trailingZeroes(int n) {
            int count = 0;

            for (int i = 0; i <= n; i++) {
                // count # of 5s in factor
                int j = i;
                while (j > 0 && j % 5 == 0) {
                    count++;
                    j /= 5;
                }
            }

            return count;
        }
    }

    /**
     * So, how can we further optimize the process from previous solution? We can actually represent the # of trailing
     * zeros in n! as: Z = n/5 + n/(5^2) + n/(5^3) + ... Where n/5 stands for the # of integers in 1-n that are
     * divisible by 5, each of these integers can provides one trailing zero. n/(5^2) stands for the # of integers in
     * 1-n that are divisible by 25, each of these can provides one ADDITIONAL trailing zero.
     * 
     * Time complexity: O(log n)
     */
    class Solution2 {
        public int trailingZeroes(int n) {
            int count = 0;

            while (n > 0) {
                count += n / 5;
                n /= 5;
            }

            return count;
        }
    }

    /**
     * A recursive version of solution 2.
     * 
     * Time complexity: O(log n)
     */
    class Solution3 {
        public int trailingZeroes(int n) {
            if (n == 0) {
                return 0;
            }

            return n / 5 + trailingZeroes(n / 5);
        }
    }

}
