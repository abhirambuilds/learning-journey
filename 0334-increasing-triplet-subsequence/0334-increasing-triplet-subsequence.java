/* CORE LOGIC:
    u just need to find one tripet in whole array that justify nums[i] < nums[j] < nums[k]
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int smallest=Integer.MAX_VALUE;
        int secondSmallest=Integer.MAX_VALUE;
        for(int i:nums){
            if(i<=smallest) smallest=i;
            else if(i<=secondSmallest) secondSmallest=i;
            else return true;
        }
        return false;
    }
}