class Solution {
    public int heightChecker(int[] heights) {
        int ori[]=Arrays.copyOf(heights,heights.length);
        int count=0;
        for(int i=0;i<heights.length-1;i++){
            for(int j=0;j<heights.length-1-i;j++){
                if(heights[j]>heights[j+1]){
                    int temp=heights[j];
                    heights[j]=heights[j+1];
                    heights[j+1]=temp;
                }
            }
        }
        for(int i=0;i<heights.length;i++){
            if(heights[i]!=ori[i])
                count++;
        }
        return count;
    }
}