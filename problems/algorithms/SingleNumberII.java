package algorithms;

import java.util.HashMap;

/**
 * Given an array of integers, every element appears three times except for one, which appears exactly once. Find that
 * single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumberII {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 3, 3, 3, 2, 2, 4, 1 };
        int result = new SingleNumberII().singleNumber(nums);
        System.out.println(result);
    }

    /**
     * Loop through the array and using a map with following rule:
     * 1. if e not in map: put(e, null)
     * 2. if e in map and v == null: put(e, e)
     * 3. if e in map and v != null: remove(e, e)
     * The only key left in the map is the result.
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int e : nums) {
            if (!map.containsKey(e)) {
                map.put(e, null);
            } else {
                if (map.get(e) == null) {
                    map.put(e, e);
                } else {
                    map.remove(e);
                }
            }
        }

        return map.keySet().iterator().next();
    }

    /**
     * Use a 32 bits number to represent the sum of all numbers. Mod each bit by 3 and what's left is the result.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int result = 0;
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                bits[i] += nums[j] >> i & 1;
            }
            bits[i] %= 3;
            result |= (bits[i] << i);
        }

        return result;
    }

}
