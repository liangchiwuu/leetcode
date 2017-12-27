package algorithms;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must
 * be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSumII {

    public static void main(String[] args) {
        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = new TwoSumII().new Solution().twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * A two pointers solution. Place left and right pointer at both ends, then there are three possibilities:
     * 
     * 1. left + right > target -> we can remove the element on right (max), since right + any other elements will only
     * increase the sum
     * 2. left + right < target -> for the same reason, we can remove the element on left (min)
     * 3. left + right = target -> return
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            if (numbers == null || numbers.length < 2) {
                return null;
            }

            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] > target) {
                    right--;
                } else if (numbers[left] + numbers[right] < target) {
                    left++;
                } else {
                    return new int[] { left + 1, right + 1 };
                }
            }

            return null;
        }
    }

}
