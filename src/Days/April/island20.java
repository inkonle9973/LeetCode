package Days.April;

import java.util.Stack;

public class island20 {


        public static int numIslands(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int res = 0;
            for(char i = 0; i<m; i++){
                for(char j = 0; j < n;j ++){
                    if(grid[i][j] == 1){
                        res++;
                        infect(grid,m,n,i,j);
                    }

                }
            }
            return res;
        }
        public static void infect (char[][] grid ,int m,int n, char i,char j){
            if(i<0||i>m||j<0||j>n||grid[i][j]!= 1){
                return;
            }
            grid[i][j]++;
            infect(grid,m,n,i++,j);
            infect(grid,m,n,i--,j);
            infect(grid,m,n,i,j++);
            infect(grid,m,n,i,j--);
        }


    public static void main(String[] args) {
        //[["1","1","1","1","0"],
        // ["1","1","0","1","0"],
        // ["1","1","0","0","0"],
        // ["0","0","0","0","0"]]

        char[][] grid = new char[][]{{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
        System.out.println(numIslands(grid));
    }
}
