package Days.April;

public class SingleNumbers28 {
    public static int[] singleNumbers(int[] nums) {
        int div = 0;
        for(int n:nums){
            div^=n;
        }
        int ret = 1;
        while((div&ret)==0){
            ret<<=1;
        }
        int a =0; int b = 0;
        for(int n:nums){
            if((ret & n)>0) a^= n;
            else b^=n;
        }
        int[] res = new int[2];
        res[0]=a;res[1]=b;
        return res;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 4, 4, 5};
        int[] res = singleNumbers(nums);
        for (int n :
                res) {
            System.out.println(n);
        }
    }
}
