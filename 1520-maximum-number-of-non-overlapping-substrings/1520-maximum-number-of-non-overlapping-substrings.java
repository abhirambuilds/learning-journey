
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            if (first[charIdx] == -1) {
                first[charIdx] = i;
            }
            last[charIdx] = i;
        }
        
        List<int[]> validIntervals = new ArrayList<>();
        
        // Step 2: For each character, find its valid expanded interval
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int left = first[i];
            int right = last[i];
            int currentRight = right;
            boolean isValid = true;
            
            // Expand the window to include all instances of any inner characters
            for (int j = left; j <= currentRight; j++) {
                int innerCharIdx = s.charAt(j) - 'a';
                
                // If an inner character started before our left index, 
                // this interval cannot validly start at 'left'.
                if (first[innerCharIdx] < left) {
                    isValid = false;
                    break;
                }
                currentRight = Math.max(currentRight, last[innerCharIdx]);
            }
            
            if (isValid) {
                validIntervals.add(new int[]{left, currentRight});
            }
        }
        
        // Step 3: Sort intervals greedily by their end points
        Collections.sort(validIntervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        
        // Step 4: Pick non-overlapping intervals
        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];
            
            if (start > lastEnd) {
                result.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }
        
        return result;
    }
}