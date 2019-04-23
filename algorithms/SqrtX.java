package algorithms;

/**
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * x is guaranteed to be a non-negative integer.
 * 
 * 
 * Example 1:
 * 
 * Input: 4
 * Output: 2
 * Example 2:
 * 
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be
 * truncated.
 */
public class SqrtX {

    public static void main(String[] args) {
        int x = 8;
        int result = new SqrtX().new Solution().mySqrt(x);
        System.out.println(result);
    }

    /**
     * A standard binary search solution. The goal here is to find the last integer which the square of it <= x. Another
     * key is that we have to use long instead of int to prevent overflow.
     * 
     * Time complexity: O(log n)
     */
    class Solution {
        public int mySqrt(int x) {
            long start = 0;
            long end = x;
            while (start + 1 < end) {
                long mid = start + (end - start) / 2;
                // this is why we need long, since int * int is likely to overflow
                if (mid * mid > x) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (end * end <= x) {
                return (int) end;
            }
            return (int) start;
        }
    }

    /**
     * Use Newton's method to find the approximation of square root. The basic idea is: for any x and initial guess r,
     * we can iteratively apply 'r = (r + x / r) / 2' to make r approach the square root of x.
     * 
     * References:
     * https://en.wikipedia.org/wiki/Newton%27s_method
     * http://www.nowamagic.net/librarys/veda/detail/2268
     * 
     * Time complexity: O(log n)
     */
    class Solution2 {
        public int mySqrt(int x) {
            long r = x;

            // Newton's method iteration
            while (r * r > x) {
                r = (r + x / r) / 2;
            }

            return (int) r;
        }
    }
}
