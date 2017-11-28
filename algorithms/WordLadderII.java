package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadderII {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        List<List<String>> results = new WordLadderII().new Solution().findLadders(beginWord, endWord, wordList);
        System.out.println(results);
    }

    /**
     * The key here is to breakdown the problem into 2 pieces: find shortest path and find all solutions. We already
     * know that BFS is good for finding the shortest path, and DFS is good for finding all solutions. Now the problem
     * becomes: if eventually we need to use DFS to find all ladders, how can we make it faster (more efficient)?
     *
     * The most naive way is, of course, to travel through all possible ladders and finds the shortest ones. But what we
     * can do here, is to first use a BFS to calculate the distance between each word and endWord. Then clearly later in
     * the DFS we just need to make sure each step leads us closer to the target. To summary:
     * 
     * 1. use BFS to calculate the distance between each word and endWord
     * 2. use DFS to find all shortest ladders, while each step must reduce the distance by 1
     *
     * Time complexity: O(n * L^2), L for the length of each word
     */
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> ladders = new ArrayList<>();
            Map<String, Integer> remainDistance = new HashMap<>();
            Set<String> wordSet = new HashSet<>(wordList);

            // find the distance between each word and endWord
            bfs(endWord, wordSet, remainDistance);
            // find all shortest ladders
            dfs(beginWord, endWord, wordSet, remainDistance, new ArrayList<>(), ladders);

            return ladders;
        }

        private void bfs(String endWord, Set<String> wordSet, Map<String, Integer> remainDistance) {
            Queue<String> queue = new LinkedList<>();

            queue.add(endWord);
            remainDistance.put(endWord, 0);

            int distance = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String word = queue.poll();
                    for (String neighbor : expand(word, wordSet)) {
                        if (remainDistance.containsKey(neighbor)) {
                            continue;
                        }

                        queue.offer(neighbor);
                        remainDistance.put(neighbor, distance);
                    }
                }
                distance++;
            }
        }

        private void dfs(String current, String endWord, Set<String> wordSet, Map<String, Integer> remainDistance,
                List<String> ladder, List<List<String>> ladders) {
            ladder.add(current);

            if (ladder.get(ladder.size() - 1).equals(endWord)) {
                ladders.add(new ArrayList<>(ladder));
            } else {
                for (String next : expand(current, wordSet)) {
                    // to ensure shortest path, each step must reduce the distance by 1
                    if (remainDistance.containsKey(current)
                            && remainDistance.get(current) - remainDistance.get(next) != 1) {
                        continue;
                    }
                    dfs(next, endWord, wordSet, remainDistance, ladder, ladders);
                }
            }

            ladder.remove(ladder.size() - 1);
        }

        private List<String> expand(String word, Set<String> wordSet) {
            List<String> expansion = new ArrayList<String>();
            for (int i = 0; i < word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == word.charAt(i)) {
                        continue;
                    }
                    String expanded = word.substring(0, i) + c + word.substring(i + 1);
                    if (wordSet.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
            return expansion;
        }
    }

}
