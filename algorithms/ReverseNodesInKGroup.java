package algorithms;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroup {

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
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = new ReverseNodesInKGroup().new Solution().reverseKGroup(head, 2);
        while (result != null) {
            System.out.print(result.val);
            System.out.print("->");
            result = result.next;
        }
        System.out.println("null");
    }

    /**
     * This problem is a good example of how a dummy node can help in linked lists. The idea is that, for each group, we
     * not only need to reverse the nodes in the group, but also need to handle the node before/after the group. Since
     * there is no node before 'head', we will need to add our own dummy node.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null || k < 2) {
                return head;
            }

            int n = countGroups(head, k);

            // add a dummy node to replace head
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            head = dummy;

            // reverse n times (since there are n groups)
            for (int i = 0; i < n; i++) {
                head = reverseK(head, k);
            }

            return dummy.next;
        }

        private int countGroups(ListNode head, int k) {
            int length = 0;
            ListNode curt = head;
            while (curt != null) {
                curt = curt.next;
                length++;
            }
            return length / k;
        }

        /*
         * Reverts k nodes AFTER head, example:
         * 
         * head -> n1 -> n2 ... nk -> nk+1
         * =>
         * head -> nk -> nk-1 ... n1 -> nk+1
         * =>
         * return n1
         */
        private ListNode reverseK(ListNode head, int k) {
            ListNode n1 = head.next;

            ListNode prev = head;
            ListNode curt = head.next;
            for (int i = 0; i < k; i++) {
                ListNode next = curt.next;
                curt.next = prev;
                prev = curt;
                curt = next;
            }

            n1.next = curt;
            head.next = prev;
            return n1;
        }
    }

}
