class Solution {
    public String frequencySort(String s) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Step 2: Use a max heap based on character frequency
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
            (a, b) -> map.get(b) - map.get(a)
        );
        maxHeap.addAll(map.keySet());
        
        // Step 3: Build the sorted string
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            int freq = map.get(c);
            for (int i = 0; i < freq; i++) {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}