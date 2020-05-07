package gan;

public class getTriggerTime {
    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int Days = increase.length;
        int requirecounts = requirements.length;
        int[] res = new int[requirecounts];
        for (int i = 0; i < requirecounts; i++) {
            res[i] = -1;
        }
        int[] ability = new int[]{0, 0, 0};
        for (int j = 0; j < requirecounts; j++) {
            if (ability[0] >= requirements[j][0]
                    && ability[1] >= requirements[j][1]
                    && ability[2] >= requirements[j][2]) {
                res[j]=0;
            }
        }
        for (int i = 1; i <= Days; i++) {
            ability[0] = ability[0] + increase[i-1][0];
            ability[1] = ability[1] + increase[i-1][1];
            ability[2] = ability[2] + increase[i-1][2];

            for (int j = 0; j < requirecounts; j++) {
                if (ability[0] >= requirements[j][0]
                        && ability[1] >= requirements[j][1]
                        && ability[2] >= requirements[j][2]) {
                    if(res[j]==-1) res[j]=i;
                   
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[][] increase = new int[][]{{2, 8, 4}, {2, 5, 0}, {10, 9, 8}};
        int[][] requirements = new int[][]{{2, 11, 3}, {15, 10, 7}, {9, 17, 12}, {8, 1, 14}};
/*        [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]*/
       int[] test = getTriggerTime(increase,requirements);
        for (int i :
                test  ) {
            System.out.println(i);
        }

    }

}
