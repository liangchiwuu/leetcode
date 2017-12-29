package algorithms;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then
 * 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = { 1, 0, 1, 2 };
        new SortColors().new Solution2().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * A counting sort 2-pass solution. Use first loop to count the numbers of each color, then use second loop to fill
     * the original array.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        private static final int RED = 0;
        private static final int WHITE = 1;
        private static final int BLUE = 2;

        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            int redCount = 0;
            int whiteCount = 0;
            @SuppressWarnings("unused")
            int blueCount = 0;
            for (int num : nums) {
                if (num == RED) {
                    redCount++;
                } else if (num == WHITE) {
                    whiteCount++;
                } else {
                    blueCount++;
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (i < redCount) {
                    nums[i] = RED;
                } else if (i < redCount + whiteCount) {
                    nums[i] = WHITE;
                } else {
                    nums[i] = BLUE;
                }
            }
        }
    }

    /**
     * A two pointers 1-pass solution. The idea is, while looping through the array, move all the 0's to head and all
     * the 2's to tail. To do so, we will actually use three pointers: left pointer to indicate where 0's should go,
     * right pointer to indicate where 2's should go, and an additional pointer i to traverse through.
     * 
     * The key to this problem is that we can NOT increase pointer i after swapped a 2 to the end. The reason we can
     * safely increase i after swapping a 0 is because all the numbers in front of i is already scanned, meaning they
     * are either 0 or 1. However, when we put a 2 to tail, the number got swapped back is unknown, and it could be
     * another 2. In this case we have to check i again.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        private static final int RED = 0;
        @SuppressWarnings("unused")
        private static final int WHITE = 1;
        private static final int BLUE = 2;

        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            int left = 0;
            int right = nums.length - 1;
            int i = 0;
            while (i <= right) {
                if (nums[i] == RED) {
                    swap(nums, i, left);
                    left++;
                    i++;
                } else if (nums[i] == BLUE) {
                    swap(nums, i, right);
                    // only move right pointer, not i
                    right--;
                } else {
                    i++;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
