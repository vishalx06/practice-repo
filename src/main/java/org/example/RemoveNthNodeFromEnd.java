package org.example;

    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
public class RemoveNthNodeFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        for(int i =0; i<=n ; i++){
            fast = fast.next;
        }

        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
