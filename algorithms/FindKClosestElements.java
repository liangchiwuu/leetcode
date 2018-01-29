package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be
 * sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * 
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * 
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 10^4
 * Absolute value of elements in the array and x will not exceed 10^4
 */
public class FindKClosestElements {

    public static void main(String[] args) {
        int[] arr = { 0, 0, 1, 2, 3, 3, 4, 7, 7, 8 };
        int k = 3;
        int x = 5;
        List<Integer> result = new FindKClosestElements().new Solution().findClosestElements(arr, k, x);
        System.out.println(result);
    }

    /**
     * A solution with heap. The idea is to define a heap that satisfy the requirements, then add each element to it.
     * Since only k closest elements are required, we can safely remove (poll) the farthest element when the heap size
     * is greater than k. It takes O(log k) time to add each element to the heap, and the sorting takes O(k log k) time,
     * so the time complexity becomes O(n log k) + O(k log k) = O((n+k) log k).
     * 
     * Time complexity: O((n+k) log k)
     */
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            if (arr == null || arr.length == 0) {
                return new ArrayList<>();
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    int dist = Math.abs(b - x) - Math.abs(a - x);
                    if (dist == 0) {
                        return b - a;
                    }
                    return dist;
                }
            });

            for (int num : arr) {
                maxHeap.offer(num);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }

            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                res.add(maxHeap.poll());
            }
            Collections.sort(res);

            return res;
        }
    }

    /**
     * A solution with binary search. The idea is to first find the element that is closest to x, than expand until we
     * have k elements. Note that this is based on the fact that the input array is previously sorted.
     * 
     * Time complexity: O(log n + k)
     */
    class Solution2 {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            if (arr == null || arr.length == 0) {
                return new ArrayList<>();
            }

            // find the element closest to x
            int start = 0;
            int end = arr.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (arr[mid] >= x) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            int index;
            if (Math.abs(arr[start] - x) < Math.abs(arr[end] - x)) {
                index = start;
            } else {
                index = end;
            }

            // starts from index, expand until there are k value
            int low = index;
            int high = index;
            while (high - low < k - 1) {
                if (low == 0) {
                    high++;
                } else if (high == arr.length - 1) {
                    low--;
                } else {
                    if (Math.abs(arr[high + 1] - x) < Math.abs(arr[low - 1] - x)) {
                        high++;
                    } else {
                        low--;
                    }
                }
            }

            List<Integer> res = new ArrayList<>();
            for (int i = low; i <= high; i++) {
                res.add(arr[i]);
            }

            return res;
        }
    }

}
