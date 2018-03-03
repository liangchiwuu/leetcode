package algorithms;

public class ValidParenthesisString {

    public static void main(String[] args) {
        String s = "(*))";
        boolean isValid = new ValidParenthesisString().new Solution().checkValidString(s);
        System.out.println(isValid);
    }

    /**
     * A dynamic programming solution. Let dp[i][j] indicates whether substring s[i:j-1] is valid. Then, dp[i][j] is
     * true if one of the following is also true:
     * 
     * 1. s[i] is '*' and s[i+1:j-1] is valid
     * 2. s[i] can be made as '(', and exists k where i < k < j such that s[k] can be made as ')' and s[i+1:k] and
     *    s[k+1:j] are both valid
     * 
     * Time complexity: O(n^3)
     */
    class Solution {
        public boolean checkValidString(String s) {
            if (s == null) {
                return false;
            }
            if (s.length() == 0) {
                return true;
            }

            int n = s.length();
            boolean[][] dp = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '*') {
                    dp[i][i] = true;
                }
                if (i < n - 1
                        && (s.charAt(i) == '(' || s.charAt(i) == '*')
                        && (s.charAt(i + 1) == ')' || s.charAt(i + 1) == '*')) {
                    dp[i][i + 1] = true;
                }
            }

            for (int size = 2; size < n; size++) {
                for (int i = 0; i + size < n; i++) {
                    if (s.charAt(i) == '*' && dp[i + 1][i + size] == true) {
                        // check first condition
                        dp[i][i + size] = true;
                    } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                        // check second condition
                        for (int k = i + 1; k <= i + size; k++) {
                            if ((s.charAt(k) == ')' || s.charAt(k) == '*')
                                    && (k == i + 1 || dp[i + 1][k - 1] == true)
                                    && (k == i + size || dp[k + 1][i + size] == true)) {
                                dp[i][i + size] = true;
                                break;
                            }
                        }
                    }
                }
            }

            return dp[0][n - 1];
        }
    }

    /**
     * A greedy solution. The idea is to only keep track of the 'balance' of left brackets. For example, string '(()())'
     * will have a sequence of balances of "1, 2, 1, 2, 1, 0" as we parse through the string:
     * 
     * ( -> 1 (has one extra left bracket)
     * (( -> 2 (has two extra left brackets)
     * (() -> 1
     * (()( -> 2
     * (()() -> 1
     * (()()) -> 0 (has no left brackets left -> valid)
     * 
     * Then what about '*' sign? since a star sign can be either left, right, or empty, it will actually expand the
     * possibilities of the balance. Take '(***)' as example:
     * 
     * ( -> 1
     * (* -> 0, 1, 2 (it's possible to have either 0/1/2 extra left brackets)
     * (** -> 0, 1, 2, 3
     * (*** -> 0, 1, 2, 3, 4
     * (***) -> 0, 1, 2, 3 (since 0 is one of the possibilities, string is valid)
     * 
     * So, we can use two low/high pointers to indicate the range of possible balance conditions. If at any point the
     * highest balance possibility falls below zero, than this string is invalid. Otherwise, check if we can have
     * exactly zero left brackets left.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public boolean checkValidString(String s) {
            if (s == null) {
                return false;
            }
            if (s.length() == 0) {
                return true;
            }

            int low = 0;
            int high = 0;

            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case '(':
                        low++;
                        high++;
                        break;
                    case ')':
                        low--;
                        high--;
                        break;
                    case '*':
                        low--;
                        high++;
                        break;
                }

                low = Math.max(low, 0);
                if (high < 0) {
                    return false;
                }
            }

            return low == 0;
        }
    }

}
