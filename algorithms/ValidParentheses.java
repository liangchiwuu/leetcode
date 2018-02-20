package algorithms;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "()[]{}";
        boolean isValid = new ValidParentheses().new Solution().isValid(s);
        System.out.println(isValid);
    }

    /**
     * A solution with stack. Simply scan through each character, whenever we see a right bracket, it must form a
     * parentheses with the head of stack.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public boolean isValid(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case ')':
                        if (stack.isEmpty() || stack.pop() != '(') {
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.pop() != '[') {
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.isEmpty() || stack.pop() != '{') {
                            return false;
                        }
                        break;
                    default:
                        stack.push(s.charAt(i));
                }
            }

            return stack.isEmpty();
        }
    }
}
