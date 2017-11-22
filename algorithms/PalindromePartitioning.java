package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * 
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> results = new PalindromePartitioning().new Solution().partition(s);
        System.out.println(results);
    }

    /**
     * A DFS recursive solution. The start index here represents the initial position to split the string.
     * 
     * One thing that can be optimized here is the isPalindrome() function. Since the # of sub-strings for given string
     * s is fixed, we can first pre-process every sub-string of s and store the results in a table. This way while doing
     * the DFS we only spend O(1) instead of O(n) to verify a palindrome.
     * 
     * Time complexity: O(n * 2^n), n for verify a palindrome, 2^n for finding all combinations
     */
    class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> results = new ArrayList<>();

            if (s == null) {
                return results;
            }

            List<String> partition = new ArrayList<>();
            dfs(s, 0, partition, results);

            return results;
        }

        private void dfs(String s, int startIndex, List<String> partition, List<List<String>> results) {
            if (startIndex == s.length()) {
                results.add(partition);
            }

            for (int i = startIndex; i < s.length(); i++) {
                String subString = s.substring(startIndex, i + 1);
                if (!isPalindrome(subString)) {
                    continue;
                }
                partition.add(subString);
                dfs(s, i + 1, partition, results);
                partition.remove(partition.size() - 1);
            }
        }

        private boolean isPalindrome(String s) {
            for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }
}
