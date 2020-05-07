package Days.May;

public class _3_53_Maximum_Subarray {

        public int maxSubArray(int[] nums) {
           int maxsum = Integer.MIN_VALUE;
           int sum = 0;
            for (int num : nums) {
                if (sum > 0) {
                    sum += num;
                } else {
                    sum = num;
                }
                maxsum = Math.max(maxsum, sum);

            }
            return maxsum;
        }

}
