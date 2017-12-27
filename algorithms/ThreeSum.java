package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in
 * the array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = { -1, 1, 0 };
        List<List<Integer>> results = new ThreeSum().new Solution().threeSum(nums);
        System.out.println(results);
    }

    /**
     * A two pointers solution. The idea is to first select the first number, then find all unique pairs that add up to
     * '0 - first number.' The key here is the handle of duplication.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();

            if (nums == null || nums.length < 3) {
                return results;
            }

            // must remember to sort before using two pointers approach
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (List<Integer> result : twoSum(nums, i + 1, nums.length - 1, 0 - nums[i])) {
                    result.add(nums[i]);
                    results.add(result);
                }
            }

            return results;
        }

        // find all unique pairs add up to target between start & end
        private List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
            int left = start;
            int right = end;
            List<List<Integer>> results = new ArrayList<>();

            while (left < right) {
                if (nums[left] + nums[right] > target) {
                    right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[left]);
                    result.add(nums[right]);
                    results.add(result);

                    left++;
                    // to guarantee that the solution is unique
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    right--;
                    // to guarantee that the solution is unique
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }

            return results;
        }
    }
}
