package algorithms;

/**
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different
 * from X. A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5
 * rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number.
 * 
 * Now given a positive number N, how many numbers X from 1 to N are good?
 * 
 * Example:
 * Input: 10
 * Output: 4
 * Explanation:
 * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * 
 * Note:
 * N will be in range [1, 10000].
 */
public class RotatedDigits {

    public static void main(String[] args) {
        int n = 10;
        int count = new RotatedDigits().new Solution().rotatedDigits(n);
        System.out.println(count);
    }

    /**
     * A brute-force solution. Simply loop through each number and check if it's valid.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int rotatedDigits(int n) {
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (isValid(i)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isValid(int n) {
            boolean isDifferent = false;
            while (n > 0) {
                switch (n % 10) {
                    case 3:
                    case 4:
                    case 7:
                        return false;
                    case 2:
                    case 5:
                    case 6:
                    case 9:
                        isDifferent = true;
                        break;
                }
                n /= 10;
            }
            return isDifferent;
        }
    }
}
