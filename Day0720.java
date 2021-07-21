package InterviewImportant.Shuati;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day0720 {

    public static boolean exist(char[][] board, String word) {
        if(board==null||board.length==0||board[0].length==0){
            return false;

        }

        char[] chars = word.toCharArray();

        boolean[][] flag = new boolean[board.length][board[0].length];

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,chars,i,j,flag,0)){
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean dfs(char[][] board, char[] chars, int i, int j, boolean[][] flag, int start){
        if(i<0||i>=board.length||j<0||j>=board[0].length||chars[start]!=board[i][j]||flag[i][j]){
            return false;
        }

        if(start==chars.length-1){

            return true;
        }

        flag[i][j] = true;
        boolean ans = false;
        ans = dfs(board,chars,i+1,j,flag,start+1)
                ||dfs(board,chars,i-1,j,flag,start+1)
                ||dfs(board,chars,i,j+1,flag,start+1)
                ||dfs(board,chars,i,j-1,flag,start+1);

        flag[i][j] = false;
        return ans;
    }


    public static void main4(String[] args) {

        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String s = "ABCCED";

        System.out.println(exist(board, s));
    }


    //根据前序和中序遍历构建二叉树
    public TreeNode buildTree1(int[] preorder, int[] inorder) {

        if (preorder.length == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        for (int x:inorder){

            list.add(x);
        }
        return Helper(preorder,list);

    }

    int pos = 0;
    private TreeNode Helper(int[] preorder, List<Integer> list) {

        if (preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[pos]);
        int index = list.indexOf(root.val);
        pos++;

        root.left = Helper(preorder,list.subList(0,index));
        root.right = Helper(preorder,list.subList(index+1,preorder.length));
        return root;

    }


    //根据中序和后序建立二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {

        if (inorder.length == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();

        for (int x: inorder){
            list.add(x);
        }
        int pos = postorder.length-1;
        return Helper1(list,postorder,pos);

    }



    private TreeNode Helper1(List<Integer> list, int[] postorder,int pos) {

        if (postorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[pos]);

        int index = list.indexOf(root.val);
        root.left = Helper1(list,postorder,pos--);
        root.right = Helper1(list,postorder,pos--);
        return root;
    }




}
