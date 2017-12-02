package algorithms;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * Note: Do not modify the linked list.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycleII {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n2;

        ListNode cycleHead = new LinkedListCycleII().new Solution().detectCycle(n1);
        System.out.println(cycleHead.val);
    }

    /**
     * In order to find the start of the cycle, we need to use the Floyd's cycle-finding algorithm. The idea is, after
     * fast pointer and slow pointer first meet (has cycle), move one of them back to head. Then move both pointers step
     * by step, the node that they meet again is the start of the cycle, here's the proof:
     * 
     * Define:
     * i -> distance that slow pointer has traveled before first meet
     * m -> distance between head & start of cycle
     * n -> length of the cycle
     * k -> distance between start of cycle & first meet
     * a -> # of cycles slow pointer has traveled
     * b -> # of cycles fast pointer has traveled
     * 
     * So for slow pointer we have: i = m + a*n + k
     * And for fast pointer we have: i*2 = m + b*n + k
     * Subtract these two, we have: i = (b-a) * n, where (b-a) is an integer
     * 
     * So when another pointer starts from the head and takes m step, the original slow pointer has now traveled i+m
     * steps, and since we already know that i is an integer multiple of n, these two pointers should meet at the start
     * of the cycle.
     * 
     * references:
     * https://en.wikipedia.org/wiki/Cycle_detection
     * http://blog.csdn.net/xiaoquantouer/article/details/51620657
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }

            // check if cycle exists
            ListNode slow = head;
            ListNode fast = head;
            do {
                if (fast == null || fast.next == null) {
                    return null;
                }
                slow = slow.next;
                fast = fast.next.next;
            } while (slow != fast);

            // find the head of cycle
            while (head != slow) {
                head = head.next;
                slow = slow.next;
            }

            return head;
        }
    }

}
