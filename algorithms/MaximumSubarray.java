package algorithms;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * 
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * click to show more practice.
 * 
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which
 * is more subtle.
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int result = new MaximumSubarray().new Solution().maxSubArray(nums);
        System.out.println(result);
    }

    /**
     * The idea is each time we add a new number, the new maximum sum will become max(prevMaxSum, prevMaxSum+curtNum).
     * Then we just need to keep track of previous maximum sum and update the result with current number.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int max = Integer.MIN_VALUE;
            int sum = 0;

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                max = Math.max(max, sum);
                sum = Math.max(sum, 0);
            }

            return max;
        }
    }

    /**
     * An implementation using prefix sum. Define PrefixSum[i] = A[0] + A[1] + ... + A[i - 1], PrefixSum[0] = 0. We can
     * easily see that Sum(i~j) = PrefixSum[j + 1] - PrefixSum[i]. This means that, in order to find the maximum
     * Sum(i~j) for given j, we need to first know the minimum PrefixSum[i] for all i < j.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int max = Integer.MIN_VALUE;
            int sum = 0;
            int minSum = 0;

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                max = Math.max(max, sum - minSum);
                minSum = Math.min(sum, minSum);
            }

            return max;
        }
    }

}
