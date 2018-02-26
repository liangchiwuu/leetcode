package algorithms;

/**
 * You are playing a simplified Pacman game. You start at the point (0, 0), and your destination is (target[0],
 * target[1]). There are several ghosts on the map, the i-th ghost starts at (ghosts[i][0], ghosts[i][1]).
 * 
 * Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south,
 * going from the previous point to a new point 1 unit of distance away.
 * 
 * You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may
 * take.) If you reach any square (including the target) at the same time as a ghost, it doesn't count as an escape.
 * 
 * Return True if and only if it is possible to escape.
 * 
 * Example 1:
 * Input:
 * ghosts = [[1, 0], [0, 3]]
 * target = [0, 1]
 * Output: true
 * Explanation:
 * You can directly reach the destination (0, 1) at time 1, while the ghosts located at (1, 0) or (0, 3) have no way to
 * catch up with you.
 * 
 * Example 2:
 * Input:
 * ghosts = [[1, 0]]
 * target = [2, 0]
 * Output: false
 * Explanation:
 * You need to reach the destination (2, 0), but the ghost at (1, 0) lies between you and the destination.
 * 
 * Example 3:
 * Input:
 * ghosts = [[2, 0]]
 * target = [1, 0]
 * Output: false
 * Explanation:
 * The ghost can reach the target at the same time as you.
 * 
 * Note:
 * All points have coordinates with absolute value <= 10000.
 * The number of ghosts will not exceed 100.
 */
public class EscapeTheGhosts {

    public static void main(String[] args) {
        int[][] ghosts = { { 1, 0 }, { 0, 3 } };
        int[] target = { 0, 1 };
        boolean canEscape = new EscapeTheGhosts().new Solution().escapeGhosts(ghosts, target);
        System.out.println(canEscape);
    }

    /**
     * Simply checking if we are closer to target than all the ghosts. Only thing to note here is the use of Manhattan
     * distance.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public boolean escapeGhosts(int[][] ghosts, int[] target) {
            int myDist = manhattanDist(new int[] { 0, 0 }, target);

            for (int[] ghost : ghosts) {
                if (manhattanDist(ghost, target) <= myDist) {
                    return false;
                }
            }

            return true;
        }

        private int manhattanDist(int[] coord1, int[] coord2) {
            int dx = coord1[0] - coord2[0];
            int dy = coord1[1] - coord2[1];
            return Math.abs(dx) + Math.abs(dy);
        }
    }

}
