package Days.April;

import java.util.Arrays;

public class merge16 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int index =-1;
        int[][] res = new int[intervals.length][2];
        for (int[] interval: intervals){
            if (index == -1 ||interval[0]>res[index][1]) {
                res[++index] = interval;
            } else {
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }//不能直接return 因为 不是一模一样的长度 比如有5个区间
        // 合并后只有4个区间，那么res[4]就还没有被赋值，也就是全零 要抛弃这部分才是答案
        return Arrays.copyOf(res,index+1);
    }
}
