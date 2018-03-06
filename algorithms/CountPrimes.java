package algorithms;

import java.util.Arrays;

public class CountPrimes {

    public static void main(String[] args) {
        int n = 21;
        int count = new CountPrimes().new Solution().countPrimes(n);
        System.out.println(count);
    }

    /**
     * (Time Limit Exceeded)
     * 
     * A brute-force solution. Simply scan through each integer and accumulate valid ones.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int countPrimes(int n) {
            int count = 0;

            for (int i = 2; i < n; i++) {
                if (isPrime(i)) {
                    count++;
                }
            }

            return count;
        }

        private boolean isPrime(int n) {
            int sqrt = (int) Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * A solution based on the sieve of Eratosthenes.
     * 
     * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
     * 
     * Time complexity: O(n log log n)
     */
    class Solution2 {
        public int countPrimes(int n) {
            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);

            int sqrt = (int) Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++) {
                if (isPrime[i]) {
                    eliminate(isPrime, i);
                }
            }

            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime[i]) {
                    count++;
                }
            }

            return count;
        }

        private void eliminate(boolean[] isPrime, int n) {
            for (int i = n * n; i < isPrime.length; i += n) {
                isPrime[i] = false;
            }
        }
    }

}
