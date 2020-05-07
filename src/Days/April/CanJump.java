package Days.April;

import java.util.Arrays;

public class CanJump {
    public static boolean canJump(int[] nums) {
        int[][] distance =new int[nums.length-1][2];
        for (int i = 0; i < nums.length - 1; i++) {

            distance[i][0] =i ;
            distance[i][1] =i+nums[i] ;

        }
        Arrays.sort(distance, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[distance.length][2];
        int index = -1;
        for (int[] dis : distance) {
            if (index == -1 || dis[0] > res[index][1]) {
                res[++index] = dis;
            } else {
                res[index][1]=Math.max(dis[1],res[index][1]);
            }
        }
        return res[0][1]>= nums.length-1;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[] {3,2,1,0,4}));
    }
}
