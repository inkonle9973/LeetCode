package fighting;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    static int count =0;
    public int expectNumber(int[] scores) {
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < scores.length; i++) {
            res.add(scores[i]);
        }
        return res.size();
    }

    public static int minimalSteps(String[] maze) {
        char[][] map = new char[maze.length][maze[0].length()];
        for (int i = 0; i < maze.length; i++) {

            map[i] =maze[i].toCharArray();

        }


        distance(map,0,0,'O');
        return 0;



    }


    static class node {

        char val;
        int i;
        int j;

        public node(char val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }
    }

    public static int distance(char[][] map, int i, int j, char shot) {
        int step = 0;
        int row = map.length;
        int column = map[0].length;
        Queue<node> queue = new LinkedList<>();
        queue.offer(new node(map[i][j], i, j));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                node tmp = queue.poll();
                if (tmp.val == shot) {
                    return step;
                } else {
                    if (tmp.i + 1 < row && tmp.i + 1 >= 0 && tmp.j >= 0 && tmp.j < column && map[tmp.i + 1][tmp.j] != '#')

                        queue.offer(new node(map[tmp.i + 1][tmp.j], tmp.i + 1, tmp.j));

                    if (tmp.i - 1 < row &&tmp. i - 1 >= 0 && tmp.j >= 0 && tmp.j < column && map[tmp.i - 1][tmp.j] != '#')
                        queue.offer(new node(map[tmp.i - 1][tmp.j], tmp.i - 1, tmp.j));

                    if (tmp.i < row && tmp.i >= 0 && tmp.j + 1 >= 0 && tmp.j + 1 < column && map[tmp.i][tmp.j + 1] != '#')
                        queue.offer(new node(map[tmp.i][tmp.j + 1], tmp.i, tmp.j + 1));

                    if (tmp.i < row && tmp.i >= 0 && tmp.j - 1 >= 0 && tmp.j - 1 < column && map[tmp.i][tmp.j - 1] != '#')
                        queue.offer(new node(map[tmp.i][tmp.j - 1], tmp.i, tmp.j - 1));
                }

            }
            step++;
        }
        return -1;
    }

    public static int splitArray(int[] nums) {

        int len = nums.length;
        find(nums, 0, len-1, len);
        return count;
    }

    public static void find(int[] nums, int head, int last, int len) {
        while (last>head&&GCD(nums[head], nums[last])==1) {
            last--;
        }
        if(last==len-1) {
            count++;
            return ;} ;
        head = last+1;
        last = len-1;
        count++;
        find(nums, head, last, len);


    }
    public static int GCD(int a,int b) {
        if(b==0)
            return a;
        else
            return GCD(b,a%b);
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2};
        System.out.println(splitArray(nums));
    }
}


