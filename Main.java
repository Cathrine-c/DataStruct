package InterviewImportant.Arithmiy.DFS;

import java.util.*;


class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}


public class Main {

      /*
    有三个盒子，还有三个小球，要把每个小球放入三个袋子中，有多少种方法
     每个盒子的处理逻辑：尝试处理手里的每一张牌，拿一张牌放入盒子，再去处理下一个盒子
     */
    public static void DFS(int[] box, int[] used, int idx) {

        //盒子全部处理完毕
        if (idx == box.length + 1) {
            //打印所有方案
            for (int i=1;i<=box.length;i++){

                System.out.println(box[i]);
            }

            return;
        }

        //处理当前盒子
        for (int i = 1; i <= box.length; i++) {

            //判断当前的牌是否用过
            if (used[i] == 0) {
                //当前没有使用的牌放入当前盒子
                box[idx] = i;

                //保存当前方案
              //  solution[i-1] = i;
                //用过了就标记为1
                used[i] = 1;

                //处理下一个盒子
                DFS(box, used, idx + 1);

                //回收当前使用的牌
                used[i]  =0;

            }
        }

    }


    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] box = new int[n+1];
        int[] used = new int[n+1];

        for (int i=1;i<=n;i++){
            box[i] = sc.nextInt();
            used[i] = sc.nextInt();

        }

        DFS(box,used,1);

    }


    /**
     * 员工的重要性：给定一个保存员工的信息的数据结构，它包含了员工唯一的id，重要度和直系下属的id
     *
     *
     */
    static class Employee{

        int id;
        int importance ;
        List<Integer> subordinates;

    }


    public static int DFS(Map<Integer,Employee> info, int id){

        //获取当前员工的重要度
        int curImportance = info.get(id).importance;

        for (int subId: info.get(id).subordinates){

            //累加当前员工和直接下属的值
            curImportance+=DFS(info,subId);

        }

        return curImportance;

    }


    public static int getImportance(List<Employee> employees,int id){

        Map<Integer, Employee> info = new HashMap<>();

        for (Employee employee:employees){
            info.put(employee.id,employee);

        }

        return DFS(info,id);

    }


    public static void main2(String[] args) {
        Employee employee1 = new Employee();
        employee1.id = 1;
        employee1.importance = 5;
        employee1.subordinates.add(2);
        employee1.subordinates.add(3);

        Employee employee2 = new Employee();
        employee2.id = 2;
        employee2.importance = 3;

        Employee employee3 = new Employee();
        employee3.id = 3;
        employee3.importance = 3;

        List<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);

        System.out.println(getImportance(list, 1));

    }


    /**
     * 有一幅以二维整数组表示的土图画，每一个整数表示该图画的像素值的大小，数值在0-65535之间
     * 给你一个坐标（sr，sc）表示图像渲染开始的像素值（行，列）和一个新的颜色值让你重新上色这幅图像
     *
     * （1）每个点进行染色
     * （2）然后上下左右搜索新的点
     * （3）判断新的点是否符合要求
     * （3）处理新的点
     */

    //存储上下左右四个方向坐标
    static int[][] nextDir={{-1,0},{1,0},{0,-1},{0,1}};
    public static void DFS(int[][] image,int row,int col,int[][] visited,int newx,int newy,int oldC,int newC){

        //1.染色
        image[newx][newy] = newC;
        //已经访问过的方格标记为1
        visited[newx][newy] = 1;

        //2.搜索：上下左右
        for (int i=0;i<4;i++){

            int nextx = newx+nextDir[i][0];
            int nexty = newy+nextDir[i][1];

            //3.判断位置有没有越界
            if (nextx < 0 || nextx >= row || nexty < 0 || nexty >= col) {
                continue;
            }

            //颜色如果符合要求，并且没有被搜索过
            if (image[nextx][nexty] == oldC && visited[nextx][nexty] == 0) {
                //继续往下搜索
                DFS(image,row,col,visited,nextx,nexty,oldC,newC);

            }

        }

    }

    public static int[][] floodFill(int[][] image,int sr,int sc,int newColor){

        int row = image.length;
        int col = image[0].length;

        int[][] visited = new int[row][col];
        int oldC = image[sr][sc];
        DFS(image,row,col,visited,sr,sc,oldC,newColor);
        return image;

    }




    /**
     * 给定一个二维矩阵，包含'X'和'0'，找到所有被x围绕的区域，并将这些区域里所有的’0‘用’X‘填充
     * 解题思路：我们可以反着来想，找到不被包围的0，然后和它相连的0必然也是不被包围的
     * 给不被包围的0进行特殊标记，把被包围的0改成X
     * 然后还原特殊标记的
     */
    //存储上下左右四个方向坐标
    static int[][] nextP={{-1,0},{1,0},{0,-1},{0,1}};

    public static void DFS(char[][] board,int row,int col,int x,int y){

        //1.标记不被包围的点
        board[x][y] = '*';

        //2.以新的位置上下左右进行搜索
        for (int i=0;i<4;i++){
            int nx = x+nextP[i][0];
            int ny = y+nextP[i][1];

            //判断是否越界
            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }

            //如果该位置是不被包围的o，那就给它进行特殊标记，然后对他进行上下左右搜索
            if(board[nx][ny] =='o'){
                DFS(board,row,col,nx,ny);

            }
        }
    }

    public static void solve(char[][] board){
        int row = board.length;
        if (row == 0) {
            return;
        }

        int col = board[0].length;

        for (int i=0;i<col;i++){
            //第一行
            if (board[0][i]=='o'){
                DFS(board,row,col,0,i);
            }


            //最后一行
            if (board[row-1][i]=='o'){
                DFS(board,row,col,row-1,i);
            }
        }


        for (int i=1;i<row-1;i++){
            //第一列
            if (board[i][0]=='o'){
                DFS(board,row,col,i,0);

            }

            //最后一列
            if (board[i][col-1]=='o'){
                DFS(board,row,col,i,col-1);

            }

        }

        //替换恢复
        for (int i=0;i<row;i++){

            for (int j=0;j<col;j++){

                if (board[i][j]=='o'){
                    board[i][j]='X';
                }else if (board[i][j]=='*'){
                    board[i][j] = 'o';
                }

            }

        }
    }


    /**
     * 岛屿数量
     * 给一个由’1‘（陆地）和’0‘（水域）组成的二维网格。计算网格中岛屿的数量
     * 只能上下左右走,只能走陆地
     *
     */

    static int[][] nextD = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void DFS(char[][] grid,int row,int col,boolean[][] visited,int x,int y){
        //1.标记
        visited[x][y] = true;

        //2.搜索
        for (int i=0;i<4;i++){
            int nx = x+nextD[i][0];
            int ny = y+nextD[i][1];

            if (nx<0||nx>=row||ny<0||ny>=col){
                continue;
            }

            if (grid[nx][ny]=='1'&&!visited[nx][ny]){

                DFS(grid,row,col,visited,nx,ny);
            }

        }
    }


    public static int numIslands(char[][] grid){
        int step = 0;
        int row = grid.length;
        if (row == 0) {
            return 0;

        }

        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){

                //为陆地且没有访问过
                if (grid[i][j]==1&&!visited[i][j]){

                    step++;
                    DFS(grid,row,col,visited,i,j);

                }
            }
        }
        return step;
    }


    //宽度优先遍历
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        List<List<Integer>> mat = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList<>();

            while (size-- != 0) {
                Node cur = queue.poll();
                list.add(cur.val);

                //孩子节点入队
                for (Node ch:cur.children){
                    queue.offer(ch);

                }
            }


            if (!list.isEmpty()) {
                mat.add(list);

            }

        }

        return mat;

    }

}
