class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                // '*' can be ')', which reduces the minimum open count
                minOpen--;
                // '*' can be '(', which increases the maximum open count
                maxOpen++;
            }

            // If maxOpen is negative, it means we have encountered too many ')'
            // and no combination of '*' can fix it.
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be less than 0. If it falls below 0, it means 
            // we assumed some '*' acted as ')', but they can just act as empty strings instead.
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // The string is valid if we can successfully close all parentheses (minOpen reaches 0)
        return minOpen == 0;
    }
}