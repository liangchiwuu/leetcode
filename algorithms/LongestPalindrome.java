package algorithms;

/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can
 * be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * 
 * Example:
 * Input:
 * "abccccdd"
 * Output:
 * 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "abccccdd";
        int ans = new LongestPalindrome().new Solution().longestPalindrome(s);
        System.out.println(ans);
    }

    /**
     * A solution with hash. In order to find the length of longest palindrome, we must count the occurrence of
     * characters that appears in pair and individually. We can simply use an integer array to record the frequency of
     * each character and then loop again to summarize the result.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int[] hash = new int[256];
            for (char c : s.toCharArray()) {
                hash[c]++;
            }

            int pair = 0;
            int single = 0;
            for (int e : hash) {
                pair += e / 2;
                single += e % 2;
            }

            return pair * 2 + Math.min(1, single);
        }
    }

}
