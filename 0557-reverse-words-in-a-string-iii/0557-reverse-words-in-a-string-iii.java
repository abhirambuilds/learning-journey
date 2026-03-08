class Solution {
    public String reverseWords(String s) {
        char c[]=s.toCharArray();
        int start=0;
        for(int i=0;i<=c.length;i++){
            if(i==c.length || c[i]==' '){
                int left=start;
                int right=i-1;
                while(left <right){
                    char temp=c[left];
                    c[left]=c[right];
                    c[right]=temp;
                    left++;
                    right--;
                }
                
            start = i+ 1;
            }
        }
        return new String(c);
    }
}