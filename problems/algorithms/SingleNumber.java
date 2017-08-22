package algorithms;

import java.util.HashSet;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 1, 3, 4, 3 };
        int result = new SingleNumber().singleNumber(nums);
        System.out.println(result);
    }

    /**
     * Loop through the array and put into set, remove from set if it's already there. Any number
     * appears twice will remove itself and whatever remains is the result.
     * 
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        for (int e : nums) {
            if (set.contains(e)) {
                set.remove(e);
            } else {
                set.add(e);
            }
        }

        return set.iterator().next();
    }

    /**
     * Use XOR operation. Since XOR is commutative, 2 same numbers will XOR itself to 0.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int result = 0;
        for (int e : nums) {
            result ^= e;
        }

        return result;
    }

}
