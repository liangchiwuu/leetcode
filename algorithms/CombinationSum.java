package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C
 * where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [
 * [7],
 * [2, 2, 3]
 * ]
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        List<List<Integer>> results = new CombinationSum().new Solution().combinationSum(candidates, target);
        System.out.println(results);
    }

    /**
     * A classic DFS recursive solution. To solve this problem, we need to start from the 3 principles of recursion:
     * 
     * 1. the definition of recursion: start from a specific index, find all the combinations that add up to the remain
     * target and add to the results.
     * 
     * 2. the breakdown of recursion: from a specific index, loop to the end of candidates and add one element at a
     * time, then repeat for each of them.
     * 
     * 3. the exit of recursion: the point to stop and return, here we should end the search when remain target equals
     * to zero.
     * 
     * Time complexity: O(# of solutions * time complexity for each solution). # of solutions is at most # of subsets
     * which is 2^n. Time complexity for each solution is greatly determined by target, for instance, it takes at least
     * 1000 loops if given candidates [1] and target 1000. Some discussions:
     * http://www.1point3acres.com/bbs/thread-117602-1-1.html
     * https://discuss.leetcode.com/topic/25900/if-asked-to-discuss-the-time-complexity-of-your-solution-what-would-you-say
     */
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if (candidates == null || candidates.length == 0) {
                return results;
            }

            Arrays.sort(candidates);
            dfs(candidates, 0, new ArrayList<Integer>(), target, results);

            return results;
        }

        // 1. the definition of recursion
        private void dfs(int[] candidates, int startIndex, List<Integer> combination, int remainTarget,
                List<List<Integer>> results) {

            // 3. the exit of recursion
            if (remainTarget == 0) {
                // making a deep copy is necessary here
                results.add(new ArrayList<>(combination));
                return;
            }

            // 2. the breakdown of recursion
            for (int i = startIndex; i < candidates.length; i++) {
                if (remainTarget < candidates[i]) {
                    break;
                }

                combination.add(candidates[i]);
                // note here we put i instead of i+1 because one element is allowed to be selected multiple times
                dfs(candidates, i, combination, remainTarget - candidates[i], results);
                combination.remove(combination.size() - 1);
            }
        }
    }

}
