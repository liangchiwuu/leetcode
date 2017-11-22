package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example,
 * If nums = [1,2,2], a solution is:
 * 
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class SubsetsII {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 2 };
        List<List<Integer>> subsets = new SubsetsII().new Solution().subsetsWithDup(nums);
        System.out.println(subsets);
    }

    /**
     * A DFS recursive solution. The idea of this problem is very similar to Combination Sum II, in order to prevent
     * duplicate subsets, we can only select a repeated number only if all the same numbers beforehand are all selected
     * (select representative).
     * 
     * Note that a WRONG approach is to find everything first, then delete duplicates. This might end up wasting huge
     * resource on repetitive useless calculation.
     * 
     * Time complexity: O(n 2^n)
     */
    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();

            if (nums == null) {
                return results;
            }

            if (nums.length == 0) {
                results.add(new ArrayList<>());
            }

            Arrays.sort(nums);
            dfs(new ArrayList<>(), nums, 0, results);

            return results;
        }

        private void dfs(List<Integer> subset, int[] nums, int offset, List<List<Integer>> results) {
            results.add(new ArrayList<>(subset));

            for (int i = offset; i < nums.length; i++) {
                if (i != offset && nums[i] == nums[i - 1]) {
                    // whenever this happens, we are skipping element 'i-1' and selecting element 'i'
                    continue;
                }

                subset.add(nums[i]);
                dfs(subset, nums, i + 1, results);
                subset.remove(subset.size() - 1);
            }
        }
    }
}
