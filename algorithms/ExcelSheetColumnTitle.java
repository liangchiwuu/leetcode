package algorithms;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * 
 * Example 1:
 * Input: 1
 * Output: "A"
 * 
 * Example 2:
 * Input: 28
 * Output: "AB"
 * 
 * Example 3:
 * Input: 701
 * Output: "ZY"
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        int n = 26;
        String columnCode = new ExcelSheetColumnTitle().new Solution().convertToTitle(n);
        System.out.println(columnCode);
    }

    /**
     * Simply base conversion, the only case to notice is 26 need to be parse as 'Z'.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public String convertToTitle(int n) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                // ASCII code of A is 65
                sb.append((char) ((n - 1) % 26 + 65));
                n = (n - 1) / 26;
            }
            return sb.reverse().toString();
        }
    }
}
