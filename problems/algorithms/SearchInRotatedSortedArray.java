package algorithms;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[] { 6, 8, 9, 1, 3, 5 };
        int target = 3;
        int result = new SearchInRotatedSortedArray().search(nums, target);
        System.out.println(result);
    }

    /**
     * Standard binary search. Since the given array is rotated, we can't tell to go left or right just by comparing
     * nums[mid] and target. Instead, we first check if the mid is located on the upper or lower half. When the mid is
     * on lower half, the only case to go right is when target is between mid and end: nums[mid] <= target <= nums[end].
     * Apply the same logic while mid is on upper half then we can solve this problem.
     * 
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[end]) {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                if (nums[mid] >= target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else {
            return -1;
        }
    }

    /**
     * Another approach is to solve this problem through 2 steps. First, find the smallest element in a rotated sorted
     * array. Next, by comparing the target and the smallest element, we can easily tell which side is the target
     * located. What's left is a sorted array and we can simply perform another binary search to find the target.
     * Also see: Find Minimum in Rotated Sorted Array
     * 
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    public int search2(int[] nums, int target) {
        // 1. find the smallest element in rotated sorted array
        // 2. search target in a sorted array
        return -1;
    }

}
