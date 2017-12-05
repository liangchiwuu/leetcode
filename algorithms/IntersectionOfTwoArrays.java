package algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 
 * Note:
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 2, 1 };
        int[] nums2 = { 2, 2 };
        int[] result = new IntersectionOfTwoArrays().new Solution().intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    /**
     * A naive solution using sets to record all intersections.
     * 
     * Time complexity: O(m + n)
     * Space complexity: O(min(m, n))
     */
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return new int[0];
            }

            Set<Integer> candidates = new HashSet<Integer>();
            for (int num : nums1) {
                candidates.add(num);
            }

            Set<Integer> results = new HashSet<Integer>();
            for (int num : nums2) {
                if (candidates.contains(num)) {
                    results.add(num);
                }
            }

            return toArray(results);
        }

        private int[] toArray(Set<Integer> set) {
            int[] result = new int[set.size()];
            int i = 0;
            for (int num : set) {
                result[i++] = num;
            }
            return result;
        }
    }

    /**
     * A sort + merge solution. The idea is to sort two arrays respectively, then use two pointers to merge.
     * 
     * Time complexity: O(nlogn + mlogm)
     * Space complexity: O(1)
     */
    class Solution2 {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return new int[0];
            }

            Arrays.sort(nums1);
            Arrays.sort(nums2);
            Set<Integer> results = new HashSet<Integer>();

            int i = 0;
            int j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    results.add(nums1[i]);
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }

            return toArray(results);
        }

        private int[] toArray(Set<Integer> set) {
            int[] result = new int[set.size()];
            int i = 0;
            for (int num : set) {
                result[i++] = num;
            }
            return result;
        }
    }

    /**
     * A binary search solution. The idea is to first sort one array, then we can loop through the second array and use
     * binary search to check if the number exists in first array.
     * 
     * Time complexity: O(nlogn + mlogn) = O((m + n) * logn)
     * Space complexity: O(1)
     */
    class Solution3 {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return new int[0];
            }

            Arrays.sort(nums1);

            Set<Integer> results = new HashSet<Integer>();
            for (int num : nums2) {
                if (results.contains(num)) {
                    continue;
                }
                if (binarySearch(nums1, num)) {
                    results.add(num);
                }
            }

            return toArray(results);
        }

        private boolean binarySearch(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return false;
            }

            int start = 0;
            int end = nums.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] > target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (nums[start] == target || nums[end] == target) {
                return true;
            }

            return false;
        }

        private int[] toArray(Set<Integer> set) {
            int[] result = new int[set.size()];
            int i = 0;
            for (int num : set) {
                result[i++] = num;
            }
            return result;
        }
    }

}
