package algorithms;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2 };
        int[] nums2 = { 3, 4 };
        double median = new MedianOfTwoSortedArrays().new Solution().findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }

    /**
     * Before we start, note that the difficulty of this problem is really more on the algorithm design rather than
     * programming, so we are going to focus on the deriving process. The challenge here is how to satisfy the
     * requirement UNDER O(n) time complexity.
     * 
     * First, what function do we need to find the the mean? Given an array A with length k, the median of this array is
     * either A[k/2+1] when there are odd numbers, or (A[k/2] + A[k/2+1]) / 2 when there are even numbers. To be more
     * specific: we need to find "element at index k/2+1" or "element at index k/2 and k/2+1." So our problem now
     * becomes: how to find the k-th smallest element from two sorted arrays?
     * 
     * Next, we can almost say that our goal is to optimize the process to O(log n) complexity if we start from O(n).
     * But what does O(log n) time complexity mean? An O(log n) complexity basically means we have to transfer a size n
     * problem into a size n/2 problem within O(1) of time, which is: T(n) = T(n/2) + O(1). And since our goal is to
     * write a function to find the k-th (smallest) number from two sorted lists: findKth(A, B, k), one way to cut the
     * size of this problem into half is to continue next level search with something like: findKth(A, B, k/2). So how
     * can we transfer from k to k/2?
     * 
     * The idea is, if we compare the k/2-th smallest element in A, i.e. A[k/2-1], and the k-th smallest element in B,
     * i.e. B[k/2 - 1], there are three possible situation:
     * 1. A[k/2-1] > B[k/2-1]
     * 2. A[k/2-1] < B[k/2-1]
     * 3. A[k/2-1] = B[k/2-1]
     * Let's first look at case 1. When B[k/2-1] is smaller than A[k/2-1], it means all the elements from B[0] to
     * B[k/2-1] can never be the k-th element in A+B, and we can safely drop the first k/2 elements from B.
     * 
     * But why? It's because since we already know that A[k/2-1] > B[k/2-1], and both arrays are sorted, this means that
     * there are at most k/2-1 (from A) + k/2-1 (from B) elements that are smaller than B[k/2-1]. In other words, there
     * are at most k-2 elements are smaller than B[k/2-1]. And since the k-th element are greater than k-1 elements in
     * A+B, B[k/2-1] and all the elements in front of it are guaranteed to be smaller than the k-th element in A+B.
     * 
     * Finally we just need to consider the edge cases:
     * 1. if A/B is empty, return B[k-1]/A[k-1]
     * 2. if k equals to 1, return min(A[0], B[0])
     * 3. if A/B has less than k/2 elements, drop from B/A
     * 
     * Also read:
     * http://blog.csdn.net/zxzxy1988/article/details/8587244
     * http://blog.csdn.net/yutianzuijin/article/details/11499917
     * 
     * Time complexity: O(log(m + n))
     */
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len = nums1.length + nums2.length;
            if (len % 2 == 1) {
                return findKth(nums1, 0, nums2, 0, len / 2 + 1);
            } else {
                double left = findKth(nums1, 0, nums2, 0, len / 2);
                System.out.println(left);
                double right = findKth(nums1, 0, nums2, 0, len / 2 + 1);
                System.out.println(right);
                return (left + right) / 2;
            }
        }

        private int findKth(int[] a, int aHead, int[] b, int bHead, int k) {
            if (aHead >= a.length) {
                return b[bHead + k - 1];
            }
            if (bHead >= b.length) {
                return a[aHead + k - 1];
            }

            if (k == 1) {
                return Math.min(a[aHead], b[bHead]);
            }

            // if has less than k/2 elements, assign max value so we can drop from another
            int aKey = aHead + k / 2 - 1 < a.length ? a[aHead + k / 2 - 1] : Integer.MAX_VALUE;
            int bKey = bHead + k / 2 - 1 < b.length ? b[bHead + k / 2 - 1] : Integer.MAX_VALUE;

            if (aKey < bKey) {
                return findKth(a, aHead + k / 2, b, bHead, k - k / 2);
            } else {
                return findKth(a, aHead, b, bHead + k / 2, k - k / 2);
            }
        }
    }

}
