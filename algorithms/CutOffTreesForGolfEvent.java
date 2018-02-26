package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in
 * this map:
 * 
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the
 * tree's height.
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with
 * lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
 * 
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the
 * trees. If you can't cut off all the trees, output -1 in that situation.
 * 
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 * 
 * Example 1:
 * Input:
 * [
 * [1,2,3],
 * [0,0,4],
 * [7,6,5]
 * ]
 * Output: 6
 * 
 * Example 2:
 * Input:
 * [
 * [1,2,3],
 * [0,0,0],
 * [7,6,5]
 * ]
 * Output: -1
 * 
 * Example 3:
 * Input:
 * [
 * [2,3,4],
 * [0,0,5],
 * [8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 * 
 * Hint: size of the given matrix will not exceed 50x50.
 */
public class CutOffTreesForGolfEvent {

    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(1, 2, 3));
        forest.add(Arrays.asList(0, 0, 4));
        forest.add(Arrays.asList(7, 6, 5));
        int steps = new CutOffTreesForGolfEvent().new Solution().cutOffTree(forest);
        System.out.println(steps);
    }

    /**
     * An intuitive solution. The idea is very simple: find the distance of origin->tree1, tree1->tree2, ... then add
     * them up. The key here is the implementation of the function that finds that minimum steps from one coordinate to
     * another. Here we use BFS for shortest path search, other methods such as A* is of course applicable as well.
     * 
     * Time complexity: O((m*n)^2)
     */
    class Solution {

        private class Tree {
            public int[] coord;
            public int height;

            public Tree(int[] coord, int height) {
                super();
                this.coord = coord;
                this.height = height;
            }
        }

        public int cutOffTree(List<List<Integer>> forest) {
            if (forest == null || forest.size() == 0
                    || forest.get(0) == null || forest.get(0).size() == 0) {
                return 0;
            }

            // find all trees
            PriorityQueue<Tree> heap = new PriorityQueue<>(new Comparator<Tree>() {
                @Override
                public int compare(Tree t1, Tree t2) {
                    return t1.height - t2.height;
                }
            });
            for (int i = 0; i < forest.size(); i++) {
                for (int j = 0; j < forest.get(0).size(); j++) {
                    if (forest.get(i).get(j) > 1) {
                        heap.offer(new Tree(new int[] { i, j }, forest.get(i).get(j)));
                    }
                }
            }

            // cut tree in order
            int steps = 0;
            int[] current = new int[] { 0, 0 };
            while (!heap.isEmpty()) {
                Tree next = heap.poll();
                int dist = bfs(forest, current, next.coord);
                if (dist == -1) {
                    return -1;
                }
                steps += dist;
                current = next.coord;
            }

            return steps;
        }

        private int bfs(List<List<Integer>> forest, int[] start, int[] end) {
            int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

            boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
            Queue<int[]> queue = new LinkedList<>();

            queue.offer(start);
            visited[start[0]][start[1]] = true;

            int steps = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] coord = queue.poll();
                    if (Arrays.equals(coord, end)) {
                        return steps;
                    }
                    for (int[] direction : directions) {
                        int r = coord[0] + direction[0];
                        int c = coord[1] + direction[1];
                        if (r >= 0 && r < forest.size() && c >= 0 && c < forest.get(0).size()
                                && !visited[r][c] && forest.get(r).get(c) > 0) {
                            queue.offer(new int[] { r, c });
                            visited[r][c] = true;
                        }
                    }
                }
                steps++;
            }

            return -1;
        }
    }

}
