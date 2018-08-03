package algorithms;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 
 * Example 1:
 * Input: "A"
 * Output: 1
 * 
 * Example 2:
 * Input: "AB"
 * Output: 28
 * 
 * Example 3:
 * Input: "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        String s = "AB";
        int columnNum = new ExcelSheetColumnNumber().new Solution().titleToNumber(s);
        System.out.println(columnNum);
    }

    /**
     * Simply loop backwards, times by multiplier, then calculate sum.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int titleToNumber(String s) {
            int multiplier = 1;
            int columnNum = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                // ASCII code of A is 65
                columnNum += (s.charAt(i) - 64) * multiplier;
                multiplier *= 26;
            }
            return columnNum;
        }
    }
}
