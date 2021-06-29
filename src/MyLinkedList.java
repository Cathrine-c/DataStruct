class Node{
    public Node next;
    public int data;


    public Node(int data){
        this.data=data;
        this.next=next;
    }
}
public class MyLinkedList {
    public Node head;

    //头插法
    public void addFirst(int data){
       Node node=new Node(data);
       if(this.head==null){
           this.head=node;
           return;
       }
       node.next=head;
       head=node;
    }

    //尾插法
    public void addLast(int data){
        Node node = new Node(data);
        Node cur=this.head;
        if(this.head==null){
            this.head=node;
            return;
        }
        while (cur.next!=null){
            cur=cur.next;
        }
        cur.next=node;
    }


//判断是否包含某个值
    public boolean contains(int key){
        Node cur=this.head;
        while (cur!=null){
            if (cur.data == key) {
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

//求链表长度
    public int size(){
        Node cur=this.head;
        int count=0;
        while (cur!=null){
            count++;
            cur=cur.next;
        }
        return count;
    }

//在任意位置插入节点
    public void insertIndex(int index,int data){
        Node node=new Node(data);
        if(index==0){
            addFirst(data);
            return;
        }
        if (index == size()) {
            addLast(data);
            return;
        }
        Node cur=findIndex(index);
        node.next=cur.next;
        cur.next=node;
    }

    //寻找要插入的位置
    private Node findIndex(int index){
        Node cur=this.head;
        if(index<0||index>size()){
            System.out.println("插入位置不合理");
        }
        while (index-1!=0){
            cur=cur.next;
            index--;
        }
        return cur;
        //int count=0;
       // while (count<index-1){
            //count++;
           // cur=cur.next;
       // }
        //return cur;
    }


    public void removeKey(int key){
        if(this.head==null){
            return;
        }
        if (this.head.data == key) {
            this.head=this.head.next;
            return;
        }
        Node prev=findPrev(key);
        if(prev==null){
            System.out.println("没有此节点");
            return;
        }
        Node del=prev.next;
        prev.next=del.next;
    }

    private Node findPrev(int key){
        Node prev=this.head;
        while (prev.next!=null){
            if(prev.next.data==key){
                return prev;
            }
            prev=prev.next;
        }
        return null;
    }


    public void removeAllKey(int key){
        Node prev=this.head;
        Node cur=prev.next;
        while (cur!=null){
            if(cur.data==key){
                prev.next=cur.next;
                cur=cur.next;
            }else{
                prev=cur;
                cur=cur.next;
            }
        }
        if(this.head.data==key){
            this.head=this.head.next;
        }
    }

//反转单链表
    public Node reverseList() {
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

    //返回链表中间节点
    public Node middleNode(){
        Node fast=this.head;
        Node slow=this.head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }


    //打印单链表
    public void display(){
        Node cur=this.head;
        while (cur!=null){
            System.out.println(cur.data+" ");
            cur=cur.next;
        }
        System.out.println();
    }

    public void display2(Node newhead){
        Node cur=newhead;
        while (cur!=null){
            System.out.println(cur.data+" ");
            cur=cur.next;
        }
        System.out.println();
    }

    //返回倒数第k个节点
    public Node findKthToTail(int k){
        Node fast=this.head;
        Node slow=this.head;
        if(k<=0||head==null){
            System.out.println("k不合理");
            return null;
        }
        while (k-1>0){
            if (fast.next != null) {
                fast=fast.next;
                k--;
            }else{
                System.out.println("k不合理");
                return null;
            }
        }
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }

    public Node partition(int x){
        Node bs=null;
        Node be=null;
        Node as=null;
        Node ae=null;
        Node cur=this.head;
        while (cur!=null) {
            if (cur.data < x) {
                if (bs == null) {
                    bs= cur;
                    be = cur;
                } else {
                    be.next = cur;
                    be = be.next;
                }
            } else {
                if (as == null) {
                    as = cur;
                    ae = cur;
                } else {
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur=cur.next;
        }
        if(bs==null){
            return as;
        }
        be.next=as;
        if (as != null) {
            ae.next=null;
        }
        return bs;
    }

    //删除重复数据
    public Node deleteDuplication(){
        Node cur=this.head;
        Node newhead=new Node(-1);
        Node tmp= newhead;
        while (cur!=null){
            if(cur.next!=null && cur.data==cur.next.data){
                while (cur.next!=null&&cur.data==cur.next.data){
                    cur=cur.next;
                }
                cur=cur.next;
            }

            tmp.next=cur;
            tmp=tmp.next;
            cur=cur.next;
        }
        tmp.next=null;
        return newhead.next;
    }

    //判断是否是回文结构如：1 2 3 2 1
    public boolean chkPalindrome(){
        if (this.head == null) {
            return false;
        }
        if (this.head.next == null) {
            return true;
        }
        Node slow=this.head;
        Node fast=this.head;
        //找到单链表的中点
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        //开始反转单链表
        Node cur=slow.next;
        while (cur!=null) {
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //slow为最后一个节点
        //开始一个从头走，一个从尾走
        while (slow!=this.head){
            if(slow.data!=this.head.data){
                return false;
            }
            //判断偶数的情况
            if(this.head.next==slow){
                return true;
            }
            slow= slow.next;
            this.head=this.head.next;
        }
        return true;
    }

    //判断链表是否有环
    public boolean hasCycle(){
        Node slow=this.head;
        Node fast=this.head;
        while (fast!=null&&fast.next!=null) {
                fast = fast.next.next;
                slow = slow.next;
            if (slow == fast) {
                return true;
            }

        }
        return false;
    }

    //返回环的入口点,没有环就返回空
    public Node detectCycle(){
        Node fast=this.head;
        Node slow=this.head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null||fast.next==null) {
            return null;
        }
        slow=this.head;
        while (slow!=fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public void clear(){
        this.head=null;
    }
}
