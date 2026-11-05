class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        
        for (int a : arr1) {
            int i = Arrays.binarySearch(arr2, a);
            i = i < 0 ? -i - 1 : i;
            
            // Check boundaries around a - d and a + d
            if ((i == arr2.length || arr2[i] - a > d) && (i == 0 || a - arr2[i - 1] > d)) {
                ++ans;
            }
        }
        
        return ans;
    }
}