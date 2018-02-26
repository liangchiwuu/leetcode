package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * 
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than
 * 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * 
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> results = new FindAllAnagramsInAString().new Solution().findAnagrams(s, p);
        System.out.println(results);
    }

    /**
     * A brute-force solution.
     * 
     * Time complexity: O(m * n)
     */
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> results = new ArrayList<>();

            for (int i = 0; i < s.length() - p.length() + 1; i++) {
                if (isAnagram(s.substring(i, i + p.length()), p)) {
                    results.add(i);
                }
            }

            return results;
        }

        private boolean isAnagram(String a, String b) {
            int[] count = new int[26];
            for (int i = 0; i < a.length(); i++) {
                count[a.charAt(i) - 'a']++;
                count[b.charAt(i) - 'a']--;
            }
            for (int i : count) {
                if (i != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * A solution using sliding window and hash. The idea is to use a hash table to record the demand of each character
     * compare to target p. For a character c, hash[c] > 0 means we need more c to satisfy target p, and vice versa.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> results = new ArrayList<>();
            int[] hash = new int[26];

            // initialize hash
            for (int i = 0; i < p.length(); i++) {
                hash[p.charAt(i) - 'a']++;
            }

            int start = 0;
            int end = 0;
            int matched = 0;
            while (end < s.length()) {
                // update hash
                if (hash[s.charAt(end) - 'a'] > 0) {
                    matched++;
                }
                hash[s.charAt(end) - 'a']--;
                end++;

                // check if matched
                if (matched == p.length()) {
                    results.add(start);
                }

                // shift window
                if (end - start == p.length()) {
                    if (hash[s.charAt(start) - 'a'] >= 0) {
                        matched--;
                    }
                    hash[s.charAt(start) - 'a']++;
                    start++;
                }
            }

            return results;
        }
    }

}
