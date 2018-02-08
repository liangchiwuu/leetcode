package algorithms;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last
 * index.)
 * 
 * Note:
 * You can assume that you can always reach the last index.
 */
public class JumpGameII {

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };
        int jumps = new JumpGameII().new Solution().jump(nums);
        System.out.println(jumps);
    }

    /**
     * A bottom-up dynamic programming solution. Define f[i] to indicate the min steps from i to end. Then f[i] will
     * equal to the minimum of all the next steps (f[i + 1] to f[i + nums[i]]) plus one.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MAX_VALUE;
            }

            int[] f = new int[nums.length];

            f[nums.length - 1] = 0;
            for (int i = nums.length - 2; i >= 0; i--) {
                f[i] = Integer.MAX_VALUE;
            }

            for (int i = nums.length - 2; i >= 0; i--) {
                for (int j = i + 1; j <= i + nums[i] && j < nums.length; j++) {
                    if (j < nums.length && f[j] != Integer.MAX_VALUE) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }

            return f[0];
        }
    }

    /**
     * A greedy algorithm solution. The code is basically the same as Jump Game, just here we add an additional steps
     * variable to accumulate # of steps. The idea is that, you must take one additional step to jump pass lastMax.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return Integer.MAX_VALUE;
            }

            int steps = 0;
            int lastMax = 0;
            int currentMax = 0;

            for (int i = 0; i < nums.length - 1; i++) {
                currentMax = Math.max(currentMax, i + nums[i]);
                if (i == lastMax) {
                    steps++;
                    lastMax = currentMax;
                }
            }

            return steps;
        }
    }

}
