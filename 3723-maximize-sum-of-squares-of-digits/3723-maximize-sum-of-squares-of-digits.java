class Solution {
    public String maxSumOfSquares(int num, int sum) {
                // Step 1: Check if the required sum is achievable
        if (sum > 9 * num) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Step 2: Append as many '9's as possible
        int nines = sum / 9;
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        
        // Step 3: Append the remaining digit, if any
        int remainder = sum % 9;
        if (nines < num && remainder > 0) {
            sb.append(remainder);
        }
        
        // Step 4: Fill the rest of the positions with zeros
        while (sb.length() < num) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}