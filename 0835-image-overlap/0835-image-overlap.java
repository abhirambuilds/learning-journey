class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        
        // Step 1: Collect coordinates of all 1s in both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }
        
        // Step 2: Map to count occurrences of each specific translation vector
        Map<String, Integer> vectorCounts = new HashMap<>();
        int maxOverlap = 0;
        
        // Step 3: Calculate the delta/shift between every pair of 1s
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int rowShift = p1[0] - p2[0];
                int colShift = p1[1] - p2[1];
                
                // Create a unique key string for this specific layout shift
                String key = rowShift + "," + colShift;
                
                // Increment the frequency of this offset configuration
                vectorCounts.put(key, vectorCounts.getOrDefault(key, 0) + 1);
                maxOverlap = Math.max(maxOverlap, vectorCounts.get(key));
            }
        }
        
        return maxOverlap;
    }
}