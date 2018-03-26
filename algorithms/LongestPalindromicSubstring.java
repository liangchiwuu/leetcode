package algorithms;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "cbbd";
        String ans = new LongestPalindromicSubstring().new Solution().longestPalindrome(s);
        System.out.println(ans);
    }

    /**
     * The idea of this solution is to use each index as the 'center' of a palindrome, then try to expand around it. The
     * key is that the center of a palindrome could be either one single character or two identical characters.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }

            int center = 0;
            int maxSize = 1;
            for (int i = 0; i < s.length(); i++) {
                int size = Math.max(expand(s, i, i), expand(s, i, i + 1));
                if (size > maxSize) {
                    center = i;
                    maxSize = size;
                }
            }

            return s.substring(center - (maxSize - 1) / 2, center + maxSize / 2 + 1);
        }

        private int expand(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }
    }

    /**
     * A bottom-up dynamic programming solution. Define dp[i][j] as whether s(i, j) is a palindrome. First initialize
     * the condition where i=j and i+1=j, then calculate through the dp array with bottom-up order.
     * 
     * Time complexity: O(n^2)
     */
    class Solution2 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }

            int n = s.length();
            boolean[][] dp = new boolean[n][n];

            // initialize dp array
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }
            for (int i = 0; i < n - 1; i++) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            }

            // bottom-up dynamic programming
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }

            int start = 0;
            int len = 1;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (dp[i][j] && j - i + 1 > len) {
                        start = i;
                        len = j - i + 1;
                    }
                }
            }

            return s.substring(start, start + len);
        }
    }

    /**
     * A solution using Manacher's algorithm.
     * 
     * https://articles.leetcode.com/longest-palindromic-substring-part-ii/
     * https://www.felix021.com/blog/read.php?2040
     * 
     * Time complexity: O(n)
     */
    class Solution3 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }

            return "";
        }
    }

}
