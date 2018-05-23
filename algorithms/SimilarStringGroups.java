package algorithms;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
 * 
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but
 * "star" is not similar to "tars", "rats", or "arts".
 * 
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}. Notice that "tars"
 * and "arts" are in the same group even though they are not similar. Formally, each group is such that a word is in the
 * group if and only if it is similar to at least one other word in the group.
 * 
 * We are given a list A of unique strings. Every string in A is an anagram of every other string in A. How many groups
 * are there?
 * 
 * Example 1:
 * Input: ["tars","rats","arts","star"]
 * Output: 2
 * 
 * Note:
 * A.length <= 2000
 * A[i].length <= 1000
 * A.length * A[i].length <= 20000
 * All words in A consist of lowercase letters only.
 * All words in A have the same length and are anagrams of each other.
 * The judging time limit has been increased for this question.
 */
public class SimilarStringGroups {

    public static void main(String[] args) {
        String[] s = { "tars", "rats", "arts", "star" };
        int numGroups = new SimilarStringGroups().new Solution().numSimilarGroups(s);
        System.out.println(numGroups);
    }

    /**
     * A BFS solution. The idea is simple:
     * 
     * 1. choose a random string from words
     * 2. use BFS to find the group
     * 3. remove this group from words
     * 3. repeat until words is empty
     * 
     * Time complexity: O(N^2 M), where N is the # of words and M is the length of each word
     */
    class Solution {
        public int numSimilarGroups(String[] words) {
            if (words == null) {
                return 0;
            }

            Set<String> set = new HashSet<>();
            for (String word : words) {
                set.add(word);
            }

            int numGroups = 0;
            while (!set.isEmpty()) {
                // find a group with BFS, then remove from set
                Set<String> group = bfs(set, set.iterator().next());
                for (String s : group) {
                    set.remove(s);
                }
                numGroups++;
            }

            return numGroups;
        }

        private Set<String> bfs(Set<String> set, String origin) {
            Queue<String> queue = new ArrayDeque<>();
            Set<String> result = new HashSet<>();

            queue.offer(origin);
            result.add(origin);

            while (!queue.isEmpty()) {
                String s = queue.poll();
                for (String n : set) {
                    if (!result.contains(n) && isSimilar(s, n)) {
                        queue.offer(n);
                        result.add(n);
                    }
                }
            }

            return result;
        }

        private boolean isSimilar(String s1, String s2) {
            int diff = 0;
            for (int i = 0; i < s1.length(); i++) {
                diff += s1.charAt(i) == s2.charAt(i) ? 0 : 1;

            }
            return diff == 2;
        }
    }

    /**
     * A Union-Find solution.
     * 
     * Time complexity: O()
     */
    class Solution2 {
        public int numSimilarGroups(String[] words) {

            // TODO: solve this problem with Union-Find

            return 0;
        }
    }

}
