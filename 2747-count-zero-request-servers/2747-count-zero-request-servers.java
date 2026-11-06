import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int numQueries = queries.length;
        int[] result = new int[numQueries];
        
        // 1. Sort logs based on their timestamp in ascending order
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 2. Store queries along with their original indices to process offline
        int[][] sortedQueries = new int[numQueries][2];
        for (int i = 0; i < numQueries; i++) {
            sortedQueries[i][0] = queries[i]; // Query time
            sortedQueries[i][1] = i;          // Original index
        }
        // Sort queries by their query times
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // 3. Sliding window setup
        int[] serverRequestCount = new int[n + 1]; // Tracks frequency of requests for each server
        int activeServers = 0;                     // Number of distinct servers with >= 1 request in the current window
        int left = 0;                              // Left pointer for the window [queryTime - x]
        int right = 0;                             // Right pointer for the window [queryTime]
        
        // 4. Process each query using the sliding window
        for (int i = 0; i < numQueries; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            // Expand the right boundary: Include all logs up to queryTime
            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                if (serverRequestCount[serverId] == 0) {
                    activeServers++;
                }
                serverRequestCount[serverId]++;
                right++;
            }
            
            // Shrink the left boundary: Remove logs that fall outside [queryTime - x, queryTime]
            int minTime = queryTime - x;
            while (left < logs.length && logs[left][1] < minTime) {
                int serverId = logs[left][0];
                serverRequestCount[serverId]--;
                if (serverRequestCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // The number of servers that received ZERO requests is total minus active
            result[originalIndex] = n - activeServers;
        }
        
        return result;
    }
}
