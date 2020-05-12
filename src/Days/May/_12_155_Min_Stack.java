package Days.May;

import java.util.Stack;

public class _12_155_Min_Stack {

}
/*********************/
class MinStack {
    Stack<Integer> datastack ;
    Stack<Integer> minstack ;

    /** initialize your data structure here. */
    public MinStack() {
        datastack = new Stack<>();
        minstack = new Stack<>();
    }

    public void push(int x) {
        datastack.push(x);
        if (!minstack.isEmpty()) {
            if (x < minstack.peek()) {
                minstack.push(x);
            } else {
                minstack.push(minstack.peek());
            }
        }else{
            minstack.push(x);
        }
    }

    public void pop() {
        datastack.pop();
        minstack.pop();
    }

    public int top() {
        return datastack.peek();
    }

    public int getMin() {
        return minstack.peek();
    }
}