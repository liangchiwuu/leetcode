package algorithms;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 * 
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * 
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 */
public class ValidTriangleNumber {

    public static void main(String[] args) {
        int[] nums = { 2, 2, 3, 4 };
        int result = new ValidTriangleNumber().new Solution().triangleNumber(nums);
        System.out.println(result);
    }

    /**
     * A two pointers solution. In oder to form a triangle, the sum of two shorter edges must be greater than the
     * longest edge. So we can first select the longest edge, then count and accumulate the possible triangle
     * combinations.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int triangleNumber(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0;
            }

            Arrays.sort(nums);
            int count = 0;
            for (int i = nums.length - 1; i >= 2; i--) {
                count += countTriangle(nums, 0, i - 1, nums[i]);
            }

            return count;
        }

        private int countTriangle(int[] nums, int start, int end, int longestEdge) {
            int left = start;
            int right = end;
            int count = 0;

            while (left < right) {
                if (nums[left] + nums[right] > longestEdge) {
                    // any edges in [left, right) can form a triangle with right, creating (right - left) triangles
                    count += (right - left);
                    right--;
                } else {
                    // we can safely skip the shortest edge
                    left++;
                }
            }

            return count;
        }
    }

}
