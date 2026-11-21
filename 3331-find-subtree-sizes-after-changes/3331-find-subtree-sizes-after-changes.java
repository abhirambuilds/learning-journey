import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        
        // 1. Build the initial tree adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] ans = new int[n];
        // Tracks the closest ancestor node ID for each character 'a'-'z'
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // 2. Run DFS to simultaneously update parents and calculate subtree sizes
        dfs(0, s, adj, lastSeen, ans);
        
        return ans;
    }

    private int dfs(int u, String s, List<Integer>[] adj, int[] lastSeen, int[] ans) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx]; // Save state for backtracking
        
        // Current node becomes the closest ancestor for its character
        lastSeen[charIdx] = u;
        
        int currentSubtreeSize = 1; // Count node u itself

        for (int v : adj[u]) {
            int childCharIdx = s.charAt(v) - 'a';
            int closestSameCharAncestor = lastSeen[childCharIdx];
            
            // Recursively compute the size of v's final subtree
            int childSize = dfs(v, s, adj, lastSeen, ans);

            if (closestSameCharAncestor != -1) {
                // v gets re-parented to closestSameCharAncestor
                ans[closestSameCharAncestor] += childSize;
            } else {
                // v stays attached to its original parent u
                currentSubtreeSize += childSize;
            }
        }

        // Add any re-parented descendants that were directly attached to u
        ans[u] += currentSubtreeSize;

        // Backtrack: restore the previous ancestor before returning up the tree
        lastSeen[charIdx] = prevAncestor;

        return ans[u];
    }
}
