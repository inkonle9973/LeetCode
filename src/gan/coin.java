package gan;

public class coin {

        public int minCount(int[] coins) {
           if(coins == null) return 0;
           int res=0;
            for (int i = 0; i < coins.length; i++) {
                res += coins[i]/2+coins[i]%2;
            }
            return res;
        }

}
