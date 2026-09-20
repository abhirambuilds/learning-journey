class Solution {
    public int reverseDegree(String s) {
        int index=1;
        int sum=0;
        for(char c:s.toCharArray()){
            int a='z'-c+1;
            int val=a*(index++);
            sum+=val;
        }
        return sum;
    }
}