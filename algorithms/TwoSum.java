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
     * A brute force solution. Simply loop through every possibilities..
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                return null;
            }

            for (int i = 0; i < nums.length; i++) {
                int j = i + 1;
                while (j < nums.length) {
                    if (nums[i] + nums[j] == target) {
                        return new int[] { i, j };
                    }
                    j++;
                }
            }

            return null;
        }
    }

    /**
     * Use a HashMap to store the values, then matching the difference. Getting a value from a HashMap is usually O(1)
     * without collision.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                return null;
            }

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

            for (int i = 0; i < nums.length; i++) {
                int diff = target - nums[i];
                if (map.containsKey(diff)) {
                    return new int[] { i, map.get(diff) };
                }
                map.put(nums[i], i);
            }

            return null;
        }
    }

}
