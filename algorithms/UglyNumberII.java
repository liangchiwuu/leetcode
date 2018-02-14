package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 */
public class UglyNumberII {

    public static void main(String[] args) {
        int n = 10;
        int uglyNumber = new UglyNumberII().new Solution().nthUglyNumber(n);
        System.out.println(uglyNumber);
    }

    /**
     * Repeatedly find the next ugly number until there are n ugly numbers. The key here is how to find the next ugly
     * number. By definition, we know that the next ugly number must be "one of the existing ugly numbers" times either
     * 2 or 3 or 5. So we can find the minimum number that > current max divided by 2/3/5, then multiply by 2/3/5
     * respectively to make then just bigger then current max, then the minimum among these three will be the next ugly
     * number.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int nthUglyNumber(int n) {
            List<Integer> uglys = new ArrayList<>();
            uglys.add(1);

            while (uglys.size() < n) {
                int nextUgly = findNext(uglys);
                uglys.add(nextUgly);
            }

            return uglys.get(n - 1);
        }

        private int findNext(List<Integer> uglys) {
            int last = uglys.get(uglys.size() - 1);
            int p2 = 0;
            int p3 = 0;
            int p5 = 0;

            while (uglys.get(p2) * 2 <= last) {
                p2++;
            }
            while (uglys.get(p3) * 3 <= last) {
                p3++;
            }
            while (uglys.get(p5) * 5 <= last) {
                p5++;
            }

            return Math.min(uglys.get(p2) * 2, Math.min(uglys.get(p3) * 3, uglys.get(p5) * 5));
        }
    }

    /**
     * A solution with heap data structure. The idea is to keep generating new ugly numbers and pop the smallest at the
     * same time. The key here is that, since we will add (if no repeat) 3 new ugly numbers each time while pop 1, it is
     * likely that the biggest ugly number will exceed the limit of integer, so we would need to use long instead of
     * integer.
     * 
     * Time complexity: O(n log n)
     */
    class Solution2 {
        private final int[] PRIMES = { 2, 3, 5 };

        public int nthUglyNumber(int n) {
            Queue<Long> queue = new PriorityQueue<>();
            Set<Long> set = new HashSet<>();

            queue.add(Long.valueOf(1));
            set.add(Long.valueOf(1));

            Long ugly = Long.valueOf(1);
            for (int i = 0; i < n; i++) {
                ugly = queue.poll();
                for (int prime : PRIMES) {
                    Long newUgly = ugly * prime;
                    if (!set.contains(newUgly)) {
                        queue.add(newUgly);
                        set.add(newUgly);
                    }
                }
            }

            return ugly.intValue();
        }
    }

}
