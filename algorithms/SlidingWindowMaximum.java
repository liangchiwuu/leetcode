package algorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * 
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * 
 * Note:
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * 
 * Follow up:
 * Could you solve it in linear time?
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        int[] max = new SlidingWindowMaximum().new Solution2().maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(max));
    }

    /**
     * A brute force solution. Simply slide through the window and loop to find the maximum.
     * 
     * Time complexity: O(n*k)
     */
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }

            int[] res = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length - k + 1; i++) {
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < k; j++) {
                    max = Math.max(max, nums[i + j]);
                }
                res[i] = max;
            }

            return res;
        }
    }

    /**
     * A solution with deque. The idea is to loop through the array and use deque to only keep "promising" elements.
     * 
     * 1. while adding elements nums[i] to deque, we discard all elements smaller than nums[i] from tail, this is
     *    because if there exists nums[x]<nums[i] && x<i, nums[x] can never be the max element in window after nums[i]
     *    comes in, nums[i] will always be a better candidate
     * 2. if an element is out of i-(k-1) range, we discard this element if it exists, note that the only possible
     *    position for this element is the head because of the FIFO nature of deque
     * 3. as a result, the numbers in deque are always in descending order, we can simply peekFirst() to get the max
     * 
     * Time complexity: O(n), since each element is offered and polled once
     */
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }

            int[] ans = new int[nums.length - k + 1];
            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < nums.length; i++) {
                inQueue(deque, nums[i]);
                if (i >= k - 1) {
                    ans[i - k + 1] = deque.peekFirst();
                    outQueue(deque, nums[i - k + 1]);
                }
            }

            return ans;
        }

        private void inQueue(Deque<Integer> deque, int num) {
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.offer(num);
        }

        private void outQueue(Deque<Integer> deque, int num) {
            if (deque.peekFirst() == num) {
                deque.pollFirst();
            }
        }
    }
}
