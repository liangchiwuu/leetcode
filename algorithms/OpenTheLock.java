package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6',
 * '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * 
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * 
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock
 * will stop turning and you will be unable to open it.
 * 
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of
 * turns required to open the lock, or -1 if it is impossible.
 * 
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * 
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 * 
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 * 
 * Example 4:
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 * 
 * Note:
 * The length of deadends will be in the range [1, 500].
 * target will not be in the list deadends.
 * Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to
 * '9999'.
 */
public class OpenTheLock {

    public static void main(String[] args) {
        String[] deadends = { "0201", "0101", "0102", "1212", "2002" };
        String target = "0202";
        int result = new OpenTheLock().new Solution().openLock(deadends, target);
        System.out.println(result);
    }

    /**
     * A typical BFS solution. The key of this problem is how you can generate the 8 possible next combinations from a
     * code.
     * 
     * Time complexity:
     */
    class Solution {
        public int openLock(String[] deadends, String target) {
            Set<String> stop = new HashSet<>();
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();

            for (String s : deadends) {
                stop.add(s);
            }
            visited.add("0000");
            queue.add("0000");

            int dist = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String code = queue.poll();
                    if (code.equals(target)) {
                        return dist;
                    }
                    if (stop.contains(code)) {
                        continue;
                    }
                    List<String> neighbors = getNeighbors(code);
                    for (String neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(neighbor);
                        }
                    }
                }
                dist++;
            }

            return -1;
        }

        /**
         * Returns 8 possible next combinations from a code.
         */
        private List<String> getNeighbors(String s) {
            List<String> neighbors = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                int up = (s.charAt(i) - '0' + 1) % 10;
                neighbors.add(s.substring(0, i) + up + s.substring(i + 1));
                int down = (s.charAt(i) - '0' - 1 + 10) % 10;
                neighbors.add(s.substring(0, i) + down + s.substring(i + 1));
            }
            return neighbors;
        }
    }

}
