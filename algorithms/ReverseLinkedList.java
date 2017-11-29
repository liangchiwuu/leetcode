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
        System.out.println(reversed.val);
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
     * An recursive solution.
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
