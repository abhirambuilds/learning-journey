class Solution {
    public void reverseString(char[] s) {
        StringBuilder sb=new StringBuilder();
        sb.append(s);
        for(int i=0;i<sb.length()/2;i++){
            int front=i;
            int back=sb.length()-1-i;
            char frontChar=sb.charAt(front);
            char backChar=sb.charAt(back); 
            sb.setCharAt(front,backChar);
            sb.setCharAt(back,frontChar);
        }
        for(int i = 0; i < s.length; i++){
            s[i] = sb.charAt(i);
        }
    }
}