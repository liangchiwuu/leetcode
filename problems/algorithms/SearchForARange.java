package algorithms;

import java.util.Arrays;

/**
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class SearchForARange {

    public static void main(String[] args) {

        int[] nums = { 5, 7, 7, 8, 8, 10 };
        int target = 8;

        int[] result = new SearchForARange().searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Divide and conquer, perform 2 binary search to locate left and right bound.
     * 
     * Time complexity: O(log n)
     */
    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) {
            return new int[] { -1, -1 };
        }

        int start;
        int end;
        int mid;
        int[] bound = new int[2];

        // search left bound
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            bound[0] = start;
        } else if (nums[end] == target) {
            bound[0] = end;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }

        // search right bound
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            bound[1] = end;
        } else if (nums[start] == target) {
            bound[1] = start;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }

        return bound;
    }
}
