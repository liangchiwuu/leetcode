package algorithms;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * 
 * For example, the following two linked lists:
 * A:          a1 → a2
 *                     ↘
 *                       c1 → c2 → c3
 *                     ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * 
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionOfTwoLinkedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode intersection = new ListNode(3);
        intersection.next = new ListNode(4);
        intersection.next.next = new ListNode(5);

        ListNode headA = new ListNode(2);
        headA.next = intersection;

        ListNode headB = new ListNode(1);
        headB.next = new ListNode(2);
        headB.next.next = intersection;

        ListNode ans = new IntersectionOfTwoLinkedLists().new Solution().getIntersectionNode(headA, headB);
        System.out.println(ans == null ? "null" : ans.val);
    }

    /**
     * A naive solution. We can first calculate the length of two linked lists, then trim the longer one from head. Once
     * these two linked list have the same length, we can safely compare the nodes one by one.
     * 
     * Time complexity: O(m+n)
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            int aLength = getLength(headA);
            int bLength = getLength(headB);
            ListNode pA = headA;
            ListNode pB = headB;

            // trim the longer list
            while (aLength != bLength) {
                if (aLength > bLength) {
                    pA = pA.next;
                    aLength--;
                } else {
                    pB = pB.next;
                    bLength--;
                }
            }

            // compare one by one
            while (pA != null && pB != null) {
                if (pA == pB) {
                    return pA;
                } else {
                    pA = pA.next;
                    pB = pB.next;
                }
            }

            return null;
        }

        private int getLength(ListNode head) {
            int length = 0;
            ListNode n = head;
            while (n != null) {
                n = n.next;
                length++;
            }
            return length;
        }
    }

}
