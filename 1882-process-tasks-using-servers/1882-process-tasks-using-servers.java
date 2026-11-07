import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Heap 1: Free servers 
        // Sorted by weight asc, then by index asc
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Heap 2: Busy servers
        // Sorted by freeTime asc, then weight asc, then index asc
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Initialize free servers: [weight, index, freeTime]
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i, 0});
        }

        int currTime = 0;
        for (int i = 0; i < m; i++) {
            // The task becomes available at second 'i'
            currTime = Math.max(currTime, i);

            // If no servers are free, fast-forward time to the next available busy server
            if (freeServers.isEmpty()) {
                currTime = Math.max(currTime, busyServers.peek()[2]);
            }

            // Release all servers that have finished their tasks by the current time
            while (!busyServers.isEmpty() && busyServers.peek()[2] <= currTime) {
                freeServers.offer(busyServers.poll());
            }

            // Assign the task to the best available free server
            int[] server = freeServers.poll();
            ans[i] = server[1]; // Store the server index in the result array
            
            // Update the server's availability time and move it to the busy queue
            server[2] = currTime + tasks[i];
            busyServers.offer(server);
        }

        return ans;
    }
}
