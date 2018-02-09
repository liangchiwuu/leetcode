package algorithms;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
 * LIS combination, it is only necessary for you to return the length.
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int result = new LongestIncreasingSubsequence().new Solution2().lengthOfLIS(nums);
        System.out.println(result);
    }

    /**
     * A bottom-up dynamic programming solution. Define f[i] as the length of LIS staring from index i. In order to find
     * f[i], we need to find the maximum f[j] for all j > i. While connecting such j after i, we get the LIS from i. So,
     * we can simply loop the array backwards to find the length of LIS starts from each index, then return the max one.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            // initialization
            int[] f = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                f[i] = 1;
            }

            // find length of LIS starts from each index
            for (int i = nums.length - 1; i >= 0; i--) {
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                    }
                }
            }

            // find max f
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(max, f[i]);
            }

            return max;
        }
    }

    /**
     * !! DRAW IT OUT TO SEE THE PROCESS !!
     * 
     * A dynamic programming solution with binary search. This f array is meant to store the increasing subsequence
     * formed by including the currently encountered element. Note that f does NOT result in LIS, but will have the same
     * length.
     * 
     * Time complexity: O(n log n)
     */
    class Solution2 {
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int[] f = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                f[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < nums.length; i++) {
                // find the first number in f >= nums[i]
                int index = binarySearch(f, nums[i]);
                f[index] = nums[i];
            }

            // length of f is the length of LIS (f != LIS)
            for (int i = nums.length - 1; i >= 0; i--) {
                if (f[i] != Integer.MAX_VALUE) {
                    return i + 1;
                }
            }

            return 0;
        }

        // find the first number >= num
        private int binarySearch(int[] f, int num) {
            int start = 0;
            int end = f.length - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (f[mid] < num) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            return f[start] >= num ? start : end;
        }
    }

}
