package algorithms;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * 
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * 
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * 
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        boolean isAnagram = new ValidAnagram().new Solution().isAnagram(s, t);
        System.out.println(isAnagram);
    }

    /**
     * A solution with hash. Simply scan through s & t and check if there are anything left in hash. The key here is the
     * understanding of using int[] as HashMap for alphabets. If inputs contain unicode characters, then we have no
     * choice but to use an actual HashMap.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s == null || t == null || s.length() != t.length()) {
                return false;
            }

            int[] hash = new int[256];

            for (int i = 0; i < s.length(); i++) {
                hash[s.charAt(i)]++;
                hash[t.charAt(i)]--;
            }

            for (int count : hash) {
                if (count != 0) {
                    return false;
                }
            }

            return true;
        }
    }

}
