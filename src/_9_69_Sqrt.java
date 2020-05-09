public class _9_69_Sqrt {
    public int mySqrt(int x) {
        int ans = -1,l = 0,r=x,
                mid=0;
        long square=0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            square=(long)mid*mid;
            if (square <= x) {
                ans = mid;
                l = mid+1;
            } else  {
                r = mid -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}

