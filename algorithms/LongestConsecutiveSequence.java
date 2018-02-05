package algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        int res = new LongestConsecutiveSequence().new Solution().longestConsecutive(nums);
        System.out.println(res);
    }

    /**
     * The idea is to first sort the array, then just loop from start to end to recored longest consecutive sequence.
     * One thing to note is the prevention of duplicates.
     * 
     * Time complexity: O(n log n)
     */
    class Solution {
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);

            int longest = 0;
            int current = 1;

            for (int i = 0; i < nums.length; i++) {
                // to prevent duplicates
                if (i != nums.length - 1 && nums[i] == nums[i + 1]) {
                    continue;
                }

                if (i == nums.length - 1 || nums[i] != nums[i + 1] - 1) {
                    longest = Math.max(longest, current);
                    current = 1;
                } else {
                    current++;
                }
            }

            return longest;
        }
    }

    /**
     * The idea is to first add all the elements into the set. Next, loop through the set and check the length of
     * consecutive sequence whenever we find a head (meaning head - 1 not in the set).
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            int longest = 0;

            for (int num : set) {
                // proceed only for the beginning of a sequence
                if (!set.contains(num - 1)) {
                    int low = num;
                    int high = num;

                    while (set.contains(high + 1)) {
                        high++;
                    }

                    longest = Math.max(longest, high - low + 1);
                }
            }

            return longest;
        }
    }

}
