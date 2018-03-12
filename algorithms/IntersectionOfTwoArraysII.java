package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * 
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into
 * the memory at once?
 */
public class IntersectionOfTwoArraysII {

    /*
     * Answers to follow up questions:
     * 
     * What if the given array is already sorted? How would you optimize your algorithm?
     * The two pointers solution is clearly better since it only takes O(min(m, n)) time and O(1) space.
     * 
     * What if nums1's size is small compared to nums2's size? Which algorithm is better?
     * If nums1's size is small, the HashMap solution would be better since O(min(m, n)) space would also be small.
     * 
     * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements
     * into the memory at once?
     * Still we can scan nums1 to build the frequency map, then process nums2 chunk by chunk (based on memory limit).
     */

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 2, 1 };
        int[] nums2 = { 2, 2 };
        int[] intersection = new IntersectionOfTwoArraysII().new Solution().intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    /**
     * A two pointers solution. First sort two arrays respectively, then use two pointers to scan through.
     * 
     * Time complexity: O(n log n + m log m)
     * Space complexity: O(1)
     */
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return new int[0];
            }

            Arrays.sort(nums1);
            Arrays.sort(nums2);
            List<Integer> list = new ArrayList<>();

            int i = 0;
            int j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    list.add(nums1[i]);
                    i++;
                    j++;
                }
            }

            int[] result = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                result[k] = list.get(k);
            }

            return result;
        }
    }

    /**
     * A HashMap solution. It's similar to Intersection of Two Arrays, just here we need a map rather than a set to also
     * record the frequency of each element.
     * 
     * Time complexity: O(m + n)
     * Space complexity: O(min(m, n))
     */
    class Solution2 {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
                return new int[0];
            }

            // ensure nums1 is shorter
            if (nums1.length > nums2.length) {
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int e : nums1) {
                map.put(e, map.getOrDefault(e, 0) + 1);
            }

            List<Integer> list = new ArrayList<>();
            for (int e : nums2) {
                if (map.containsKey(e) && map.get(e) > 0) {
                    map.put(e, map.get(e) - 1);
                    list.add(e);
                }
            }

            int[] result = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                result[k] = list.get(k);
            }

            return result;
        }
    }

}
