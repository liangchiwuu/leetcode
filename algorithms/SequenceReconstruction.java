package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is
 * a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common
 * supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of
 * it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 * 
 * Example:
 * Given org = [1,2,3], seqs = [[1,2],[1,3]]
 * Return false
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be
 * reconstructed.
 * 
 * Given org = [1,2,3], seqs = [[1,2]]
 * Return false
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * 
 * Given org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
 * Return true
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * 
 * Given org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
 * Return true
 */
public class SequenceReconstruction {

    public static void main(String[] args) {
        int[] org = new int[] { 1, 2, 3 };
        int[][] seqs = new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        boolean result = new SequenceReconstruction().new Solution().sequenceReconstruction(org, seqs);
        System.out.println(result);
    }

    /**
     * A BFS solution uses topological sort, the key steps are:
     * 
     * 1. use seqs to build the adjacency list
     * 2. check if there is only one possible topological sort
     * 
     * Note that there are a lot of boundary checks in between to handle exceptions.
     */
    public class Solution {
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            if (org == null || seqs == null) {
                return false;
            }

            // build graph and calculate indegree
            Map<Integer, Set<Integer>> adjList = new HashMap<>();
            Map<Integer, Integer> indegree = new HashMap<>();
            int numOfNodes = 0;
            for (int node : org) {
                adjList.put(node, new HashSet<>());
                indegree.put(node, 0);
            }
            for (int[] seq : seqs) {
                numOfNodes += seq.length;
                // check if seq contains node not in org
                for (int node : seq) {
                    if (node > org.length) {
                        return false;
                    }
                }
                for (int i = 0; i < seq.length - 1; i++) {
                    int from = seq[i];
                    int to = seq[i + 1];
                    // to prevent duplicate edge
                    if (!adjList.get(from).contains(to)) {
                        indegree.put(to, indegree.get(to) + 1);
                        adjList.get(from).add(to);
                    }
                }
            }

            // case [1], []
            if (org.length > numOfNodes) {
                return false;
            }

            // topological sort
            Queue<Integer> queue = new LinkedList<>();
            for (int node : org) {
                if (indegree.get(node) == 0) {
                    queue.add(node);
                }
            }
            while (!queue.isEmpty()) {
                // if at any moment there are more than one choice, org is not unique
                if (queue.size() > 1) {
                    return false;
                }
                int node = queue.poll();
                for (int neighbor : adjList.get(node)) {
                    indegree.put(neighbor, indegree.get(neighbor) - 1);
                    if (indegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            return true;
        }
    }

    /**
     * The key steps of this approach are:
     * 
     * 1. build edges from seqs
     * 2. check if all the edges in org exist in seqs
     * 
     * Note that there are a lot of boundary checks in between to handle exceptions.
     */
    public class Solution2 {
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            if (org == null || seqs == null) {
                return false;
            }

            Map<Integer, Integer> indexes = getIndexes(org);

            // build edges from seqs
            int numOfNodes = 0;
            Set<List<Integer>> edges = new HashSet<>();
            for (int[] seq : seqs) {
                numOfNodes += seq.length;
                for (int node : seq) {
                    if (!indexes.containsKey(node)) {
                        return false;
                    }
                }
                for (int i = 0; i < seq.length - 1; i++) {
                    List<Integer> edge = new ArrayList<>();
                    edge.add(seq[i]);
                    edge.add(seq[i + 1]);
                    if (indexes.get(edge.get(0)) > indexes.get(edge.get(1))) {
                        return false;
                    }
                    edges.add(edge);
                }
            }

            // case [1], []
            if (org.length > numOfNodes) {
                return false;
            }

            // check if all the edges in org exist
            for (int i = 0; i < org.length - 1; i++) {
                List<Integer> edge = new ArrayList<>();
                edge.add(org[i]);
                edge.add(org[i + 1]);
                if (!edges.contains(edge)) {
                    return false;
                }
            }

            return true;
        }

        private Map<Integer, Integer> getIndexes(int[] org) {
            Map<Integer, Integer> indexes = new HashMap<>();
            for (int i = 0; i < org.length; i++) {
                indexes.put(org[i], i);
            }
            return indexes;
        }
    }

}
