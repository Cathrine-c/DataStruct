package InterviewImportant.DS;

import java.util.List;

class ListNode{
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }


}

public class MyList {


    public static ListNode build1(){

        ListNode A = new ListNode(1);
        ListNode B = new ListNode(2);
        ListNode C = new ListNode(3);
        ListNode D = new ListNode(4);
        ListNode E = new ListNode(5);

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        return A;
    }

    public static ListNode build(){

        ListNode A = new ListNode(3);
        ListNode B = new ListNode(6);
        ListNode C = new ListNode(8);
        ListNode D = new ListNode(9);
        ListNode E = new ListNode(12);

        A.next = B;
        B.next = C;
        C.next = D;
        D.next = B;
       // D.next = E;
        return A;
    }

    public static void display(ListNode head){
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
    }


    public static void main(String[] args) {
      //  display(build());
       // System.out.println();
     //   display(build1());
        //System.out.println();
        //display(deleteVal(build(),9));
        //display(reverse(build()));
        //display(midNode(build()));
        //display(kNode(build(),4));

       // display(mergeNode(build1(),build()));

        //System.out.println(isPalindromeList(build()));

        //display(separateList(build(),4));
        //display(deleteSameNode(build()));

        //display(shareNode(build1(),build()));
       // System.out.println(isHasCycle(build()));
        //display(detectCycle(build()));
        display(firstCysleNode(build()));
    }


    //删除给定值为data的所有节点
    public static ListNode deleteVal(ListNode head,int data){

        if (head == null) {
            return null;
        }


        ListNode prev = head;
        ListNode cur = head.next;


        while (cur != null) {
            if (cur.val == data) {
                prev.next = cur.next;
                cur = prev.next;

            }else {
                cur = cur.next;
                prev = prev.next;
            }

        }

        if (head.val == data) {
            head = head.next;
        }
        return head;
    }


    //反转单链表
    public static ListNode reverse(ListNode head){

        //如果链表为空或者只有一个节点，就返回它本身
        if (head == null||head.next==null) {
            return head;
        }

        ListNode Next =null;
        ListNode pre = null;


        while (head != null) {
            Next = head.next;
            head.next = pre;
            pre = head;
            head = Next;

        }

        return pre;
    }

    //返回链表的中间节点
    public static ListNode midNode(ListNode head){
        if (head == null) {
            return null;
        }
        //定义快慢节点，慢指针走一步，快指针走两步
        ListNode fast = head;
        ListNode slow = head;

        //当fast指向空时，slow指向的节点就是中间节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;

    }

    //输入一个链表，输出该链表中倒数第k个结点。
    public static ListNode kNode(ListNode head,int k){
        if (k < 0) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (k>0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }



   // 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public static ListNode mergeNode(ListNode l1,ListNode l2){
        ListNode newHead = new ListNode(-1);

        ListNode cur = newHead;

        if (l1 == null&&l2==null) {
            return null;
        }

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;

            }else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }

        return newHead.next;
    }



    //链表的回文结构
    public static boolean isPalindromeList(ListNode head){

        ListNode slow = head;
        ListNode fast = head;
        //找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode pre = null;
        ListNode Next = null;

        //将后半部分反转
        while (slow != null) {
            Next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = Next;
        }

        //与前半部分进行比较
        while (pre != null) {
            if (pre.val == head.val) {
                pre= pre.next;
                head = head.next;
            }else {
                return false;
            }
        }
        return true;
    }


    //以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
    public static ListNode separateList(ListNode head,int x){
       ListNode as = null;
       ListNode ae = null;
       ListNode bs = null;
       ListNode be = null;

       ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (as == null) {
                    as = cur;
                    ae = as;

                }else {
                    ae.next = cur;
                    ae = ae.next;

                }
            }
            if (cur.val >= x) {
                if (bs == null) {
                    bs = cur;
                    be = bs;

                }else {
                    be.next = cur;
                    be = be.next;
                }
            }
            cur = cur.next;
        }

        if (as!=null&&bs != null) {
            ae.next = bs;
            be.next = null;
        }

        if (as != null) {
            return as;
        }

        if (bs != null) {
            return bs;
        }
        return as;
    }


    // 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
    public static ListNode deleteSameNode(ListNode head){
        ListNode cur = head;
        if (cur == null || cur.next == null) {
            return cur;
        }

        while (cur != null&&cur.next!=null) {
            if (cur.val != cur.next.val) {
                cur = cur.next;
            }else {

                cur.next = cur.next.next;
            }
        }

        return head;
    }


    // 输入两个链表，找出它们的第一个公共结点
    public static ListNode shareNode(ListNode l1,ListNode l2){
        if (l1==null|| l2 == null) {
            return null;
        }

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        ListNode cur = new ListNode(-1);
        ListNode newCur = cur;
        while (cur1 != null && cur2 != null) {
            if (cur1.val<cur2.val) {
                cur1 = cur1.next;

            } else if (cur1.val > cur2.val) {
                cur2 = cur2.next;
            }else {
                return cur1;
            }

        }
        return null;
    }



    //给定一个链表，判断链表中是否有环。
    public static boolean isHasCycle(ListNode head){

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null&&fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }


    //给定一个链表，返回链表开始入环的第一个节点。
    public static ListNode firstCysleNode(ListNode head){

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null&&fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }

        ListNode newhead = head;
        while (newhead != slow) {
            newhead = newhead.next;
            slow = slow.next;
        }

        return slow;
    }


    //杨辉三角的打印
    public List<List<Integer>> generate(int numRows) {
        return null;


    }

}
