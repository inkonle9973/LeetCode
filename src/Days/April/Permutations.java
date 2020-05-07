package Days.April;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len<1)return  res;
        List<Integer> path = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        dfs(nums,len,0,used,path,res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, Set<Integer> used, List<Integer> path, List<List<Integer>> res) {

        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used.contains(nums[i])) {
                path.add(nums[i]);
                used.add(nums[i]);
                dfs(nums, len, depth+1,used, path, res);
                path.remove(path.size() - 1);
                used.remove(nums[i]);
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations solution = new Permutations();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }


}
