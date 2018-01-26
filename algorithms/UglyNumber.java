package algorithms;

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
