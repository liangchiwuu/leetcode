package algorithms;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int s = 7;
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        int minSubArrayLen = new MinimumSizeSubarraySum().new Solution().minSubArrayLen(s, nums);
        System.out.println(minSubArrayLen);
    }

    /**
     * A brute-force solution. Simply find and compare all valid shortest subarray start from each index.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int min = Integer.MAX_VALUE;

            // find the shortest subarray starts from index i that sum(array) > s
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                int j = i;
                while (j < nums.length && sum < s) {
                    sum += nums[j];
                    j++;
                }
                if (sum >= s) {
                    min = Math.min(min, j - i);
                }
            }

            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }

    /**
     * So what can we do to optimize the time complexity of previous solution? One thing we can do is, when the sum of
     * subarray (i, j) is already calculated, we don't need to reset j while increasing i. Instead, we fix j at the same
     * position and only increase i by one. This will reduce the time complexity from O(n^2) to O(n) since both i and j
     * will only scan through the array once.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int j = 0;
            int sum = 0;
            int min = Integer.MAX_VALUE;

            // find the shortest subarray starts from index i that sum(array) > s
            for (int i = 0; i < nums.length; i++) {
                while (j < nums.length && sum < s) {
                    sum += nums[j];
                    j++;
                }
                if (sum >= s) {
                    min = Math.min(min, j - i);
                }
                // update sum while removing i
                sum -= nums[i];
            }

            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }

}
