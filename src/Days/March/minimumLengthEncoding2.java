package Days.March;

import java.util.HashMap;
import java.util.Map;

public class minimumLengthEncoding2 {
    public static int minlength(String[] words) {
        TrieTree trie = new TrieTree();
        Map<TrieTree,Integer> nodes=new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieTree cur = trie;
            for (int j = word.length() -1 ; j >0 ; j--) {
                cur = cur.get(word.charAt(j));
            }
            nodes.put(cur, i);
        }
        int ans = 0;
        for (TrieTree node :
                nodes.keySet()) {
            if (node.count == 0) {
                ans += words[nodes.get(node)].length() + 1;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"time", "me", "bell"};
        System.out.println(minlength(words));
    }

}

class TrieTree {
    TrieTree[] children;
    int count;

    public TrieTree() {
        children = new TrieTree[26];
        count = 0;
    }

    public TrieTree get(char c) {
        if (children[c - 'a'] == null) {
            children[c -'a'] = new TrieTree();
            count ++;
        }
        return children[c - 'a'];
    }
}