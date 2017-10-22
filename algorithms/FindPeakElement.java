package algorithms;

/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 1 };
        int result = new FindPeakElement().new Solution().findPeakElement(nums);
        System.out.println(result);
    }

    /**
     * Standard binary search.
     * 
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    class Solution {
        public int findPeakElement(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0;
            int end = nums.length - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] > nums[mid - 1]) {
                    // either a uphill or a peak, peak >= mid
                    start = mid;
                } else if (nums[mid] > nums[mid + 1]) {
                    // either a downhill or peak, peak <= end
                    end = mid;
                } else {
                    // found a valley, doesn't matter to go left or right
                    end = mid;
                }
            }

            if (nums[start] > nums[end]) {
                return start;
            } else {
                return end;
            }
        }
    }
}
