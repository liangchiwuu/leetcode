package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
        List<List<Integer>> permutations = new Permutations().new Solution2().permute(nums);
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

    /**
     * A BFS solution. The idea is to start with an empty result, add one new number at a time then find new results.
     * When a new number is added, each permutation in old results will create [length of permutation + 1] new
     * permutations.
     * 
     * Example:
     * [] -> add 1 -> [1]
     * [1] -> add 2 -> [1, 2], [2, 1]
     * [1, 2], [2, 1] -> add 3 -> [1, 2, 3], [1, 3, 2], [3, 1, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1]
     * ^^^^^^^^^^^^^^ each permutation has 2+1 different place to insert new number, giving 6 (=2*3) new permutations
     * ...
     * 
     * Time complexity: O(n!)
     */
    class Solution2 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();

            if (nums == null) {
                return results;
            }

            results.add(new LinkedList<>());
            for (int num : nums) {
                results = addNum(results, num);
            }

            return results;
        }

        // goal: given current results, find new results after add a new number
        private List<List<Integer>> addNum(List<List<Integer>> results, int num) {
            List<List<Integer>> newResults = new ArrayList<>();
            for (List<Integer> permutation : results) {
                // for a permutation with length 'i', add a number will give 'i+1' new permutations
                for (int i = 0; i < permutation.size() + 1; i++) {
                    List<Integer> newPermutation = new LinkedList<>(permutation);
                    newPermutation.add(i, num);
                    newResults.add(newPermutation);
                }
            }
            return newResults;
        }
    }
}
