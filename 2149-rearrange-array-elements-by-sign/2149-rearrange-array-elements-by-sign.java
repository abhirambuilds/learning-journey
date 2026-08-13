class Solution {
    public int[] rearrangeArray(int[] nums) {
        int positiveIdx=0;
        int negativeIdx=1;
        int[] ans=new int[nums.length];
        for(int num:nums) {
            if(num>0) 
            {   ans[positiveIdx]=num;
                positiveIdx+=2;
            }
            else    
            {   ans[negativeIdx]=num;
                negativeIdx+=2;
            }
        }
        return ans;
    }
}