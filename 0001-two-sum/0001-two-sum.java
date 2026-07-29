class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hs=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int comp=target-nums[i];
            if(hs.containsKey(comp)){
                return new int [] {hs.get(comp),i}; 
            }
            hs.put(nums[i],i);
        }
        return new int [] {-1,-1};
    }
}