class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            List<String> st=new ArrayList<>();
            st.add(str);
            char arr[]=str.toCharArray();
            Arrays.sort(arr);
            String key=String.valueOf(arr);
            if(!map.containsKey(key)){
                map.put(key,st);
            }else{
                map.get(key).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }
}