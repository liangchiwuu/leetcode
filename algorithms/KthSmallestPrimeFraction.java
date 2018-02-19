package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * A sorted list A contains 1, plus some number of primes. Then, for every p < q in the list, we consider the fraction
 * p/q.
 * 
 * What is the K-th smallest fraction considered? Return your answer as an array of ints, where answer[0] = p and
 * answer[1] = q.
 * 
 * Examples:
 * Input: A = [1, 2, 3, 5], K = 3
 * Output: [2, 5]
 * Explanation:
 * The fractions to be considered in sorted order are: 1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
 * The third smallest fraction is 2/5.
 * 
 * Input: A = [1, 7], K = 1
 * Output: [1, 7]
 * 
 * Note:
 * A will have length between 2 and 2000.
 * Each A[i] will be between 1 and 30000.
 * K will be between 1 and A.length * (A.length + 1) / 2.
 */
public class KthSmallestPrimeFraction {

    public static void main(String[] args) {
        int[] primes = { 1, 2, 3, 5 };
        int k = 3;
        int[] res = new KthSmallestPrimeFraction().new Solution().kthSmallestPrimeFraction(primes, k);
        System.out.println(Arrays.toString(res));
    }

    /**
     * A solution using heap. Let's take [1, 2, 3, 5, 7] as an example, we can easily have the following relationships
     * between all possible fractions:
     * 
     * 1/7 < 1/5 < 1/3 < 1/2
     * 2/7 < 2/5 < 2/3
     * 3/7 < 3/5
     * 5/7
     * 
     * Then this problem simply turns into a 'merge k sorted list' problem. We can use a heap to repetitively find the
     * minimum head from all lists until reach the k-th element. Note that here we store the 'index' of p/q instead of
     * the value of p/q.
     * 
     * Time complexity: O(k log n)
     */
    class Solution {
        public int[] kthSmallestPrimeFraction(int[] primes, int k) {
            PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    double aVal = (double) primes[a[0]] / primes[a[1]];
                    double bBal = (double) primes[b[0]] / primes[b[1]];
                    return Double.compare(aVal, bBal);
                }
            });

            for (int pIndex = 0; pIndex < primes.length - 1; pIndex++) {
                heap.offer(new int[] { pIndex, primes.length - 1 });
            }

            int count = 0;
            int[] fraction = new int[2];
            while (count < k && !heap.isEmpty()) {
                fraction = heap.poll();
                if (fraction[1] > fraction[0] + 1) {
                    heap.offer(new int[] { fraction[0], fraction[1] - 1 });
                }
                count++;
            }

            return new int[] { primes[fraction[0]], primes[fraction[1]] };
        }
    }

}
