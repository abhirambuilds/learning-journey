class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        return search(letters, target, 0);
    }
    public char search(char[] letters, char target, int idx) {
        if(idx == letters.length)
            return letters[0];
        if(letters[idx] > target)
            return letters[idx];
        return search(letters, target, idx + 1);
    }
}