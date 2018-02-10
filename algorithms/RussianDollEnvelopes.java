package algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into
 * another if and only if both the width and height of one envelope is greater than the width and height of the other
 * envelope.
 * 
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * 
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] =>
 * [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {

    public static void main(String[] args) {
        int[][] envelopes = new int[][] { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
        int maxEnvelopes = new RussianDollEnvelopes().new Solution().maxEnvelopes(envelopes);
        System.out.println(maxEnvelopes);
    }

    /**
     * A dynamic programming solution. The idea is:
     * 
     * 1. sort the array in 'ascending' on width and 'descending' on height when width are the same
     * 2. find the Longest Increasing Subsequence based on height
     * 
     * The key here is that the height must be in 'descending' order while width are the same. The reason is that since
     * [6, 4] cannot contain [6, 7], we need to put [6, 7] before [6, 4] so that [6, 4] -> [6, 7] will not appear in our
     * LIS.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes == null || envelopes.length == 0) {
                return 0;
            }

            Arrays.sort(envelopes, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
                }
            });

            // initialization
            int[] f = new int[envelopes.length];
            for (int i = 0; i < envelopes.length; i++) {
                f[i] = 1;
            }

            for (int i = envelopes.length - 1; i >= 0; i--) {
                for (int j = i; j < envelopes.length; j++) {
                    if (envelopes[j][1] > envelopes[i][1]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                    }
                }
            }

            // find max f
            int max = Integer.MIN_VALUE;
            for (int num : f) {
                max = Math.max(num, max);
            }

            return max;
        }
    }

    /**
     * A dynamic programming + binary search solution. The idea is the same as the previous solution. Just we optimize
     * the dynamic programming process using the same method as in Longest Increasing Subsequence.
     * 
     * Time complexity: O(n log n)
     */
    class Solution2 {
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes == null || envelopes.length == 0) {
                return 0;
            }

            Arrays.sort(envelopes, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
                }
            });

            // initialization
            int[] f = new int[envelopes.length];
            for (int i = 0; i < envelopes.length; i++) {
                f[i] = Integer.MAX_VALUE;
            }

            for (int[] envelope : envelopes) {
                // find the first index in f >= envelope[1]
                int index = binarySearch(f, envelope[1]);
                f[index] = envelope[1];
            }

            for (int i = f.length - 1; i >= 0; i--) {
                if (f[i] != Integer.MAX_VALUE) {
                    return i + 1;
                }
            }

            return 0;
        }

        private int binarySearch(int[] f, int num) {
            int start = 0;
            int end = f.length - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (f[mid] < num) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            return f[start] >= num ? start : end;
        }
    }

}
