public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Array to store the last seen index of each ASCII character
        int[] lastSeen = new int[128];
        
        // Initialize all positions to -1 indicating they haven't been seen yet
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }
        
        int maxLength = 0;
        int left = 0; // Left boundary of the sliding window
        
        // Expand the window using the right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If the character was seen inside the current window, 
            // shrink the window by moving 'left' to the right of its last occurrence
            if (lastSeen[currentChar] >= left) {
                left = lastSeen[currentChar] + 1;
            }
            
            // Record/update the current character's index
            lastSeen[currentChar] = right;
            
            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}