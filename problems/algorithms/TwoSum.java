package algorithms;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * 
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = new TwoSum().new Solution().twoSum(nums, target);
        System.out.println("Result: " + Arrays.toString(result));
    }

    /**
     * Use double loop to step through.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] result = new int[2];
            int length = nums.length;

            for (int i = 0; i < length; i++) {
                int j = i + 1;
                while (j < length) {
                    if (nums[i] + nums[j] == target) {
                        return new int[] { i, j };
                    }
                    j++;
                }
            }

            return result;
        }
    }

    /**
     * Use a HashMap to store the values, then matching the difference.
     * Getting a value from a HashMap is usually O(1) while no collision.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            int i = 0;
            int j = 0;
            int length = nums.length;
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

            for (int k = 0; k < length; k++) {
                int diff = target - nums[k];
                if (map.containsKey(diff)) {
                    i = k;
                    j = map.get(diff);
                    break;
                } else {
                    map.put(nums[k], k);
                }
            }

            return new int[] { i, j };
        }
    }

}
