package algorithms;

/**
 * Reverse a singly linked list.
 */
public class ReverseLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode reversed = new ReverseLinkedList().new Solution().reverseList(head);
        while (reversed != null) {
            System.out.print(reversed.val);
            System.out.print("->");
            reversed = reversed.next;
        }
        System.out.println("null");
    }

    /**
     * An iterative solution.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curt = head;

            while (curt != null) {
                ListNode next = curt.next;
                curt.next = prev;
                prev = curt;
                curt = next;
            }

            return prev;
        }
    }

    /**
     * A recursive solution. This is NOT recommended since a linked list can easily be very long and causes stack
     * overflow.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public ListNode reverseList(ListNode head) {
            return reverse(null, head);
        }

        private ListNode reverse(ListNode prev, ListNode curt) {
            if (curt == null) {
                return prev;
            }

            ListNode next = curt.next;
            curt.next = prev;
            return reverse(curt, next);
        }
    }

}
