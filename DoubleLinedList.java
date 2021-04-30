class Node {
    public int val;
    public Node next;
    public Node prev;

    public Node(int val) {
        this.val = val;
    }
}

public class DoubleLinedList {
    public Node head;
    public Node last;


    public void addFirst(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
            this.last = node;
        } else {

            node.next = this.head;
            node.prev = null;
            this.head = node;
        }
    }


    public void addLast(int data) {
        Node node = new Node(data);
        if (this.last == null) {
            this.head = node;
            this.last = node;
        } else {
            this.last.next = node;
            node.next = null;
            this.last = node;
        }
    }


    private Node findIndex(int index) {
        Node cur = this.head;
        while(index>0) {
            cur = cur.next;
            index --;
        }
        return cur;
    }


    private void checkIndex(int index) {
        if(index<0||index>size()) {
            System.out.println("index不合理");
        }
    }

    public void addIndex(int index,int data) {
        checkIndex(index);
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size()) {
            addLast(data);
            return;
        }
            Node cur = findIndex(index);
            Node node = new Node(data);
            node.next = cur;
            node.prev = cur.prev;
            cur.prev = node;
            node.prev.next = node;
    }


    public boolean contains(int key) {
        Node cur = this.head;
        while(cur!=null) {
            if(cur.val==key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    public void removeKey(int key) {
        Node cur = this.head;
        while(cur != null) {
            if (cur.val == key) {
                if (cur == this.head) {
                    this.head = this.head.next;
                    this.head.prev = null;
                } else {
                    cur.prev.next = cur.next;
                    if (cur.next != null) {
                        cur.next.prev = cur.prev;
                    }else{
                        this.last = cur.prev;
                    }
                }
                return;
            }
            cur = cur.next;
        }
    }

    public void removeAllKey(int key) {
        Node cur = this.head;
        while(cur != null) {
            if(cur.val==key) {
                if(cur == this.head) {
                    this.head = this.head.next;
                    this.head.prev = null;
                }else{
                    cur.prev.next = cur.next;
                    if(cur.next!=null) {
                        cur.next.prev = cur.prev;
                    }else{
                        this.last = cur.prev;
                    }
                }
            }
            cur = cur.next;
        }
    }

    public void display() {
        Node cur = this.head;
        while(cur!=null) {
            System.out.println(cur.val+" ");
            cur = cur.next;
        }
    }


    public int size() {
        Node cur = this.head;
        int count = 0;
        while(cur != null) {
            cur = cur.next;
            count ++;
        }
        return count;
    }
}
