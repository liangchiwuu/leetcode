package algorithms;

/**
 * This is a follow up for "Search in Rotated Sorted Array":if
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * The array may contain duplicates.
 */
public class SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums = new int[] { 6, 8, 9, 1, 3, 5 };
        int target = 2;
        boolean result = new SearchInRotatedSortedArrayII().search(nums, target);
        System.out.println(result);
    }

    /**
     * While this array may contain duplicates, the worst case is that all the elements are the same. This can only be
     * verified after you have visited every element, which limits the worst time complexity to be O(n).
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        for (int e : nums) {
            if (e == target) {
                return true;
            }
        }

        return false;
    }

    /**
     * A 'fake' binary search since (sometimes) you can only remove one element at a time. The worst time complexity is
     * still O(n) but will performs better in normal situation.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < nums[end]) {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else if (nums[mid] > nums[end]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                end--;
            }

        }

        if (nums[start] == target || nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }

}
