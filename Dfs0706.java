package InterviewImportant.Arithmiy.DFS;

import java.util.ArrayList;
import java.util.Arrays;

public class Dfs0706 {


    public static void main(String[] args) {

    }


    //机器人的移动范围
    public int movingCount(int m, int n, int k) {

        boolean[][] flag = new boolean[m][n];

        return dfs(0, 0, m, n, k, flag);

    }

    private int dfs(int r, int c, int m, int n, int k, boolean[][] flag) {

        if (r < 0 || r >= m || c < 0 || c >= n || (r / 10 + r % 10 + c / 10 + c % 10 > k) || flag[r][c]) {

            return 0;
        }

        flag[r][c] = true;

        return 1 + dfs(r + 1, c, m, n, k, flag) + dfs(r, c + 1, m, n, k, flag) + dfs(r - 1, c, m, n, k, flag) + dfs(r, c - 1, m, n, k, flag);

    }



    //输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
    //B是A的子结构， 即 A中有出现和B相同的结构和节点值。
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if (A == null||B==null) {
            return false;
        }

        return dfs(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B);

    }


    private boolean dfs(TreeNode A,TreeNode B){

        if (B==null)return true;
        if (A==null)return false;

        return A.val==B.val&&dfs(A.left,B.left)&&dfs(A.right,B.right);

    }



    //你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
    // 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
    // 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
    public int[] pondSizes(int[][] land) {
        int count = 0;

        ArrayList<Integer> res = new ArrayList<>();

        for (int i=0;i<land.length;i++){

            for (int j=0;j<land[0].length;j++){

                if (land[i][j]==0) {
                    count = dfs(land, i, j);

                }
            }
        }
        int[] list = new int[res.size()];
        for (int i=0;i<res.size();i++){
            list[i] = res.get(i);

        }

        Arrays.sort(list);

        return list;
    }

    private int dfs(int[][] land,int row,int col){

        if (row<0||row>=land.length||col<0||col>=land[0].length||land[row][col]!=0){

            return 0;
        }
        land[row][col] = -1;
        int count = 1;

        count+=dfs(land,row+1,col);
        count+=dfs(land,row-1,col);
        count+=dfs(land,row,col+1);
        count+=dfs(land,row,col-1);
        count+=dfs(land,row+1,col+1);
        count+=dfs(land,row-1,col-1);
        count+=dfs(land,row-1,col+1);
        count+=dfs(land,row+1,col-1);

        return count;

    }



}
