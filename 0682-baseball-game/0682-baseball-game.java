class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s:operations){
            if(s.equals("+")){
                int a=stack.pop();
                int b=stack.peek();
                stack.push(a);
                stack.push(a+b);
            }
            else if(s.equals("D")){
                stack.push(2*stack.peek());
            }else if(s.equals("C")){
                stack.pop();

            }else{
                stack.push(Integer.parseInt(s));
            }
        } 
        int sum=0;
        while(!stack.isEmpty()){
            sum+=stack.pop();
        }
        return sum;
    }
}