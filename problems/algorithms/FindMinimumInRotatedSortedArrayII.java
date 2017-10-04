package algorithms;

/**
 * This is a follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 */
public class FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        int[] nums = new int[] { 4, 4, 4, 0, 1, 2, 4 };
        int result = new FindMinimumInRotatedSortedArrayII().new Solution().findMin(nums);
        System.out.println(result);
    }

    /**
     * While this array may contain duplicates, the worst case is that all the elements are the same. This can only be
     * verified after you have visited every element, which limits the worst time complexity to be O(n).
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution {
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int min = nums[0];
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                }
            }
            return min;
        }
    }

    /**
     * A 'fake' binary search since (sometimes) you can only remove one element at a time. The worst time complexity is
     * still O(n) but will performs better in normal situation.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution2 {
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0;
            int end = nums.length - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == nums[end]) {
                    // if mid equals to end, it's fine to remove end without impact the smallest element
                    end--;
                } else if (nums[mid] < nums[end]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (nums[start] < nums[end]) {
                return nums[start];
            } else {
                return nums[end];
            }
        }
    }

}
