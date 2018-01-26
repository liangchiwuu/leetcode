package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * LRUCache cache = new LRUCache( 2 // capacity );
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1); // returns 1
 * cache.put(3, 3); // evicts key 2
 * cache.get(2); // returns -1 (not found)
 * cache.put(4, 4); // evicts key 1
 * cache.get(1); // returns -1 (not found)
 * cache.get(3); // returns 3
 * cache.get(4); // returns 4
 */
public class LRUCache {

    /*
     * To implement a LRU cache, we will need a map to store the key-value pair, and another linked list to store the
     * order of entries. One thing to note is that here we use two dummy nodes to make it easier to remove from head and
     * append to tail, the whole list will look like:
     * 
     * head -> oldest -> 2nd oldest -> ... -> newest -> tail
     */

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int CAPACITY;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        this.map = new HashMap<>();

        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // remove current
        Node current = map.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // append to tail
        appendToTail(current);

        return current.value;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            // after get(), node has been moved to tail, now we just have to update value
            map.get(key).value = value;
            return;
        }

        // remove the oldest
        if (map.size() == CAPACITY) {
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        appendToTail(newNode);
    }

    private void appendToTail(Node node) {
        node.prev = tail.prev;
        tail.prev = node;
        node.prev.next = node;
        node.next = tail;
    }

}
