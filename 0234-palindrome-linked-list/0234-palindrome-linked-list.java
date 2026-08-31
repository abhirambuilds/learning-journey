/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 /*BRUTE FORCE:
USE STACK ,
        if (head == null || head.next == null) return true;
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode temp=head;
        while (temp!=null) {
            stack.push(temp.val);
            temp=temp.next;
        }
        temp=head;
        while(temp!=null){
            if(temp.val!=stack.pop()){
                return false;
            }
            temp=temp.next;
        }

        return true;

 */
 //OPTIMAL SOLUTION
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode secondHalf=reverse(slow);
        ListNode firstHalf=head;
        while(secondHalf!=null){
            if(firstHalf.val!=secondHalf.val){
                return false;
            }
            secondHalf=secondHalf.next;
            firstHalf=firstHalf.next;
        }
        return true;

    }
    private static ListNode reverse(ListNode head){
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;
    }
}