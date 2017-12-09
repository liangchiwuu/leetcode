package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
 * equals to k.
 * 
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * 
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1 };
        int k = 2;
        int result = new SubarraySumEqualsK().new Solution().subarraySum(nums, k);
        System.out.println(result);
    }

    /**
     * Brute force solution, simply loop through every possible subarrays and check sum.
     * 
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    class Solution {
        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    /**
     * A solution with HashMap and prefix sum. Define PrefixSum[i] = A[0] + A[1] + ... + A[i - 1], PrefixSum[0] = 0. We
     * can easily see that Sum(i~j) = PrefixSum[j + 1] - PrefixSum[i]. This means that, if Sum(i~j) equals to K for
     * given j, PrefixSum[j + 1] - PrefixSum[i] must also equals to K. So for a certain j, we need to use a HashMap to
     * store all previous PrefixSum[i] and also how many times they have appeared.
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution2 {
        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int count = 0;
            int sum = 0;

            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (map.containsKey(sum - k)) {
                    count += map.get(sum - k);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }

            return count;
        }
    }

}
