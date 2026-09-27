class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Store the current length of StringBuilder
                stack.push(sb.length());
            } else if (c == ')') {
                // Get the start index of the matching '('
                int start = stack.pop();
                // Reverse the substring inside the parentheses
                reverse(sb, start, sb.length() - 1);
            } else {
                // Regular character, just append it
                sb.append(c);
            }
        }
        return sb.toString();
    }
    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }
}