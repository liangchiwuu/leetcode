package algorithms;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * 
 * Example:
 * s = "leetcode"
 * return 0.
 * 
 * Example:
 * s = "loveleetcode",
 * return 2.
 * 
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        String s = "loveleetcode";
        int res = new FirstUniqueCharacterInAString().new Solution().firstUniqChar(s);
        System.out.println(res);
    }

    /**
     * A 2-pass solution with hash. Simply count the occurrences of each character then scan again to find the first
     * unique one. Normally speaking, an integer array of size 256 is enough for mapping characters. You can always use
     * a hash map instead to guarantee the support of all possible characters.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int firstUniqChar(String s) {
            if (s == null || s.length() == 0) {
                return -1;
            }

            int[] freq = new int[256];
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i)]++;
            }

            for (int i = 0; i < s.length(); i++) {
                if (freq[s.charAt(i)] == 1) {
                    return i;
                }
            }
            return -1;
        }
    }

}
