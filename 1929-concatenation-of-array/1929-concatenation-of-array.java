class Solution {
    public int[] getConcatenation(int[] nums) {
        int nums2[]=Arrays.copyOf(nums,nums.length);
        int res[]=new int[nums.length*2];
        System.arraycopy(nums,0,res,0,nums.length);
        System.arraycopy(nums2,0,res,nums.length,nums2.length);
        return res;
    }
}