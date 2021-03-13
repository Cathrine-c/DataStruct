package InterviewImportant.DS;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
    char val;
    TreeNode left;
    TreeNode right;


    public TreeNode(char val) {
        this.val = val;
    }



}
public class MyTree {

    //构建二叉树

    public static TreeNode buildTree(){
        TreeNode A= new TreeNode('A');
        TreeNode B= new TreeNode('B');
        TreeNode C= new TreeNode('C');
        TreeNode D= new TreeNode('D');
        TreeNode E= new TreeNode('E');
        TreeNode F= new TreeNode('F');


        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        return A;
    }




    public static void main(String[] args) {
//        preOrder(buildTree());
//        System.out.println();
//        Inorder(buildTree());
//        System.out.println();
//        postOrder(buildTree());

        System.out.println(height(buildTree()));
    }


    //前序遍历,递归遍历
    public static void preOrder(TreeNode root){
        if (root == null) {
            return;
        }
        System.out.print(root.val+" ");
        preOrder(root.left);
        preOrder(root.right);
    }


    //中序遍历,递归遍历
    public static void Inorder(TreeNode root){
        if (root == null) {
            return;
        }

        Inorder(root.left);
        System.out.print(root.val+" ");
        Inorder(root.right);

    }


    //后序遍历，递归遍历
    public static void postOrder(TreeNode root){
        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+" ");
    }


    //层序遍历,队列实现
    public static ArrayList<ArrayList<Character>> levelOrder(TreeNode root){


        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Character>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            int count = queue.size();
            ArrayList<Character> list = new ArrayList<>();

            while (count > 0) {
                TreeNode node = queue.poll();

                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);

                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                count--;
            }

                result.add(list);
            }

        return result;
    }


    //求二叉树的高度（深度）
    public static int height(TreeNode root){
        if (root == null) {
            return 0;
        }

        return 1+Math.max(height(root.left),height(root.right));
    }


    //判断两棵树是否相同
    public static boolean isSameTree(TreeNode p,TreeNode q){
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }
        if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }

            return isSameTree(p.left,q.right)&&isSameTree(p.right,q.left);
        }
        return false;
    }


    //判断一棵树是否为另一棵树的子树
    public static boolean ischildTree(TreeNode p,TreeNode q){
        if (p == null) {
            return false;
        }

        return ischildTreeHelper(p,q)||ischildTreeHelper(p.left,q)||ischildTreeHelper(p.right,q);

    }


    private static boolean ischildTreeHelper(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return ischildTreeHelper(p.left,q.left)&&ischildTreeHelper(p.right,q.right);
    }


    //判断一棵二叉树是否为镜像二叉树（对称）
    public static boolean issymetricTree(TreeNode root){
        if (root == null) {
            return true;
        }

        return issymetricTreeHelper(root.left,root.right);

    }


    private static boolean issymetricTreeHelper(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return issymetricTreeHelper(p.left,q.right)&&issymetricTreeHelper(p.right,q.left);
    }


    //判断一棵树是否为平衡二叉树
    public static boolean isBalanceTree(TreeNode root){
        if (root == null) {
            return true;
        }

        if (Math.abs(height(root.left)-height(root.right))>1){
            return false;
        }

        return isBalanceTree(root.left)&&isBalanceTree(root.right);

    }



    //给定一棵树，找出两个指定节点的最近公共祖先
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null||root==p||root==q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left == null && right == null) {
            return null;
        }
        if (left==null)return right;
        if (right==null)return left;
        return root;

    }


    //输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
    static TreeNode prev ;
    static TreeNode head;

    public static TreeNode Convert(TreeNode pRootOfTree) {
        head = null;
        inOrder(pRootOfTree);

        return head;
    }

    public static void inOrder(TreeNode pRootOfTree){

        if (pRootOfTree != null) {
            inOrder(pRootOfTree.left);
        }

        if (head == null) {
            head = pRootOfTree;

        }

        if (prev == null) {
            pRootOfTree.left = null;
        }else {
            prev.right = pRootOfTree;
            pRootOfTree.left = prev;
        }

        prev = pRootOfTree;
        inOrder(pRootOfTree.right);

    }


    //采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        tree2StrHelper(t,sb);
        return sb.toString();
    }

    private void tree2StrHelper(TreeNode t, StringBuilder sb) {
        if (t != null) {
            sb.append(t.val);

            if (t.left != null && t.right != null) {
                sb.append('(');
                tree2StrHelper(t.left,sb);
                sb.append(')');
            }

            if (t.right != null) {
                sb.append('(');
                tree2StrHelper(t.right,sb);
                sb.append(')');
            }
        }
    }


    //根据前序遍历和中序遍历构建二叉树
    public TreeNode buildTree(char[] preorder, char[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        for (int i=0;i<preorder.length;i++){
            if (preorder[0]==inorder[i]){
                root.left = buildTree(Arrays.copyOfRange(preorder,1,i+1),Arrays.copyOfRange(inorder,0,i));
                root.right = buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length),Arrays.copyOfRange(inorder,i+1,inorder.length));
                break;
            }
        }
        return root;

    }


}
