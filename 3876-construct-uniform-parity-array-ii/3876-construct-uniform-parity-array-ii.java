/*  Core LOGIC:
    odd  - odd  = even
    even - even = even
    odd  - even = odd 
    even - odd  = odd
 */
class Solution {
    public boolean uniformArray(int[] nums1) {
        if(allEven(nums1)) return true;
        if(allOdd(nums1)) return true;
        int minOdd=Integer.MAX_VALUE;
        for(int i:nums1){
            if(i%2!=0){
                minOdd=Math.min(i,minOdd);
            }
        }
        for(int i:nums1){
            if(i%2==0){
                if(i<minOdd) return false;
            }
        }
        return true;

    }
    private static boolean  allEven(int[] nums){
        for(int i:nums){
            if(i%2!=0) return false;
        }
        return true;
    }
    private static boolean allOdd(int[] nums){
        for(int i:nums){
            if(i%2==0) return false;
        }
        return true;
    }
}