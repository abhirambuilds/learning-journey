class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int start=1;
        int end=0;
        for(int p:piles){
            end=Math.max(end,p);
        }
        while(start<end)
        {   int mid=start+(end-start)/2;
            long hours=0;
            for(int p:piles){
                hours+=(p+mid-1)/mid;
            }
            if(hours<=h){
                end=mid;
            }
            else{
                start=mid+1;
            }
        }
        return start;
    }
}