package Days.April;

import java.util.Stack;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1stack = new Stack<>();
        Stack<Integer> l2stack = new Stack<>();
        ListNode head = null;
        while (l1 != null) {
            l1stack.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2stack.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!l1stack.isEmpty() || !l2stack.isEmpty() || carry > 0) {
            int sum = carry;
            int a = l1stack.isEmpty() ? 0 : l1stack.pop();
            int b = l2stack.isEmpty() ? 0 : l2stack.pop();
            sum += a + b;
            carry = sum / 10;
            sum %= 10;
            //这一段很关键
            ListNode ans = new ListNode(sum);
            ans.next=head;
            head = ans;
        }
        return head;

    }
}