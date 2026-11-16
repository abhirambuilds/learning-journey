class Solution {
    public String digitSum(String s, int k) {
        // Continue the process until the string length is less than or equal to k
        while (s.length() > k) {
            StringBuilder nextString = new StringBuilder();
            
            // Traverse the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                // Get the current chunk (handle the last chunk gracefully)
                String chunk = s.substring(i, Math.min(i + k, s.length()));
                
                // Calculate the sum of digits in the current chunk
                int sum = 0;
                for (char ch : chunk.toCharArray()) {
                    sum += ch - '0'; // Convert char to its numeric value
                }
                
                // Append the sum to the new string
                nextString.append(sum);
            }
            
            // Update s with the newly constructed string
            s = nextString.toString();
        }
        
        return s;
    }
}
