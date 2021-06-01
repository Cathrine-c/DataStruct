package InterviewImportant.Shuati;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    //机器人从r行c列的网格左上角走到右下角，可以走的网格为0，有障碍物为1，返回一条可行的路径
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid.length == 0) {
            return res;
        }

        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(0);
        res.add(ans);


        if (dfs(0,0,obstacleGrid)) return res;
        else return new ArrayList<>();
    }

    //判断是否走得通
    private boolean dfs(int x, int y, int[][] obstacleGrid) {
        //如果越界，直接返回false
        if (x== obstacleGrid.length||y==obstacleGrid[0].length||obstacleGrid[x][y]==1){
            return false;
        }

        //如果已经到达终点，直接返回true
        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            return true;
        }


        List<Integer> list = new ArrayList<>();
        list.add(x+1);
        list.add(y);
        res.add(list);
        //如果向下走，走得通把当前点下一个添加进去
        if (dfs(x+1,y,obstacleGrid))return true;
        //如果走不通，把当前点删除
        res.remove(res.size()-1);

        list.add(x);
        list.add(y+1);
        res.add(list);
        if (dfs(x,y+1,obstacleGrid))return true;
        res.remove(res.size()-1);

        //所有路都走不通，置为1
        obstacleGrid[x][y]=1;

        return false;
    }




    public static void main(String[] args) {

    }
}
