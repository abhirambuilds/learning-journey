import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Map keys to their corresponding values for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        // Step 2: Iterate through the string in a single pass
        while (i < n) {
            char curr = s.charAt(i);
            
            if (curr == '(') {
                int start = i + 1;
                // Move forward until we find the closing bracket
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }
                // Extract the bracket key string
                String key = s.substring(start, i);
                // Append the replacement value or "?" if not found
                result.append(map.getOrDefault(key, "?"));
            } else {
                result.append(curr);
            }
            i++;
        }
        
        return result.toString();
    }
}
