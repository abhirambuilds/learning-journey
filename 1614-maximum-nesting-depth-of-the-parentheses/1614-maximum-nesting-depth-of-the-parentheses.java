class Solution {
    public int maxDepth(String s) {
        int maxDep=0;
        int currDep=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                currDep++;
                maxDep=Math.max(currDep,maxDep);
            }else if(s.charAt(i)==')') currDep--;
        }
        return maxDep;
    }
}