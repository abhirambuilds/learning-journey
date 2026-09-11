class Solution {
    public int[] findEvenNumbers(int[] digits) {
                int counts[]=new int[10];
        for(int digit:digits){
            counts[digit]++;
        }
        ArrayList<Integer> unq=new ArrayList<>();
        for(int num=100;num<=998;num+=2){
            int digit1=num/100;
            int digit2=(num/10)%10;
            int digit3=num%10;
            counts[digit1]--;
            counts[digit2]--;
            counts[digit3]--;
            if(counts[digit1]>=0 && counts[digit2]>=0 && counts[digit3]>=0) {
                int number=digit1*100+digit2*10+digit3;
                unq.add(number);
            }
            counts[digit1]++;
            counts[digit2]++;
            counts[digit3]++;
        };
        int res[]=new int[unq.size()];
        int i=0;
        for(int n:unq) res[i++]=n;
        return res;
    }
}