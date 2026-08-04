class Solution {
    public String largestOddNumber(String num) {
        for(int i=num.length()-1;i>=0;i--)
        {   char n=num.charAt(i);
            if(n== '1' ||n== '3' ||n== '5' ||n== '7' ||n== '9'){
                return num.substring(0,i+1);
            }
        }
        return "";
    }
}