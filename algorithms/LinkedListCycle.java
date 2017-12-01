package algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycle {

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

        boolean hasCycle = new LinkedListCycle().new Solution().hasCycle(n1);
        System.out.println(hasCycle);
    }

    /**
     * A very straight forward solution. Simply loop through the list and add visited nodes to the set. If we visit a
     * node which has been visited then there is a cycle in list.
     * 
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }

            Set<ListNode> visited = new HashSet<>();
            while (head != null) {
                if (visited.contains(head)) {
                    return true;
                }
                visited.add(head);
                head = head.next;
            }

            return false;
        }
    }

    /**
     * The challenge here is to spend only constant extra space. Here we use two different pointers: fast and slow. Slow
     * pointer moves step by step, fast pointer moves two steps at time. If there is a cycle in list, fast and slow
     * pointer must meet at some point.
     * 
     * See this algorithm (Floyd's Tortoise and Hare) at:
     * https://en.wikipedia.org/wiki/Cycle_detection
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public class Solution2 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }

            ListNode slow = head;
            ListNode fast = head.next;
            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            }

            return true;
        }
    }

}
