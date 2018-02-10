package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example,
 * If nums = [1,2,3], a solution is:
 * 
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        List<List<Integer>> subsets = new Subsets().new Solution().subsets(nums);
        System.out.println(subsets);
    }

    /**
     * An iterative solution. The idea is to start with an empty set, then add one number at a time. Each time we add a
     * number, the new results will become the combination of:
     * 
     * 1. all sets in previous results
     * 2. all sets in previous results, with the number we just add in each one
     * 
     * For instance, if the original result is []
     * add 1 -> [] + [1] -> [], [1]
     * add 2 -> [], [1] + [2], [1, 2] -> [], [1], [2], [1, 2]
     * add 3 -> [], [1], [2], [1, 2] + [3], [1, 3], [2, 3], [1, 2, 3]
     * -> [], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]
     * ...
     * 
     * Note: this works only when there is no duplicate in the given set.
     * Note2: the time complexity is O(n 2^n) not O(2^n) because the time spend on each subset is not O(1) but O(n)
     * 
     * Time complexity: O(n 2^n)
     */
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> results = new ArrayList<List<Integer>>();

            if (nums == null) {
                return results;
            }

            // optional, if nums is sorted, all subsets are guaranteed in non-descending order
            Arrays.sort(nums);

            results.add(new ArrayList<Integer>());
            for (int e : nums) {
                addNum(results, e);
            }

            return results;
        }

        private void addNum(List<List<Integer>> results, int number) {
            int length = results.size();
            for (int i = 0; i < length; i++) {
                List<Integer> newResult = new ArrayList<Integer>(results.get(i));
                newResult.add(number);
                results.add(newResult);
            }
        }
    }

    /**
     * A DFS solution. Note that this solution satisfy an additional constrain: elements in a subset must be in
     * non-descending order. There are 2 reasons to sort the input at beginning:
     * 
     * 1. to avoid duplication
     * 2. to ensure that the subsets are in non-descending order
     * 
     * The idea is to start the search from an offset index and only look afterwards.
     * 
     * Time complexity: O(n 2^n)
     */
    class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> results = new ArrayList<List<Integer>>();

            if (nums == null) {
                return results;
            }

            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }

            Arrays.sort(nums);
            helper(new ArrayList<Integer>(), nums, 0, results);

            return results;
        }

        /*
         * Three key element of recursion:
         * 
         * 1. definition of recursion: finds all sets that start with 'subset' and add into 'results'
         */
        private void helper(List<Integer> subset, int[] nums, int offset, List<List<Integer>> results) {
            results.add(new ArrayList<Integer>(subset));

            // 2. breakdown of recursion
            for (int i = offset; i < nums.length; i++) {
                // [1] -> [1, 2]
                subset.add(nums[i]);
                // find all sets that start with [1, 2] and add to results
                helper(subset, nums, i + 1, results);
                // [1, 2] -> [1] backtracking
                subset.remove(subset.size() - 1);
            }

            // 3. exit of recursion
            // return;
        }
    }

    /**
     * A solution use bit manipulation. The idea is that, any subset of an array of length n can be represented by a
     * n-bit decimal number. Each digit in this decimal number indicates the existence of nth element.
     * 
     * For instance, for input [1, 2, 3]:
     * 000 -> []
     * 001 -> [3]
     * 010 -> [2]
     * 011 -> [2, 3]
     * ...
     * 
     * Time complexity: O(n 2^n)
     */
    class Solution3 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            int n = nums.length;
            Arrays.sort(nums);

            // 1 << n equals to 2^n
            for (int i = 0; i < (1 << n); i++) {
                List<Integer> subset = new ArrayList<Integer>();

                for (int j = 0; j < n; j++) {
                    // check whether the jth digit in i's binary representation is 1
                    if ((i >> j & 1) == 1) {
                        subset.add(nums[j]);
                    }
                }

                result.add(subset);
            }

            return result;
        }
    }

}
