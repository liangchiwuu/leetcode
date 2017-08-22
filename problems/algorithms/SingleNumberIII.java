package algorithms;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once.
 * 
 * For example:
 * 
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * 
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class SingleNumberIII {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 3, 2, 5 };
        int[] result = new SingleNumberIII().singleNumber(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Loop through the array and put into set, remove from set if it's already there. Any number
     * appears twice will remove itself and whatever remains is the result.
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        for (int e : nums) {
            if (set.contains(e)) {
                set.remove(e);
            } else {
                set.add(e);
            }
        }

        int[] result = new int[set.size()];
        int i = 0;
        for (int e : set) {
            result[i] = e;
            i++;
        }

        return result;
    }

    /**
     * Since there are 2 single numbers A and B, the XOR result of all number must != 0. Then we can use
     * this XOR result to split the original array into 2 groups, all paired numbers must be in the same
     * group, and A/B must be in the different group. Then we can use the same method from Single Number I
     * to get the result.
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int[] singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int xor = 0;
        for (int e : nums) {
            xor ^= e;
        }

        // get the last bit of XOR result, e.g. 1010 ==> 0010
        // https://en.wikipedia.org/wiki/Bit_manipulation
        int lastBit = xor - (xor & (xor - 1));
        int r1 = 0, r2 = 0;
        for (int e : nums) {
            if ((lastBit & e) == 0) {
                r1 ^= e;
            } else {
                r2 ^= e;
            }
        }

        return new int[] { r1, r2 };
    }

}
