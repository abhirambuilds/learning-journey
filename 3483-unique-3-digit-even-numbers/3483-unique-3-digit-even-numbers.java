class Solution {
    public int totalNumbers(int[] digits) {
        int counts[]=new int[10];
        for(int digit:digits){
            counts[digit]++;
        }
        int unq=0;
        for(int num=100;num<=998;num+=2){
            int digit1=num/100;
            int digit2=(num/10)%10;
            int digit3=num%10;
            counts[digit1]--;
            counts[digit2]--;
            counts[digit3]--;
            if(counts[digit1]>=0 && counts[digit2]>=0 && counts[digit3]>=0) unq++;
            counts[digit1]++;
            counts[digit2]++;
            counts[digit3]++;
        }
        return unq;
    }
}