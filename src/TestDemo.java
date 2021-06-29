
public class TestDemo {

    //找到两个单链表的公共节点
    //求两个链表的长度
    //让长链表走差值·步
    //两个再一起走，判断是否相等
    public static Node getIntersection(Node headA,Node headB){
        int lenA=0;
        int lenB=0;
        Node pl=headA;
        Node ps=headB;
        int len=lenA-lenB;
        while (len<0){
            pl=headB;
            ps=headA;
        }
        for (int i=0;i<len;i++){
            pl=pl.next;
        }
        while (pl!=ps){
            pl=pl.next;
            ps=ps.next;
        }
        if(pl!=null){
            return pl;
        }
        return null;
    }

    //把两个有序单链表合并成一个有序单链表
    public Node mergeTwoLists(Node headA,Node headB){
        Node newhead=new Node(-1);
        Node tmp=newhead;
        while (headA!=null&&headB!=null){
            if (headA.data<headB.data) {
                tmp.next=headA;
                tmp=tmp.next;
                headA=headA.next;

            }else{
                tmp.next=headB;
                tmp=tmp.next;
                headB=headB.next;
            }
        }
        if (headA != null) {
            tmp.next=headA;
        }
        if(headB!=null){
            tmp.next=headB;
        }
        return newhead.next;
    }


    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addFirst(6);
        myLinkedList.addFirst(4);
        myLinkedList.addFirst(3);
        myLinkedList.addLast(5);
        myLinkedList.addLast(9);

        MyLinkedList myLinkedList2 = new MyLinkedList();
        myLinkedList2.addFirst(10);
        myLinkedList2.addFirst(20);
        myLinkedList2.addFirst(30);
        myLinkedList2.addLast(50);
        myLinkedList2.addLast(90);

       // myLinkedList.addLast(2);
       // myLinkedList.insertIndex(4,0);
       // System.out.println(myLinkedList.contains(4));
        //System.out.println(myLinkedList.size());
        //myLinkedList.clear();

        //myLinkedList.removeAllKey(6);
        //myLinkedList.removeKey(5);
        //myLinkedList.removeAllKey(3);
        myLinkedList.display();
        myLinkedList2.display();
        createCut(myLinkedList.head,myLinkedList2.head);
        Node ret=getIntersection(myLinkedList.head,myLinkedList2.head);
        System.out.println(ret.data);
        //Node ret=myLinkedList.reverseList();
        //myLinkedList.display2(ret);
       // System.out.println(myLinkedList.middleNode().data);

    }

    public static void createCut(Node headA, Node headB) {
        headA.next=headB.next.next;
    }
}
