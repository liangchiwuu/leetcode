package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other
 * rabbits have the same color as them. Those answers are placed in an array.
 * 
 * Return the minimum number of rabbits that could be in the forest.
 * 
 * Examples:
 * Input: answers = [1, 1, 2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit than answered "2" can't be red or the answers would be inconsistent.
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 * 
 * Input: answers = [10, 10, 10]
 * Output: 11
 * 
 * Input: answers = []
 * Output: 0
 * 
 * Note:
 * answers will have length at most 1000.
 * Each answers[i] will be an integer in the range [0, 999].
 */
public class RabbitsInForest {

    public static void main(String[] args) {
        int[] answers = new int[] { 1, 1, 2 };
        int rabbits = new RabbitsInForest().new Solution().numRabbits(answers);
        System.out.println(rabbits);
    }

    /**
     * When a rabbit says that there are n other rabbits have the same color, it actually implies that the group size of
     * this color is n+1 (including itself). Since each rabbit that says a different number must be in different group,
     * we can first count the number of each answers.
     * 
     * Next, assume there are 21 rabbits answering 5, what's the minimum # of groups? Since the group size is 5+1 = 6,
     * there must be at least 21/6 = 3.5 -> 4 groups, which equals to 6*4 = 24 rabbits. Repeat this process for each
     * answer and we get the minimum # of all rabbits.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public int numRabbits(int[] answers) {
            if (answers == null || answers.length == 0) {
                return 0;
            }

            // count answers
            Map<Integer, Integer> map = new HashMap<>();
            for (int answer : answers) {
                if (map.containsKey(answer)) {
                    map.put(answer, map.get(answer) + 1);
                } else {
                    map.put(answer, 1);
                }
            }

            int total = 0;
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                // size of each group
                int size = e.getKey() + 1;
                // # of groups
                int groupCount = (int) Math.ceil(e.getValue() / (double) size);
                total += size * groupCount;
            }

            return total;
        }
    }
}
