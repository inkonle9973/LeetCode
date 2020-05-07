package Days.May;

public class _7_572_SubtreeofAnotherTree {

    class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }


    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return  true;
        }
        if (s == null) {
            return false;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSametree(s, t);
    }

    public boolean isSametree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && isSametree(s.left, t.left) && isSametree(s.right, t.right);
    }



}
