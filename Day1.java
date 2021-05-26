package InterviewImportant.Shuati;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  class ListNode {
      int val;
     ListNode next;
      ListNode(int x) { val = x; }
  }

public class Day1 {



    public static void main(String[] args) {

    }


    //给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，
    // 则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];

        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(tree);

        List<ListNode> list = new ArrayList<>();
        ListNode prhead = new ListNode(-1);
        while (!queue.isEmpty()) {
            ListNode cur = prhead;

            int size = queue.size();
            for (int i=0;i<size;i++){

                tree = queue.poll();
                cur.next = new ListNode(tree.val);

                cur = cur.next;

                if (tree.left != null) {
                    queue.offer(tree.left);

                }
                if (tree.right != null) {
                    queue.offer(tree.right);

                }
            }
            list.add(prhead.next);

        }

        ListNode[] res = new ListNode[list.size()];

        for (int i=0;i<list.size();i++){
            res[i] = list.get(i);

        }
        return res;

    }


    //实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：
    // 任意一个节点，其两棵子树的高度差不超过 1。
    //利用高度解决
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(depth(root.left)-depth(root.right))<2&&isBalanced(root.left)&&isBalanced(root.right);


    }

    private int depth(TreeNode root) {


        if (root == null) {
            return 0;
        }

        return 1+Math.max(depth(root.left),depth(root.right));

    }


    //方式2：后序遍历解决
    boolean flag = true;
    public boolean isBalanced1(TreeNode root) {

        dfs(root);

        return flag;
    }


    private int dfs(TreeNode root){

        if (root==null)return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);
        if (Math.abs(left-right)>1){
            flag = false;
        }

        return Math.max(left,right)+1;

    }



    //实现一个函数，检查一棵二叉树是否为二叉搜索树。
    //中序遍历,保存前驱节点
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean l = isValidBST(root.left);

        if (!l) {
            return false;
        }

        if (pre == null) {
            pre = root;

        }else {
            if (pre.val >= root.val) {
                return false;
            }
            pre = root;
        }

        return isValidBST(root.right);
    }


    public boolean isValidBST1(TreeNode root) {
        if (root==null) return true;
        TreeNode left = root.left;
        TreeNode right = root.right;

        //找寻左子树中最右节点
        while (left != null && left.right != null) {
            left = left.right;

        }

        //找寻右子树中最左节点
        while (right != null && right.left != null) {
            right = right.left;

        }
        //当前层是否合理
        boolean ret = (left==null||left.val<root.val)&&(right==null||right.val>root.val);
        //进入左子树和右子树判断是否合法
        return ret&&isValidBST(root.left)&&isValidBST(root.right);

    }


    //设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
    //如果指定节点没有对应的“下一个”节点，则返回null。
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        //当前节点值小于等于目标值，那么当前目标值的后继者必然在右子树
        if (root.val <= p.val) {
            return inorderSuccessor(root.right,p);

        }
        //否则结果可能是当前节点，或者在当前节点的左子树中，
        //那么先去左子树找一下试试，找不到的话返回当前节点即结果
        TreeNode node = inorderSuccessor(root.left,p);

        return node==null?root:node;

    }


    //首个公共祖先
    // 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。
    // 注意：这不一定是二叉搜索树。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p||root==q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left!=null&&right!=null)return root;
        return left==null?right:left;


    }



    //检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。
    // 设计一个算法，判断 T2 是否为 T1 的子树。
    //如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，
    //从节点 n 处把树砍断，得到的树与 T2 完全相同。
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        }

        if (t1.val == t2.val) {
            if (dfs(t1,t2)){
                return true;
            }

        }
        boolean flag1 = checkSubTree(t1.left,t2);
        boolean flag2 = checkSubTree(t1.right,t2);
        return flag1||flag2;

    }

    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (t1==null&&t2==null) return true;
        if (t1==null||t2==null||t1.val!=t2.val) return false;
        boolean flag1 = dfs(t1.left,t2.left);
        boolean flag2 = dfs(t1.right,t2.right);

        return flag1&&flag2;

    }


    public int pathSum(TreeNode root, int sum) {


    }



}
