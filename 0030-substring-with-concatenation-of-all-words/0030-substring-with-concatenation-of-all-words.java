import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        // Count frequencies of each word in the target array
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int stringLength = s.length();
        int wordCountSize = words.length;
        int wordLength = words[0].length();
        int totalWordsLength = wordLength * wordCountSize;

        // Loop through all possible alignments/offsets within the length of a single word
        for (int i = 0; i < wordLength; i++) {
            int left = i;
            int right = i;
            int matchedWordsCount = 0;
            Map<String, Integer> currentWindowWords = new HashMap<>();

            // Slide the window forward by wordLength chunks
            while (right + wordLength <= stringLength) {
                // Extract the next word chunk from the right pointer
                String currentWord = s.substring(right, right + wordLength);
                right += wordLength;

                // Case 1: If the word is part of our target dictionary
                if (wordCount.containsKey(currentWord)) {
                    currentWindowWords.put(currentWord, currentWindowWords.getOrDefault(currentWord, 0) + 1);
                    matchedWordsCount++;

                    // Case 2: If the word frequency exceeds what is allowed, shrink window from left
                    while (currentWindowWords.get(currentWord) > wordCount.get(currentWord)) {
                        String leftWord = s.substring(left, left + wordLength);
                        currentWindowWords.put(leftWord, currentWindowWords.get(leftWord) - 1);
                        matchedWordsCount--;
                        left += wordLength;
                    }

                    // Case 3: If the full window matches the exact criteria, save the left index
                    if (matchedWordsCount == wordCountSize) {
                        result.add(left);
                    }
                } 
                // Case 4: The word is completely invalid, reset our window completely
                else {
                    currentWindowWords.clear();
                    matchedWordsCount = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}
