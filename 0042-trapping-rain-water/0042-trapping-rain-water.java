//Water=min(Leftmax,RightMax)-currentHeight
class Solution {
    public int trap(int[] height) {
        int n=height.length;
        int leftMax[]=new int[n];
        leftMax[0]=0;
        int rightMax[]=new int[n];
        rightMax[n-1]=0;
        for(int i=1;i<n;i++){
            leftMax[i]=Math.max(height[i-1],leftMax[i-1]);
        }
        for(int i=n-2;i>=0;i--){
            rightMax[i]=Math.max(height[i+1],rightMax[i+1]);
        }
        int water[]=new int[n];
        for(int i=0;i<n;i++){
            water[i]=Math.max(Math.min(leftMax[i],rightMax[i])-height[i],0);
        }
        int sol=0;
        for(int w:water){
            sol+=w;
        }
        return sol;
    }
}