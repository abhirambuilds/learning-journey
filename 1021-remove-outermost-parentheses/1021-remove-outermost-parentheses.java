class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder res=new StringBuilder();
        int opened=0;
        for(char c:s.toCharArray()){
            if(c=='('){
                if(opened>0){
                    res.append(c);
                }
                opened++;
            }else{
                opened--;
                if(opened>0){
                    res.append(c);
                }
            }
        }
        return res.toString();
    }
}