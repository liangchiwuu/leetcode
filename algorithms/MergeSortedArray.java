package algorithms;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * 
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from
 * nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, -1, -1 };
        int[] nums2 = { 4, 5 };
        new MergeSortedArray().new Solution().merge(nums1, 3, nums2, 2);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * A straightforward answer that loops backwards.
     * 
     * Time complexity: O(m+n)
     */
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int mergeIndex = m + n - 1;
            int i = m - 1;
            int j = n - 1;

            while (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[mergeIndex--] = nums1[i--];
                } else {
                    nums1[mergeIndex--] = nums2[j--];
                }
            }

            // happens when nums2 finishes earlier
            while (i >= 0) {
                nums1[mergeIndex--] = nums1[i--];
            }

            // happens when nums1 finishes earlier
            while (j >= 0) {
                nums1[mergeIndex--] = nums2[j--];
            }
        }
    }

}
