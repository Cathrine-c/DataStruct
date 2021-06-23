package InterviewImportant.DP;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

public class Test5Medium {



    //一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    //机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    //问总共有多少条不同的路径？
    public int uniquePaths(int m, int n) {

        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                dp[i][j] = 1;

            }
        }



        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];


            }
        }
        return dp[m-1][n-1];

    }


    /**
     一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }

        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];

        for (int i = 0;i < m;i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;

                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1];

                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j];

                }else {
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];

                }
            }
        }
        return dp[m-1][n-1];
    }


    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
     * 返回满足题意的二叉搜索树的种数。
     * f(i)为以i为根的二叉搜索树的个数,G(n)表示总共二叉树的个数
     * G(n) = f(1)+f(2)+...+f(n)   (1)
     *
     * 当i为根结点时，左子树节点个数为[1,2,3,..,i-1],右子树节点个数为[i+1,i+2,...n]
     * 所以当i为根结点时，它的左子树个数为i-1个，右子树节点个数为n-i个
     * f(i)=G(i-1)*G(n-i)    (2)
     * 由(1)和(2)可得 G(n) = G(0)*G(n-1)+G(1)*G(n-2)+...+G(n-1)*G(0)
     *
     */
    public static int numTrees(int n) {

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2;i<=n;i++){
            for (int j=1;j<=i;j++){

                dp[i] = dp[i]+dp[j-1]*dp[i-j];

            }
        }
        return dp[n];
    }


    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<>();
        return generateTreesHelper(1,n);


    }

    //辅助方法
    private List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> res = new LinkedList<>();

        if (start > end) {
            res.add(null);
            return res;
        }


        for (int i=start;i<=end;i++){

            List<TreeNode> subLeft = generateTreesHelper(start,i-1);

            List<TreeNode> subRight = generateTreesHelper(i+1,end);

            for (TreeNode left:subLeft){

                for (TreeNode right:subRight){

                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;

                    res.add(node);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        int n =4;

        System.out.println(numTrees(4));

    }

}
