package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the
 * list or null.
 * 
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public static void main(String[] args) {
        RandomListNode n1 = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        n1.next = n2;
        n2.next = n3;
        n1.random = n3;
        n2.random = null;
        n3.random = n2;

        RandomListNode head = new CopyListWithRandomPointer().new Solution2().copyRandomList(n1);
        while (head != null) {
            System.out.print(head.label + "(" + (head.random == null ? null : head.random.label) + ")->");
            head = head.next;
        }
        System.out.println("null");
    }

    /**
     * A classic solution by first cloning nodes and create a mapping table, then clone edges based on the mapping. This
     * problem is very similar to 'Clone Graph.'
     * 
     * Time complexity: O(n)
     * Space complexity: O(n), apart from input/output, the map here takes O(n) space
     */
    public class Solution {
        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) {
                return null;
            }

            Map<RandomListNode, RandomListNode> mapping = new HashMap<>();

            // clone nodes
            RandomListNode curt = head;
            while (curt != null) {
                mapping.put(curt, new RandomListNode(curt.label));
                curt = curt.next;
            }

            // clone edges
            curt = head;
            while (curt != null) {
                mapping.get(curt).next = mapping.get(curt.next);
                mapping.get(curt).random = mapping.get(curt.random);
                curt = curt.next;
            }

            return mapping.get(head);
        }
    }

    /**
     * The challenge here is to spend only constant extra space while deep cloning the list. The trick here is to insert
     * the copy nodes after the original nodes, for example: 1->2 will transfer to 1->1'->2->2'. This way the original
     * node after node n becomes n.next.next, and the copy of n is now n.next. See the steps breakdown:
     * 
     * 1. input (parentheses means random pointer): 1(3)->2(null)->3(2)->null
     * 2. after in-place clone nodes: 1(3)->1'(null)->2(null)->2'(null)->3(2)->3'(null)->null
     * 3. after copy random pointer: 1(3)->1'(3')->2(null)->2'(null)->3(2)->3'(2')->null
     * 4. after split 2 lists: 1'(3')->2'(null)->3'(2')->null
     * 
     * pros: uses O(1) extra space
     * cons: the original list is modified (not a pure function) and might causes problem in multithreading
     * 
     * Time complexity: O(n)
     * Space complexity: O(1), here we only look at 'extra' space taken and neglect input/output
     */
    public class Solution2 {
        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) {
                return null;
            }

            // in-place clone nodes
            RandomListNode curt = head;
            while (curt != null) {
                RandomListNode copy = new RandomListNode(curt.label);
                copy.next = curt.next;
                curt.next = copy;
                curt = curt.next.next;
            }

            // copy random pointer
            curt = head;
            while (curt != null) {
                curt.next.random = curt.random == null ? null : curt.random.next;
                curt = curt.next.next;
            }

            // split 2 lists
            RandomListNode newHead = head.next;
            curt = head;
            while (curt != null) {
                RandomListNode copy = curt.next;
                curt.next = copy.next;
                copy.next = copy.next == null ? null : copy.next.next;
                curt = curt.next;
            }

            return newHead;
        }
    }
}
