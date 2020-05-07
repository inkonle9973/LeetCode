package Days.April;

public class HappyNumber30 {
    public static boolean isHappy(int n) {
        int fast=sqlsum(sqlsum(n));
        int low =sqlsum(n);
        while (low != fast) {
            fast=sqlsum(sqlsum(fast));
            low = sqlsum(low);
        }
        return  fast==1;
    }

    public static int sqlsum(int n) {
        int sum = 0;
        while (n > 0) {
            sum+= (n%10)*(n%10);
            n = n/10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}
