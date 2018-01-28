package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        ListNode l2 = null;
        ListNode l3 = new ListNode(-1);
        ListNode[] lists = { l1, l2, l3 };

        ListNode merged = new MergeKSortedLists().new Solution().mergeKLists(lists);
        while (merged != null) {
            System.out.print(merged.val + "->");
            merged = merged.next;
        }
        System.out.println("null");
    }

    /**
     * A solution with minimum heap. The idea is simple, repetitively find the minimum head in k lists then append to
     * result. The key here is to use PriorityQueue and Comparator to build a minimum heap for ListNode. The time
     * complexity to poll a value from a heap of size k (contains head of each list) is O(log k), and since we need to
     * repeat this action for n times (for n nodes in total), the time complexity is O(n log k).
     * 
     * Time complexity: O(n log k)
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
                @Override
                public int compare(ListNode l1, ListNode l2) {
                    return l1.val - l2.val;
                }
            });

            for (ListNode n : lists) {
                if (n != null) {
                    heap.add(n);
                }
            }

            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            while (!heap.isEmpty()) {
                ListNode node = heap.poll();
                tail.next = node;
                tail = tail.next;
                if (node.next != null) {
                    heap.add(node.next);
                }
            }

            return dummy.next;
        }
    }

    /**
     * A divide and conquer solution. The idea is similar to merge sort, first we divide the lists to two parts: left
     * and right, recursively process them respectively, then merge them together. Since the depth of this recursion
     * (total # of layers) is log k, and for each layer we need to process all the nodes (assume n nodes in total), the
     * time complexity here becomes O(n log k).
     * 
     * Time complexity: O(n log k)
     */
    class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            return merge(lists, 0, lists.length - 1);
        }

        private ListNode merge(ListNode[] lists, int start, int end) {
            if (start == end) {
                return lists[start];
            }

            int mid = (start + end) / 2;
            ListNode left = merge(lists, start, mid);
            ListNode right = merge(lists, mid + 1, end);

            // merge left and right
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    tail.next = left;
                    left = left.next;
                } else {
                    tail.next = right;
                    right = right.next;
                }
                tail = tail.next;
            }
            if (left == null) {
                tail.next = right;
            } else {
                tail.next = left;
            }

            return dummy.next;
        }
    }

}
