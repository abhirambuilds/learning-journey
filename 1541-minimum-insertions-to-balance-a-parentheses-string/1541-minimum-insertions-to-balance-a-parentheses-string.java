class Solution {
    public int minInsertions(String s) {
        int insertions = 0;      // Tracks total required insertions
        int neededRight = 0;     // Tracks the number of closing brackets ')' needed

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If we need an odd number of ')', it means we have a single ')' 
                // lingering. We must immediately fix it by adding one ')' to pair it up.
                if (neededRight % 2 != 0) {
                    insertions++; // Insert 1 ')'
                    neededRight--; // Decrease needed right count because we manually balanced it
                }
                // Each new '(' requires 2 closing brackets
                neededRight += 2;
            } else { // c == ')'
                neededRight--; // We found a closing bracket, so we need one less

                // If neededRight drops below 0, it means we have an unexpected ')'
                if (neededRight < 0) {
                    insertions++;     // Insert 1 '(' to balance the current group
                    neededRight += 2; // This newly inserted '(' demands 2 right brackets, 
                                      // but we use 1 from the current index, so net increase is 2
                }
            }
        }

        // At the end, any remaining required right brackets must be manually inserted
        return insertions + neededRight;
    }
}