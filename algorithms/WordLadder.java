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
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 */
public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        int result = new WordLadder().new Solution().ladderLength(beginWord, endWord, wordList);
        System.out.println(result);
    }

    /**
     * A BFS Solution. The idea here is to first construct a graph from beginWord & wordList, then simply use BFS to
     * traverse the graph to find the shortest path.
     * 
     * Time complexity: O(n^2 * L), L for the length of each word
     */
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (beginWord.equals(endWord)) {
                return 1;
            }

            Map<String, List<String>> adjList = buildAdjList(beginWord, wordList);
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();

            visited.add(beginWord);
            queue.add(beginWord);

            int length = 1;
            while (!queue.isEmpty()) {
                length++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String word = queue.poll();
                    for (String neighbor : adjList.get(word)) {
                        if (visited.contains(neighbor)) {
                            continue;
                        }
                        if (neighbor.equals(endWord)) {
                            return length;
                        }

                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            return 0;
        }

        private Map<String, List<String>> buildAdjList(String beginWord, List<String> wordList) {
            Map<String, List<String>> adjList = new HashMap<>();

            for (String word : wordList) {
                List<String> neighbors = new ArrayList<>();
                for (String w : wordList) {
                    if (distanceBetween(word, w) == 1) {
                        neighbors.add(w);
                    }
                }
                adjList.put(word, neighbors);
            }

            List<String> neighbors = new ArrayList<>();
            for (String w : wordList) {
                if (distanceBetween(beginWord, w) == 1) {
                    neighbors.add(w);
                }
            }
            adjList.put(beginWord, neighbors);

            return adjList;
        }

        private int distanceBetween(String x, String y) {
            int distance = 0;
            for (int i = 0; i < x.length(); i++) {
                if (x.charAt(i) != y.charAt(i)) {
                    distance++;
                }
            }
            return distance;
        }
    }

    /**
     * Another BFS solution. Similar to the previous solution, we are still performing a BFS on a graph. The difference
     * is that here we find the neighbors(next words) on the go with getNextWords() function. One thing to note is that
     * this approach will generally faster than the previous one since normally n >> L.
     * 
     * Time complexity: O(n * L^2), L for the length of each word
     */
    class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (beginWord.equals(endWord)) {
                return 1;
            }

            Set<String> dict = new HashSet<>(wordList);
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();

            visited.add(beginWord);
            queue.add(beginWord);

            int length = 1;
            while (!queue.isEmpty()) {
                length++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String word = queue.poll();
                    for (String nextWord : getNextWords(word, dict)) {
                        if (visited.contains(nextWord)) {
                            continue;
                        }
                        if (nextWord.equals(endWord)) {
                            return length;
                        }

                        visited.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }

            return 0;
        }

        private ArrayList<String> getNextWords(String word, Set<String> dict) {
            ArrayList<String> nextWords = new ArrayList<String>();
            for (char c = 'a'; c <= 'z'; c++) {
                for (int i = 0; i < word.length(); i++) {
                    if (c == word.charAt(i)) {
                        continue;
                    }
                    String nextWord = replace(word, i, c);
                    if (dict.contains(nextWord)) {
                        nextWords.add(nextWord);
                    }
                }
            }
            return nextWords;
        }

        private String replace(String s, int index, char c) {
            char[] chars = s.toCharArray();
            chars[index] = c;
            return new String(chars);
        }
    }
}
