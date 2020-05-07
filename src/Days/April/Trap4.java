package Days.April;

import java.util.Stack;

public class Trap4 {
    public static int trap(int[] height) {
        if (height == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans =0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();
                while (!stack.isEmpty() && height [stack.peek()]== height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }

            }
            stack.add(i);
            }
        return ans;
        }



    public static void main(String[] args) {
        int[] num = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(num));
    }
}
