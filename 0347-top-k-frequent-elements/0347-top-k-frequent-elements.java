class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        List<Integer>[] bucket=new ArrayList[nums.length+1];
        for(Map.Entry<Integer,Integer> m:map.entrySet()){
            int freq=m.getValue();
            if(bucket[freq]==null) bucket[freq]=new ArrayList<>();
            bucket[freq].add(m.getKey());
        }
        int ans[]=new int[k];
        int index=0;
        for(int i=nums.length;i>=0;i--){
            if(bucket[i]==null) continue;
            for(int num:bucket[i]){
                ans[index++]=num;
                if(index==k) return ans;
            }
        }
        return ans;
    }
}