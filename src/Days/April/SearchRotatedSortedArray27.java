package Days.April;

public class SearchRotatedSortedArray27 {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid=0;
        int divide = nums[0];
        while (left <= right) {
            mid = left + (right-left)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= divide) {
                if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left=mid +1;
                }
            } else {
                if (nums[mid] < target && target < nums[right]) {
                    left = mid + 1;

                } else {
                    right = mid -1;
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{ 4,5,6,7,0,1,2};
        System.out.println(search(nums,5));
    }
}
