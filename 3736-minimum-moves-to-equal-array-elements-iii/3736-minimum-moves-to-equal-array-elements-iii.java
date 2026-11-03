class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int moves=0;
        for(int i=0;i<nums.length;i++){
            moves+=nums[nums.length-1]-nums[i];
        }
        return moves;
    }
}