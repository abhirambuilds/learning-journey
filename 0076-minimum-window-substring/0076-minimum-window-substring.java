class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Frequency map for characters in string t (ASCII size 128 covers all letters)
        int[] targetMap = new int[128];
        for (char c : t.toCharArray()) {
            targetMap[c]++;
        }

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int startIdx = 0;
        
        // Number of characters from t that need to be matched in the window
        int count = t.length();

        // Expand the right boundary of the window
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            // If the character is needed, decrement the total count required
            if (targetMap[rightChar] > 0) {
                count--;
            }
            // Decrement the frequency in our map
            targetMap[rightChar]--;
            right++;

            // When a valid window is found (all characters matched)
            while (count == 0) {
                // Update the minimum window parameters
                if (right - left < minLen) {
                    minLen = right - left;
                    startIdx = left;
                }

                char leftChar = s.charAt(left);
                // Increment the map back as we prepare to discard leftChar
                targetMap[leftChar]++;
                
                // If this character was part of the original string t requirements, 
                // we break the valid window condition
                if (targetMap[leftChar] > 0) {
                    count++;
                }
                
                // Shrink the window from the left
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);
    }
}