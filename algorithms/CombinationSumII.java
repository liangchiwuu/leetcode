package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 */
public class CombinationSumII {

    public static void main(String[] args) {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        List<List<Integer>> results = new CombinationSumII().new Solution().combinationSum2(candidates, target);
        System.out.println(results);
    }

    /**
     * A DFS solution. This problem differs from Combination Sum in 2 aspects:
     * 
     * 1. each number may only be used once
     * 2. the candidates here may contain duplicate numbers
     * 
     * To solve criteria #1, we should always add the start index by 1 (use i+1 instead of i) while entering next level
     * search to ensure that the same element will not be chosen again.
     * 
     * Solving criteria #2 is a bit tricky. Here we use a method called 'select representative.' This basically means we
     * can only select a repeated number only if all the same numbers beforehand are all selected (from a sorted array).
     * For instance: given candidates [1, 1, 2] and target 3, the second '1' can only be selected when the first '1' is
     * also selected. This prevents the duplicate combinations in solution.
     *
     * Time complexity: O(# of solutions * time complexity for each solution). # of solutions is at most # of subsets
     * which is 2^n. Time complexity for each solution is greatly determined by target, for instance, it takes at least
     * 1000 loops if given candidates [1] and target 1000. Some discussions:
     * http://www.1point3acres.com/bbs/thread-117602-1-1.html
     * https://discuss.leetcode.com/topic/25900/if-asked-to-discuss-the-time-complexity-of-your-solution-what-would-you-say
     */
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> results = new ArrayList<>();
            if (candidates == null || candidates.length == 0) {
                return results;
            }

            Arrays.sort(candidates);
            dfs(0, target, candidates, new ArrayList<>(), results);

            return results;
        }

        private void dfs(int startIndex, int remainTarget, int[] candidates, List<Integer> combination,
                List<List<Integer>> results) {
            if (remainTarget == 0) {
                results.add(new ArrayList<>(combination));
                return;
            }

            for (int i = startIndex; i < candidates.length; i++) {
                if (i != startIndex && candidates[i] == candidates[i - 1]) {
                    // whenever this happens, we are skipping element 'i-1' and selecting element 'i'
                    continue;
                }

                if (remainTarget < candidates[i]) {
                    break;
                }

                combination.add(candidates[i]);
                // plus the start index by 1 to prevent selecting same element twice
                dfs(i + 1, remainTarget - candidates[i], candidates, combination, results);
                combination.remove(combination.size() - 1);
            }
        }
    }
}
