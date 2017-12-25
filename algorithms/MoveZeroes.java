package algorithms;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * 
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 };
        new MoveZeroes().new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * A solution with two pointers. Use one pointer to record the position of last zero, and another pointer to
     * traverse through the array and swap. Note that:
     * 
     * 1. all elements before the slow pointer are not zero
     * 2. all elements between two pointers are zeroes
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public void moveZeroes(int[] nums) {
            int i = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                }
            }
        }
    }

    /**
     * A straightforward two-step solution. First move all the non zero elements to front, then fill the rest with zero.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public void moveZeroes(int[] nums) {
            // move all the non zero elements to front
            int i = 0;
            for (int num : nums) {
                if (num != 0) {
                    nums[i++] = num;
                }
            }

            // fill the rest with zero
            while (i < nums.length) {
                nums[i++] = 0;
            }
        }
    }
}
