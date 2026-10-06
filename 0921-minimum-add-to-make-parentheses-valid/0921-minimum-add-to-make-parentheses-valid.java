class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;   // Unmatched '(' needing a ')'
        int closeNeeded = 0;  // Unmatched ')' needing a '('
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openNeeded++;
            } else if (c == ')') {
                if (openNeeded > 0) {
                    openNeeded--; // Match with an existing '('
                } else {
                    closeNeeded++; // No '(' available, need one
                }
            }
        }
        return openNeeded + closeNeeded;
    }
}