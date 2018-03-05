package algorithms;

/**
 * Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)
 * 
 * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at
 * the end. Given K, find how many non-negative integers x have the property that f(x) = K.
 * 
 * Example 1:
 * Input: K = 0
 * Output: 5
 * Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.
 * 
 * Example 2:
 * Input: K = 5
 * Output: 0
 * Explanation: There is no x such that x! ends in K = 5 zeroes.
 * 
 * Note:
 * K will be an integer in the range [0, 10^9].
 */
public class PreimageSizeOfFactorialZeroesFunction {

    public static void main(String[] args) {
        int k = 5;
        int ans = new PreimageSizeOfFactorialZeroesFunction().new Solution().preimageSizeFZF(k);
        System.out.println(ans);
    }

    /**
     * (This problem is a follow up of Factorial Trailing Zeroes, so make sure to complete it first.)
     * 
     * Define trailingZeroes(n) as the # of trailing zeros in n!. We can easily see that trailingZeroes() is a monotone
     * increasing function with a step size of 5. This is because for any integer k, "5k, 5k+1, 5k+2, 5k+3, 5k+4" will
     * have same # of zeros, in other words, # of trailing zeros will increase every five numbers. So now the question
     * has become: does there exist any n such that n! has k trailing zeros? If yes, then return 5, otherwise return 0.
     * A binary search will be a good approach under this situation.
     * 
     * n    trailing zeroes in n!
     * --------------------------
     * 0    0
     * 1    0
     * 2    0
     * 3    0
     * 4    0
     * 5    1
     * 6    1
     * 7    1
     * 8    1
     * 9    1
     * 10   2
     * 11   2
     * 12   2
     * 13   2
     * 14   2
     * 15   3
     * 16   3
     * 17   3
     * 18   3
     * 19   3
     * 20   4
     * 21   4
     * 22   4
     * 23   4
     * 24   4
     * 25   6
     * 26   6
     * 27   6
     * 28   6
     * 29   6
     * 30   7
     * ...
     * 
     * Time complexity: O(log n)
     */
    class Solution {
        public int preimageSizeFZF(int k) {
            int start = 0;
            // end should be a number has at least k trailing zeros in factorial
            int end = 10 * k + 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                int zeros = trailingZeroes(mid);
                if (zeros == k) {
                    return 5;
                } else if (zeros > k) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (trailingZeroes(start) == k || trailingZeroes(end) == k) {
                return 5;
            }

            return 0;

        }

        private int trailingZeroes(int n) {
            return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        }
    }

}
