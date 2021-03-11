package java_0125;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的实现
 */

class TreeNode{
    TreeNode left;
    TreeNode right;
    char val;

    public TreeNode(char val) {
        this.val = val;
    }


}
public class MyTree {


    public static TreeNode build(){
        TreeNode a = new TreeNode('A');
        TreeNode b = new TreeNode('B');
        TreeNode c = new TreeNode('C');
        TreeNode d = new TreeNode('D');
        TreeNode f = new TreeNode('F');
        TreeNode g = new TreeNode('G');
        TreeNode h = new TreeNode('H');


        a.left = b;
        a.right = c;
        b.left = d;
        b.right = f;
        c.left = g;
        c.right = h;

        return a;
    }

    //先序遍历
    public static void preOrder(TreeNode root){
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    //中序遍历
    public static void inOrder(TreeNode root){
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }


    //后序遍历
    public static void postOrder(TreeNode root){
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+" ");
    }


    //求节点个数
    public static int size(TreeNode root){
        if (root == null) {
            return 0;
        }

        //整棵树节点个数=根节点+左子树节点+右子树节点
        return 1+size(root.left)+size(root.right);
    }


    //求叶子节点个数
    public int leafSize(TreeNode root){
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return leafSize(root.left)+leafSize(root.right);
    }


    //判断一棵树是否为另一棵树的子树
    public boolean isSameTree(TreeNode s,TreeNode t){
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        boolean ret = false;
//        if (s.val == t.val) {
//            ret = isSameTree(s,t);
//        }
//        if (!ret) {
//            ret = isSameTree(s.left,t);
//        }
//        if (!ret) {
//            ret = isSameTree(s.right,t);
//        }
//        return ret;

        return ret||isSameTree(s.left,t)||isSameTree(s.right,t);

    }


    //求二叉树的高度
    public int maxDepth(TreeNode root){
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftheight = maxDepth(root.left);
        int rightheight = maxDepth(root.right);
        return 1+Math.max(leftheight,rightheight);

    }

    //判断一棵树是否为平衡二叉树
    public boolean isBalanced(TreeNode root){
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        int leftheight = maxDepth(root.left);
        int rightheight = maxDepth(root.right);

        if (leftheight - rightheight > 1 || leftheight - rightheight < -1) {
            return false;
        }

        return isBalanced(root.left)&&isBalanced(root.right);

    }


    //判断一棵树是否为镜像二叉树
    public boolean symmetric(TreeNode root){
        if (root == null) {
            return true;
        }
        return isMirror(root.left,root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return isMirror(t1.left,t2.right)&&isMirror(t1.right,t2.left);

    }


    //层序遍历 ，用队列实现
    public static void levelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.val);

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }



    /**
     * 完全二叉树的三种情况：借助层序遍历
     * 1.左子树和右子树都有
     * 2.只有左子树
     * 3.根结点没有左子树和右子树
     *
     */
    public boolean isCompleteTree(TreeNode root){
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean isSecondStep = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (!isSecondStep) {
                //第一阶段
                if (cur.left != null && cur.right != null) {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else if (cur.left == null && cur.right != null) {
                    return false;
                } else if (cur.left != null && cur.right == null) {
                    isSecondStep = true;
                    queue.offer(cur.left);
                }else{
                    isSecondStep = true;
                }

            }else {
                //第二阶段
                if (cur.left != null || cur.right != null) {
                    return false;
                }
            }
        }
        return true;
    }




    public static void main(String[] args) {
//        System.out.println("先序遍历：");
//        preOrder(build());
//        System.out.println();
//        System.out.println("中序遍历:");
//        inOrder(build());
//        System.out.println();
//        System.out.println("后序遍历：");
//        postOrder(build());
//        System.out.println();
//        System.out.println(size(build()));
//        System.out.println();
        levelOrder(build());
    }


}
