package algorithms;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * Challenge:
 * Solve it by merge sort & quick sort separately.
 */
public class SortList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(-1);
        head.next.next = new ListNode(0);

        ListNode sorted = new SortList().new Solution().sortList(head);
        while (sorted != null) {
            System.out.print(sorted.val + "->");
            sorted = sorted.next;
        }
        System.out.println("null");
    }

    /**
     * Merge sort solution.
     * 
     * Time complexity: O(n log n)
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            // divide
            ListNode mid = findMiddle(head);
            ListNode left = head;
            ListNode right = mid.next;
            mid.next = null;

            // sort
            left = sortList(left);
            right = sortList(right);

            // merge
            return merge(left, right);
        }

        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    tail.next = l2;
                    l2 = l2.next;
                } else {
                    tail.next = l1;
                    l1 = l1.next;
                }
                tail = tail.next;
            }
            if (l1 == null) {
                tail.next = l2;
            } else {
                tail.next = l1;
            }
            return dummy.next;
        }

        private ListNode findMiddle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

    /**
     * Quick sort solution. The key here is to split the list into three (left, right, equal) pieces instead of two
     * during partition. The use of equal partition can help to reduce the load of next loop. This problem is also a
     * good example of how to use dummy and tail nodes to locate a list.
     * 
     * Time complexity: O(n log n)
     */
    class Solution2 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            int pivot = findPivot(head);
            ListNode leftDummy = new ListNode(0);
            ListNode leftTail = leftDummy;
            ListNode equalDummy = new ListNode(0);
            ListNode equalTail = equalDummy;
            ListNode rightDummy = new ListNode(0);
            ListNode rightTail = rightDummy;

            // partition
            while (head != null) {
                if (head.val < pivot) {
                    leftTail.next = head;
                    leftTail = head;
                } else if (head.val > pivot) {
                    rightTail.next = head;
                    rightTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
                head = head.next;
            }

            // break list
            leftTail.next = null;
            equalTail.next = null;
            rightTail.next = null;

            ListNode left = sortList(leftDummy.next);
            ListNode right = sortList(rightDummy.next);

            return concat(left, equalDummy.next, right);
        }

        private int findPivot(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.val;
        }

        private ListNode concat(ListNode left, ListNode mid, ListNode right) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            tail.next = left;
            tail = getTail(tail);
            tail.next = mid;
            tail = getTail(tail);
            tail.next = right;
            return dummy.next;
        }

        private ListNode getTail(ListNode head) {
            if (head == null) {
                return head;
            }
            while (head.next != null) {
                head = head.next;
            }
            return head;
        }
    }

}
