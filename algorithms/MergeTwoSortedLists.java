package algorithms;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes
 * of the first two lists.
 */
public class MergeTwoSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(4);

        ListNode merged = new MergeTwoSortedLists().new Solution().mergeTwoLists(l1, l2);
        while (merged != null) {
            System.out.print(merged.val);
            System.out.print("->");
            merged = merged.next;
        }
        System.out.println("null");
    }

    /**
     * A standard iterative solution. The key here is the use of dummy nodes.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode curt = dummy;

            while (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    curt.next = l2;
                    l2 = l2.next;
                } else {
                    curt.next = l1;
                    l1 = l1.next;
                }
                curt = curt.next;
            }

            if (l1 == null) {
                curt.next = l2;
            } else {
                curt.next = l1;
            }

            return dummy.next;
        }
    }

    /**
     * A recursive solution. Note that this WILL CAUSE stack overflow when list is long.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

}
