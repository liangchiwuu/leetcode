package algorithms;

/**
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
 * 
 * Example 1:
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 * 
 * Example 2:
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d]+101[e]+101[e] to the sum. Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 * 
 * Note:
 * 0 < s1.length, s2.length <= 1000.
 * All elements of each string will have an ASCII value in [97, 122].
 */
public class MinimumAsciiDeleteSumForTwoStrings {

    public static void main(String[] args) {
        String s1 = "delete";
        String s2 = "leet";
        int deleteSum = new MinimumAsciiDeleteSumForTwoStrings().new Solution().minimumDeleteSum(s1, s2);
        System.out.println(deleteSum);
    }

    /**
     * A bottom-up dynamic programming solution. Let's define f[i][j] as the minimum ascii delete sum for s1[i:] and
     * s2[j:]. Then, we can know that:
     * 
     * 1. when one string is empty, the answer would simply be the ascii sum of the other
     * 2. when s1.charAt(i) == s2.charAt(j), f[i][j] will equal to f[i + 1][j + 1]
     * 3. when s1.charAt(i) != s2.charAt(j), we will need to delete either s1.charAt(i) or s2.charAt(j), which yields:
     * ---> if delete s1.charAt(i): f[i][j] = f[i + 1][j] + s1.codePointAt(i)
     * ---> if delete s2.charAt(j): f[i][j] = f[i][j + 1] + s2.codePointAt(j)
     * ---> pick the smaller one
     * 
     * Time complexity: O(m*n)
     */
    class Solution {
        public int minimumDeleteSum(String s1, String s2) {
            int[][] f = new int[s1.length() + 1][s2.length() + 1];

            // initialization
            for (int i = s1.length() - 1; i >= 0; i--) {
                f[i][s2.length()] = f[i + 1][s2.length()] + s1.codePointAt(i);

            }
            for (int j = s2.length() - 1; j >= 0; j--) {
                f[s1.length()][j] = f[s1.length()][j + 1] + s2.codePointAt(j);
            }

            for (int i = s1.length() - 1; i >= 0; i--) {
                for (int j = s2.length() - 1; j >= 0; j--) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        f[i][j] = f[i + 1][j + 1];
                    } else {
                        f[i][j] = Math.min(f[i + 1][j] + s1.codePointAt(i), f[i][j + 1] + s2.codePointAt(j));
                    }
                }
            }

            return f[0][0];
        }
    }

}
