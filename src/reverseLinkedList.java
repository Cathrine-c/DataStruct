import java.util.List;

//反转单链表
 public class reverseLinkedList {
     public Node head;

     public Node reverseList(Node head) {
         Node cur=head;
         Node prev=null;
         Node newhead=null;
         while (cur!=null){
             Node curNext=cur.next;
         if(curNext==null){
              newhead=cur;
         }
             cur.next=prev;
             prev=cur;
             cur=curNext;
         }
         return newhead;
     }
 }


