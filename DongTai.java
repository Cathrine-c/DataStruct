package InterviewImportant.DP;

public class DongTai {


    //给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    public static int minPathSum(int[][] grid) {

        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        for (int i=1;i<row;i++){
            //处理0列上的边界格
            grid[i][0] = grid[i-1][0]+grid[i][0];

        }
        for (int j=1;j<col;j++){
            //处理0行上的边界格
            grid[0][j] = grid[0][j-1]+grid[0][j];

        }

        for (int i=1;i<row;i++){
            for (int j=1;j<col;j++){
                //转移方程
                grid[i][j] = Math.min(grid[i-1][j],grid[i][j-1])+grid[i][j];

            }
        }
        return grid[row-1][col-1];

    }



    /**
     * 背包问题：有n个物品和一个大小为m的背包，给定数组A表示每个物品的大小和数组V表示每个物品的价值
     * 问最多能装入背包的总价值是多大？
     * 转换问题为：从n个商品中做选择，当包的大小为m时的最大价值
     * 状态F(i,j)：从i个商品中做选择，当包的大小为j时的最大价值
     * (1)A[i-1]<=j:  不放：F(i,j)=F(i-1,j)  放：F(i,j)=F(i-1,j-A[i-1])+V[i-1]
     * F(i,j) = Math.max(F(i-1,j),F(i-1,j-A[i-1)+V[i-1])
     *
     * (2)A[i-1]>j: F(i,j)=F(i-1,j)  表明背包放不下
     *
     * 初始状态：F(i,0)=F(0,j)=0
     *
     * 返回F(n,m)
     */

    public static int maxValueBag(int m,int[] A,int[] V){

        int n=A.length;

        if (m == 0 || n == 0) {
            return 0;
        }

        //第0行或者第0列为0
        int[][] maxV = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){

                if (A[i-1]<=j){
                    maxV[i][j] = Math.max(maxV[i-1][j],maxV[i-1][j-A[i-1]]+V[i-1]);

                }else {
                    maxV[i][j] = maxV[i-1][j];

                }
            }
        }
        return maxV[n][m];
    }


    public static void main(String[] args) {
//        int m=4;
//        int[] A={4,2,1,1};
//        int[] V={5,3,2,1};
//        System.out.println(maxValueBag(m, A, V));

        System.out.println(minSeparateNum("dde"));

    }


    /**
     * 给一个字符串，分割s使得分割出的每一个子串都是回文串，计算将字符串分割成回文分割结果的最小切割数
     * 例如，给定字符串s="aab"，可以分割成"aa"、"b",只需要分割一次
     * 问题：字符串s的最小分割次数
     * 状态F(i)：字符串前i个字符的最小分割次数
     * 转移方程：
     * F(i):j<i&&[j+1,i]是回文串
     * min{F(j)+1}     最后取值应该比较当前值和更新后的值哪个小取哪个
     * 如果整体为回文字符串[1,i]:F(i)=0
     * 初始状态：F(i)=i-1;
     * 返回：F(n)
     */


    public static int minSeparateNum(String s){
        //从1开始
        int[] minC = new int[s.length()+1];

        for (int i=1;i<=s.length();i++){
            minC[i] = i-1;

        }

        for (int i=2;i<=s.length();i++){
            if (isPal(s,0,i-1)){
                minC[i] = 0;
                continue;
            }

            for (int j=1;j<i;j++){
                if (isPal(s,j,i-1)){
                    minC[i] = Math.min(minC[i],minC[j]+1);

                }
            }
        }

        return minC[s.length()];
    }


    private static boolean isPal(String s, int start, int end) {

        while (start < end) {
            if (s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }



    /*
    给定两个单词word1和word2，计算word1转换为word2至少需要几步，对单词有以下3种操作：
    a）在单词中插入一个字符
    b）删除单词中的一个字符
    c）替换单词中的一个字符
    问题：word1转成word2的最小操作次数
    **word1的前i个字符转成word2的前j个字符的最小操作次数
    转移方程：
       F(i,j)：选三者最小
        替换：F(i-1,j-1)+(s1[i]==s2[j]?0:1)
        插入：F(i-1,j)+1
        删除：F(i,j-1)+1
       初始状态：F(i,0)--->删除
               F(0,j)--->插入
        返回  F(len1,len2)

     */

    public static int minOperatChar(String s1,String s2){

        int[][] minD = new int[s1.length()+1][s2.length()+1];

        int len1 = s1.length();
        int len2 = s2.length();

        for (int i=0;i<=len1;i++){
            minD[0][i] = i;

        }
        for (int i=0;i<len2;i++){
            minD[i][0] = i;

        }

        for (int i=1;i<len1;i++){
            for (int j=1;j<len2;j++){

                //插入删除
                minD[i][j] = Math.min(minD[i-1][j],minD[i][j-1])+1;

                //如果当前字母相同就不需要替换 也就不需要+1
                if (s1.charAt(i) == s2.charAt(j)) {
                    minD[i][j] = Math.min(minD[i-1][j-1],minD[i][j]);

                }else {
                    //替换
                    minD[i][j] = Math.min(minD[i - 1][j - 1] + 1, minD[i][j]);
                }
            }
        }

        return minD[len1][len2];

    }



}
