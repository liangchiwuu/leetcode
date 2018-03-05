package algorithms;

import java.util.Arrays;

/**
 * Rotate an array of n elements to the right by k steps.
 * 
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * 
 * Hint:
 * Could you do it in-place with O(1) extra space?
 * 
 * Related problem: Reverse Words in a String II
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;
        new RotateArray().new Solution().rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * The idea is that, after rotate the array k times, we will move k elements from the end to start. So we can first
     * reverse all the elements, then reverse a partition of size k (last k elements now at beginning), then reverse the
     * rest. Take [1, 2, 3, 4, 5, 6, 7] and k = 3 as example:
     * 
     * original                 -> 1 2 3 4 5 6 7
     * reverse all              -> 7 6 5 4 3 2 1
     * reverse first k elements -> 5 6 7 4 3 2 1
     * reverse rest             -> 5 6 7 1 2 3 4
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution {
        public void rotate(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return;
            }

            k = k % nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

}
