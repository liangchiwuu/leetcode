package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w. Now given
 * all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest
 * price from src to dst with up to k stops. If there is no such route, output -1.
 * 
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200. (0->1, 1->2)
 * 
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500. (0 -> 2)
 * 
 * Note:
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } };
        int src = 0;
        int dst = 2;
        int k = 1;

        int cost = new CheapestFlightsWithinKStops().new Solution().findCheapestPrice(n, flights, src, dst, k);
        System.out.println(cost);
    }

    /**
     * A BFS solution. We start from source city and iterate up to k+1 times, in each iteration we expand and check if
     * there exists a cheaper route.
     * 
     * Time complexity: O(k * e), where e is the # of flights
     */
    class Solution {
        class Flight {
            int dest;
            int cost;

            public Flight(int dest, int cost) {
                this.dest = dest;
                this.cost = cost;
            }
        }

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<Flight>> adj = buildGraph(n, flights);

            int[] cost = new int[n];
            Arrays.fill(cost, Integer.MAX_VALUE);
            Queue<Integer> queue = new LinkedList<Integer>();

            cost[src] = 0;
            queue.offer(src);

            int step = 0;
            while (step < k + 1 && !queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    int city = queue.poll();
                    // update cost for neighbor if lower
                    for (Flight flight : adj.get(city)) {
                        if (cost[city] + flight.cost < cost[flight.dest]) {
                            cost[flight.dest] = cost[city] + flight.cost;
                            queue.add(flight.dest);
                        }
                    }
                }
                step++;
            }

            return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
        }

        private Map<Integer, List<Flight>> buildGraph(int n, int[][] flights) {
            Map<Integer, List<Flight>> adj = new HashMap<>();
            for (int i = 0; i < n; i++) {
                adj.put(i, new ArrayList<>());
            }
            for (int[] flight : flights) {
                adj.get(flight[0]).add(new Flight(flight[1], flight[2]));
            }
            return adj;
        }
    }

}
