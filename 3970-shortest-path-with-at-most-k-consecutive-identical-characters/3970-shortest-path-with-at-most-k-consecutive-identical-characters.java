import java.util.*;

public class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Step 1: Build the adjacency list for the directed graph
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
        }
        
        // Step 2: Initialize the distance array
        // dist[u][c] stores the minimum weight to reach node 'u' with 'c' consecutive identical characters ending at u
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // Step 3: Setup the PriorityQueue for Dijkstra's Algorithm
        // Elements in PQ: {current_cost, current_node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Base case: start at node 0 with cost 0 and consecutive count of 1
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentCost = curr[0];
            int u = curr[1];
            int currentCount = curr[2];
            
            // If we reached the destination node, return the cost immediately
            if (u == n - 1) {
                return currentCost;
            }
            
            // Skip if we found a shorter path to this state already
            if (currentCost > dist[u][currentCount]) {
                continue;
            }
            
            // Traverse all directed neighbors
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                // Determine the next consecutive count based on matching labels
                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? currentCount + 1 : 1;
                
                // Only proceed if the path satisfies the condition of at most k consecutive identical characters
                if (nextCount <= k) {
                    int nextCost = currentCost + weight;
                    if (nextCost < dist[v][nextCount]) {
                        dist[v][nextCount] = nextCost;
                        pq.offer(new int[]{nextCost, v, nextCount});
                    }
                }
            }
        }
        
        // Return -1 if destination node n-1 is unreachable under the given constraints
        return -1;
    }
}
