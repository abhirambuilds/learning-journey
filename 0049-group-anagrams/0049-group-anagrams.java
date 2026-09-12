class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char s[]=str.toCharArray();
            Arrays.sort(s);
            String key=String.valueOf(s);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<> (map.values());
    }
}