class Solution {
    public int countCommas(int n) {
        int num=n-1000+1;
        if(num>0){
            return num;
        }
        return 0;
    }
}