package gan;

import java.util.*;

public class numWays {
    public  int count = 0;
    public  Map<Integer, Set<Integer>> graph = new HashMap<>();
    public  Stack<Integer> trace = new Stack<>();

    public  void addEdge(int v, int w) {

        if (graph.containsKey(v)) graph.get(v).add(w);
        else {
            Set<Integer> k = new HashSet<>();
            k.add(w);
            graph.put(v, k);
        }

    }

    public  int numWays(int n, int[][] relation, int k) {
        for (int i = 0; i < relation.length; i++) {
            addEdge(relation[i][0], relation[i][1]);
        }

        int depth = 1;
        trace.push(0);
        findtrace(0, depth, n - 1, k);

        return count;
    }

    public  void findtrace(int key, int depth, int aim, int k) {
        if (depth > k) {
            trace.pop();
            return;
        }
        Set<Integer> pass = graph.get(key);
        if (pass == null) {
            return;
        }
        for (int passobject :
                pass) {
            if (passobject == aim) {
                if (depth == k) count++;

            } else {
                trace.add(passobject);
                findtrace(passobject, depth + 1, aim, k);
            }
        }
        trace.pop();

    }


}
