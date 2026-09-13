class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to the basket
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If we have more than 2 types of fruit, shrink the window from the left
            while (basket.size() > 2) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }
                left++;
            }

            // Update the maximum number of fruits collected
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}