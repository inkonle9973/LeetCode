package Days.April;



public class MergerSortedList26 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        int left = 0;
        int right = lists.length - 1;
        return mergeLists(lists, left, right);
    }

    private ListNode mergeLists(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        int mid = left + (right - left) / 2;
        ListNode l = mergeLists(lists, left, mid);
        ListNode r = mergeLists(lists, mid + 1, right);
        return merge2Lists(l, r);
    }

    private ListNode merge2Lists(ListNode l, ListNode r) {
        if (l == null) {
            return r;
        } else if (r == null) {
            return  l;
        }
        if (l.val <= r.val) {
            l.next = merge2Lists(l.next, r);
            return l;
        } else {
            r.next = merge2Lists(l, r.next);
            return r;
        }
    }
    private ListNode merge2Lists2(ListNode l, ListNode r) {
        ListNode prehead = new ListNode(0);
        ListNode prev = prehead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                prev.next = l;
                l=l.next;
                prev = prev.next;
            } else {
                prev.next = r;
                r = r.next;
                prev = prev.next;

            }
        }
        prev.next = l != null ? l : r;
        return prehead.next;

    }

}
