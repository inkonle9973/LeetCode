package Days;

import java.util.Arrays;

public class Cards1 {
    public static boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int i :
                deck) {
            count[i]++;
        }
        int g = -1;
        for (int i :
                count) {
            if (i > 0) {
                if (g == -1) {
                    g = i;
                }
                g = gcd(g, i);

            }
        }
        return g >= 2;
    }


  

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 2, 3,3 };
        System.out.println(hasGroupsSizeX(a));
    }
}
