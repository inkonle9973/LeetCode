package Days.May;

public class _4_45_JumpGameII {
    public static int jump(int[] nums) {
       int ans = 0;
       int start = 0;
       int end = 1;
        while (end < nums.length) {
            int maxPos =0;
            for (int i = start; i < end; i++) {
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start =end;
            end = maxPos +1;
            ++ans;
        }
        return ans;


    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }
}
