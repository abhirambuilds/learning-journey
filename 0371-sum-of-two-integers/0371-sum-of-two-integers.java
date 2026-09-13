class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            // Calculate the carry bits
            int carry = (a & b) << 1;
            
            // Sum without considering the carry
            a = a ^ b;
            
            // Update b to hold the carry for the next iteration
            b = carry;
        }
        return a;
    }
}