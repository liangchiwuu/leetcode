package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * 
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class PermutationsII {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };
        List<List<Integer>> permutations = new PermutationsII().new Solution().permuteUnique(nums);
        System.out.println(permutations);
    }

    /**
     * A DFS recursive solution. The key here is how to avoid duplicate permutations. Similar to Subsets II and
     * Combination Sum II, we need to select representative. This means when 2 identical element, the later one can only
     * be selected if the previous one is selected as well.
     * 
     * Example:
     * given [a1, a2, b] where a1 and a2 have same value
     * permutation [b, a1, a2] -> OK
     * permutation [b, a2, a1] -> NOT OK since a2 can only come after a1
     * 
     * This way we can ensure that there will be no duplications in result.
     * 
     * Time complexity: O(n!)
     */
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();

            if (nums == null) {
                return results;
            }

            // sort in necessary to group the same values together
            Arrays.sort(nums);
            dfs(nums, new boolean[nums.length], new ArrayList<>(), results);

            return results;
        }

        private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
            if (permutation.size() == nums.length) {
                results.add(new ArrayList<>(permutation));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    // means this element is already in the permutation
                    continue;
                }

                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    // means element 'i-1' is skipped
                    continue;
                }

                permutation.add(nums[i]);
                visited[i] = true;
                dfs(nums, visited, permutation, results);
                permutation.remove(permutation.size() - 1);
                visited[i] = false;
            }
        }
    }

}
