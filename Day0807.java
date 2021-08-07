package InterviewImportant.jianzhi;

import java.util.Scanner;

public class Day0807 {





    public static int getMaximumResource (int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int resource = 0;

        boolean[][] visited = new boolean[m][n];

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){

                if (grid[i][j]!=0){

                    dfs(grid,i,j,resource,visited);

                }
            }
        }
        return max;
    }


    static int max;
    private static void dfs(int[][] grid, int i, int j, int resource,boolean[][] visited) {

        if (i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0||visited[i][j]){


        }else {


            resource+=grid[i][j];

            visited[i][j] = true;

            dfs(grid,i-1,j,resource,visited);
            dfs(grid,i+1,j,resource,visited);
            dfs(grid,i,j-1,resource,visited);
            dfs(grid,i,j+1,resource,visited);
            max = max>resource?max:resource;
        }

    }


    public static void main(String[] args) {
        int[][] a = {{0,6,0},{5,8,7},{0,9,0}};
        System.out.println(getMaximumResource(a));


    }



}
