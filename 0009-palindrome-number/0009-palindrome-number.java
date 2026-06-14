class Solution {
    public boolean isPalindrome(int x) {
        int num=x;
        if (x < 0)
            return false;
        int rev = 0;
        int digit;
        while (x!=0) {
            digit=x%10;
            x/=10;
            rev=rev*10+digit;
        }
        return num == rev?true:false;
    }
}
