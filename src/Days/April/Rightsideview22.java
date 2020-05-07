package Days.April;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Rightsideview22 {
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {

        if (root == null) {
            return res;
        }
        DFS(root,0);
        return res;

    }

    public void DFS(TreeNode node , int depth) {
        if (node == null) {
            return;
        }

        if (depth == res.size()) {
            res.add(node.val);
        }
        depth++;
        DFS(node.right, depth);
        DFS(node.left, depth);
    }
    public void BFS(TreeNode root, List<Integer> res) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode key = queue.poll();
                if (key.left != null) {
                    queue.add(key.left);
                }
                if (key.right != null) {
                    queue.add(key.right);
                }
                if (i == size - 1) {
                    res.add(key.val);
                }
            }
        }

    }
}
