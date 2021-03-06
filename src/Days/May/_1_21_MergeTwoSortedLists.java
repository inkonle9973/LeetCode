package Days.May;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class _1_21_MergeTwoSortedLists{
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode pre = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next=l1;
                l1 = l1.next;
                pre=pre.next;

            }else{
            pre.next =l2;
            l2 = l2.next;
            pre=pre.next;
            }
        }
        pre.next = l1!=null?l1:l2;
        return prehead.next;

    }
}
