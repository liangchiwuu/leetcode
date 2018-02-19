package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 * 
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * 
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * 
 * Input: S = "12345"
 * Output: ["12345"]
 * 
 * Note:
 * S will be a string with length at most 12.
 * S will consist only of letters or digits.
 * 
 */
public class LetterCasePermutation {

    public static void main(String[] args) {
        String s = "a1b2";
        List<String> results = new LetterCasePermutation().new Solution().letterCasePermutation(s);
        System.out.println(results);
    }

    /**
     * A DFS solution. Define DFS function as follow: find all letter case permutations from start index.
     * 
     * Time complexity: O(n 2^n), since there are 2^n possible answers and O(n) to parse each of them
     */
    class Solution {
        public List<String> letterCasePermutation(String s) {
            List<String> results = new ArrayList<>();

            if (s == null) {
                return results;
            }

            dfs(s.toCharArray(), 0, results);

            return results;
        }

        private void dfs(char[] s, int index, List<String> results) {
            if (index == s.length) {
                results.add(new String(s));
                return;
            }

            dfs(s, index + 1, results);
            if (Character.isLetter(s[index])) {
                s[index] = Character.isUpperCase(s[index])
                        ? Character.toLowerCase(s[index]) : Character.toUpperCase(s[index]);
                dfs(s, index + 1, results);
            }
        }
    }

    /**
     * An iterative solution. The idea is to start with an empty string and loop through each character. If the
     * character is a number, append it to all existing answers; if the character is a letter, copy all current answers
     * and append upper/lower case respectively.
     * 
     * Time complexity: O(n 2^n), since there are 2^n possible answers and O(n) to parse each of them
     */
    class Solution2 {
        public List<String> letterCasePermutation(String s) {
            if (s == null) {
                return new ArrayList<>();
            }

            List<StringBuilder> ans = new ArrayList<>();
            ans.add(new StringBuilder());

            for (char c : s.toCharArray()) {
                if (Character.isLetter(c)) {
                    int n = ans.size();
                    for (int i = 0; i < n; i++) {
                        ans.add(new StringBuilder(ans.get(i)));
                        ans.get(i).append(Character.toUpperCase(c));
                        ans.get(n + i).append(Character.toLowerCase(c));
                    }
                } else {
                    for (StringBuilder sb : ans) {
                        sb.append(c);
                    }
                }
            }

            List<String> results = new ArrayList<>();
            for (StringBuilder sb : ans) {
                results.add(sb.toString());
            }

            return results;
        }
    }

}
