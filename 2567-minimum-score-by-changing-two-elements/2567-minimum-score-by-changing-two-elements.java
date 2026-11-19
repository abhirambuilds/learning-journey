import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        // Sort the array to easily access the smallest and largest elements
        Arrays.sort(nums);
        
        // Option 1: Change the two smallest elements (nums[0] and nums[1])
        int option1 = nums[n - 1] - nums[2];
        
        // Option 2: Change the two largest elements (nums[n - 1] and nums[n - 2])
        int option2 = nums[n - 3] - nums[0];
        
        // Option 3: Change one smallest (nums[0]) and one largest (nums[n - 1])
        int option3 = nums[n - 2] - nums[1];
        
        // Return the minimum range among the three choices
        return Math.min(option1, Math.min(option2, option3));
    }
}
