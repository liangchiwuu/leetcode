package algorithms;

/**
 * Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new
 * length.
 * 
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra
 * memory.
 * 
 * Example:
 * 
 * Given nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };
        int result = new RemoveDuplicatesFromSortedArray().new Solution().removeDuplicates(nums);
        System.out.println(result);
    }

    /**
     * A two pointers solution. Use slow pointer to record non-repetitive numbers and fast pointer to traverse through
     * the array.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    i++;
                    nums[i] = nums[j];
                }
            }

            return i + 1;
        }
    }

}
