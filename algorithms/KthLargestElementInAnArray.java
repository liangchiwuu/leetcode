package algorithms;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * 
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 2;
        int result = new KthLargestElementInAnArray().new Solution().findKthLargest(nums, k);
        System.out.println(result);
    }

    /**
     * The solution to this problem is very much similar to quick sort. The idea is to only proceed on one side after
     * partitioning based on the index of the pivot. Here we perform the partition with two pointers from both side.
     * 
     * Time complexity: O(n), since T(n) = T(n/2) + O(n)
     */
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null) {
                return -1;
            }
            return quickSelect(nums, 0, nums.length - 1, k);
        }

        private int quickSelect(int[] nums, int start, int end, int k) {
            if (start == end) {
                return nums[start];
            }

            // partition from both end
            int i = start;
            int j = end;
            int pivot = nums[(start + end) / 2];
            while (i <= j) {
                while (nums[i] > pivot) {
                    i++;
                }
                while (nums[j] < pivot) {
                    j--;
                }
                if (i <= j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }
            }

            // decide which partition to proceed
            if (j >= k - 1) {
                return quickSelect(nums, start, j, k);
            } else if (i <= k - 1) {
                return quickSelect(nums, i, end, k);
            }

            // happens when array becomes [... j, pivot, i, ...] after partition
            return nums[j + 1];
        }
    }

    /**
     * This solution differs from the previous one only in the implementation of partitioning. Here we partition the
     * list with only one pointer loop from left to right.
     * 
     * Time complexity: O(n), since T(n) = T(n/2) + O(n)
     */
    class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null) {
                return -1;
            }
            return quickSelect(nums, 0, nums.length - 1, k);
        }

        private int quickSelect(int[] nums, int start, int end, int k) {
            if (start == end) {
                return nums[start];
            }

            // partition
            int pivot = nums[end];
            int i = start;
            for (int j = start; j < end; j++) {
                if (nums[j] >= pivot) {
                    swap(nums, i++, j);
                }
            }
            swap(nums, i, end);

            // decide which partition to proceed
            if (i < k - 1) {
                return quickSelect(nums, i + 1, end, k);
            } else if (i > k - 1) {
                return quickSelect(nums, start, i - 1, k);
            }

            return nums[i];
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * An iterative version of quick select.
     * 
     * Time complexity: O(n), since T(n) = T(n/2) + O(n)
     */
    class Solution3 {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null) {
                return -1;
            }

            int start = 0;
            int end = nums.length - 1;

            while (start <= end) {
                // partition
                int pivot = nums[end];
                int i = start;
                for (int j = start; j < end; j++) {
                    if (nums[j] >= pivot) {
                        swap(nums, i++, j);
                    }
                }
                swap(nums, i, end);

                // narrow range
                if (i < k - 1) {
                    start = i + 1;
                } else if (i > k - 1) {
                    end = i - 1;
                } else {
                    return nums[i];
                }
            }

            return -1;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
