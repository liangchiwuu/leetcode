package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * For example,
 * [1,2,3] have the following permutations:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> permutations = new Permutations().new Solution().permute(nums);
        System.out.println(permutations);
    }

    /**
     * A DFS recursive solution. To solve this problem, let's start with the 3 principles of recursion:
     * 
     * 1. definition: find all permutations that start with permutation then add to results
     * 2. breakdown: for all unselected elements, add one at a time then repeat
     * 3. exit: return when all elements are selected
     * 
     * Time complexity: O(n!)
     */
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();

            if (nums == null) {
                return results;
            }

            dfs(nums, new HashSet<>(), new ArrayList<>(), results);

            return results;
        }

        // goal: find all permutations that start with permutation then add to results
        private void dfs(int[] nums, Set<Integer> searched, List<Integer> permutation, List<List<Integer>> results) {
            if (permutation.size() == nums.length) {
                results.add(new ArrayList<>(permutation));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (searched.contains(nums[i])) {
                    continue;
                }
                permutation.add(nums[i]);
                searched.add(nums[i]);
                dfs(nums, searched, permutation, results);
                permutation.remove(permutation.size() - 1);
                searched.remove(nums[i]);
            }
        }
    }
}
