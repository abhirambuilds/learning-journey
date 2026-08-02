class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> mp=new HashMap<>();
        for(int num:nums){
            mp.put(num,mp.getOrDefault(num,0)+1);
        }
        for(int n:mp.keySet()){
            if(mp.get(n)>nums.length/2){
                return n;
            }
        }
        return -1;
    }
}