class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        int max=1;
        Set<Integer> st=new HashSet<>();
        for(int num:nums) st.add(num);
        for(int num:st){
            if(!st.contains(num-1)){
                int start=num;
                int length=1;
                while(st.contains(start+1)){
                    start++;
                    length++;
                }
                max=Math.max(max,length);
            }
        }
        return max;
    }
}