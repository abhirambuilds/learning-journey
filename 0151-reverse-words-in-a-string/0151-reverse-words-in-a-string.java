class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) return "";
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            if (i < 0) {
                break;
            }
            int end = i + 1;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(s.substring(i + 1, end));
        }
        return result.toString();
    }
}