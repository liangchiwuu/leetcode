package algorithms;

/**
 * Write a program to check whether a given number is an ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is
 * not ugly since it includes another prime factor 7.
 * 
 * Note that 1 is typically treated as an ugly number.
 */
public class UglyNumber {

    public static void main(String[] args) {
        int num = 8;
        boolean isUgly = new UglyNumber().new Solution().isUgly(num);
        System.out.println(isUgly);
    }

    /**
     * A straightforward solution, simply eliminate each factor and check if the result equals to 1.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public boolean isUgly(int num) {
            while (num >= 2 && num % 2 == 0) {
                num /= 2;
            }
            while (num >= 3 && num % 3 == 0) {
                num /= 3;
            }
            while (num >= 5 && num % 5 == 0) {
                num /= 5;
            }

            return num == 1;
        }
    }

}
