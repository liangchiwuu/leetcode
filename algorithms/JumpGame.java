package algorithms;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array. Each element
 * in the array represents your maximum jump length at that position. Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 0, 4 };
        boolean canJump = new JumpGame().new Solution().canJump(nums);
        System.out.println(canJump);
    }

    /**
     * A bottom-up dynamic programming solution. Define f[i] to indicate if it's possible to reach the end from i. Then
     * we can simply see that f[i] can only be true if one of the next steps (f[i + 1] to f[i + nums[i]]) is true.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public boolean canJump(int[] nums) {
            if (nums == null | nums.length == 0) {
                return false;
            }

            boolean[] f = new boolean[nums.length];
            f[nums.length - 1] = true;

            for (int i = nums.length - 2; i >= 0; i--) {
                for (int j = i + 1; j <= i + nums[i]; j++) {
                    if (f[j] == true) {
                        f[i] = true;
                        break;
                    }
                }
            }

            return f[0];
        }
    }

    /**
     * A greedy algorithm solution. The idea is simple, let's start from index 0, the farthest we can reach at this
     * moment is 0 + nums[0] = nums[0], note that all indices between origin and farthest is also accessible. Now move
     * to index 1, the farthest index we can reach now becomes max(farthest, 1 + nums[1]). We can repeat this process
     * until the end of the array or when i is no longer accessible (i > farthest).
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public boolean canJump(int[] nums) {
            if (nums == null | nums.length == 0) {
                return false;
            }

            int farthest = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (i > farthest) {
                    break;
                }
                farthest = Math.max(farthest, i + nums[i]);
            }

            return farthest >= nums.length - 1;
        }
    }

}
