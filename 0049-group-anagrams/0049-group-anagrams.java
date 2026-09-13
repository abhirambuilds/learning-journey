class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char ar[]=str.toCharArray();
            Arrays.sort(ar);
            String key=String.valueOf(ar);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<> (map.values());
    }
}