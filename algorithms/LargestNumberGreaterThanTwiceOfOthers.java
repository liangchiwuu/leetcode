package algorithms;

/**
 * In a given integer array nums, there is always exactly one largest element.
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 * If it is, return the index of the largest element, otherwise return -1.
 * 
 * Example 1:
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array x,
 * 6 is more than twice as big as x. The index of value 6 is 1, so we return 1.
 * 
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: -1
 * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 * 
 * Note:
 * nums will have a length in the range [1, 50].
 * Every nums[i] will be an integer in the range [0, 99].
 */
public class LargestNumberGreaterThanTwiceOfOthers {

    public static void main(String[] args) {
        int[] nums = { 3, 6, 1, 0 };
        int result = new LargestNumberGreaterThanTwiceOfOthers().new Solution().dominantIndex(nums);
        System.out.println(result);
    }

    /**
     * A straightforward solution. This question is equivalent to: is the largest element at least twice as much as the
     * second largest element? So we just need to simply loop the array and record the largest and second largest.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int dominantIndex(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int max = Integer.MIN_VALUE;
            int maxIndex = -1;
            int secondMax = Integer.MIN_VALUE;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) {
                    secondMax = max;
                    max = nums[i];
                    maxIndex = i;
                } else if (nums[i] > secondMax) {
                    secondMax = nums[i];
                }
            }

            return max < secondMax * 2 ? -1 : maxIndex;
        }
    }
}
