package algorithms;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
 * LIS combination, it is only necessary for you to return the length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int result = new LongestIncreasingSubsequence().new Solution().lengthOfLIS(nums);
        System.out.println(result);
    }

    /**
     * Brute force.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            int[] length = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                length[i] = 1;
            }

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        length[j] = Math.max(length[j], length[i] + 1);
                    }
                }
            }

            int maxLength = 0;
            for (int i = 0; i < nums.length; i++) {
                maxLength = Math.max(maxLength, length[i]);
            }

            return maxLength;
        }
    }

    /**
     * TODO: improve it to O(n log n) time complexity
     * 
     * Time complexity: O(n log n)
     */
    class Solution2 {
        public int lengthOfLIS(int[] nums) {
            return 0;
        }
    }

}
