package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this
 * subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 */
public class LargestDivisibleSubset {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        List<Integer> ans = new LargestDivisibleSubset().new Solution().largestDivisibleSubset(nums);
        System.out.println(ans);
    }

    /**
     * A brute force solution. Simply find all possible divisible subsets, then return the largest. (Time Limit
     * Exceeded)
     * 
     * Time complexity: O(n 2^n)
     */
    class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new ArrayList<>();
            }

            Arrays.sort(nums);

            // find all possible divisible subsets
            ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
            subsets.add(new ArrayList<>());
            for (int num : nums) {
                addNum(subsets, num);
            }

            ArrayList<Integer> largest = new ArrayList<>();
            for (ArrayList<Integer> subset : subsets) {
                if (subset.size() > largest.size()) {
                    largest = subset;
                }
            }

            return largest;
        }

        private void addNum(ArrayList<ArrayList<Integer>> subsets, int num) {
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> newSubset = new ArrayList<Integer>(subsets.get(i));
                if (newSubset.size() == 0 || num % newSubset.get(newSubset.size() - 1) == 0) {
                    newSubset.add(num);
                    subsets.add(newSubset);
                }
            }
        }
    }

    /**
     * A bottom-up dynamic programming solution. The key here is to first sort the array, by doing this we can simplify
     * the problem from finding "largest divisible subset" to finding "longest divisible subsequence". Define f[i] as
     * the size of longest divisible subsequence from i. First, we initialize the f[] array to 1, since each element can
     * clearly be a divisible subsequence by itself. Next, we loop nums[] backwards and check if there exists a latter
     * element that can forms a longer divisible subsequence.
     * 
     * One thing to note here is that, in this question we are asked to find not only the size of the subset, but also
     * the content of the subset. To achieve this, we need another next[] array where next[i] indicates the next index
     * after i that will form a longest divisible subsequence.
     * 
     * Time complexity: O(n^2)
     */
    class Solution2 {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new ArrayList<>();
            }

            Arrays.sort(nums);

            // f[i] indicates the size of largest divisible subset from i
            int[] f = new int[nums.length];
            // next[i] indicates the next element in this subset
            int[] next = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                f[i] = 1;
                next[i] = i;
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] % nums[i] == 0 && f[j] + 1 > f[i]) {
                        f[i] = f[j] + 1;
                        next[i] = j;
                    }
                }
            }

            // find the head of largest divisible subset
            int p = 0;
            for (int i = 0; i < nums.length; i++) {
                if (f[i] > f[p]) {
                    p = i;
                }
            }

            List<Integer> subset = new ArrayList<>();
            subset.add(nums[p]);
            while (next[p] != p) {
                p = next[p];
                subset.add(nums[p]);
            }

            return subset;
        }
    }

}
