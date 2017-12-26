package algorithms;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * 
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * 
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean result = new ValidPalindrome().new Solution().isPalindrome(s);
        System.out.println(result);
    }

    /**
     * The difference between this problem and a regular palindrome check is to skip invalid characters. The key here is
     * the use of methods from Character class.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public boolean isPalindrome(String s) {
            if (s == null) {
                return false;
            }

            if (s.length() == 0) {
                return true;
            }

            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (!isValid(s.charAt(left))) {
                    left++;
                    continue;
                }
                if (!isValid(s.charAt(right))) {
                    right--;
                    continue;
                }
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }

        private boolean isValid(char c) {
            return Character.isLetter(c) || Character.isDigit(c);
        }
    }

    /**
     * Similar to the previous solution, just here we first filter out all invalid characters, then perform a normal
     * palindrome check.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public boolean isPalindrome(String s) {
            if (s == null) {
                return false;
            }

            if (s.length() == 0) {
                return true;
            }

            s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }
    }

}
