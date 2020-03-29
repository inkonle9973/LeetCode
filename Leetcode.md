## Leetcode
### Days
- [ ] 3.27 卡牌

最大公约数概念：辗转相除法 

```Java
public class Cards1 {  
  public static boolean hasGroupsSizeX(int[] deck) {  
  int[] count = new int[10000];  
 for (int i :  
                deck) {  
  count[i]++;  
  }  
  int g = -1;  
 for (int i : count) {  
  if (i > 0) {  
  	if (g == -1) {  
  		g = i;  
 	 }  
 	 g = gcd(g, i);  
  
  }  
 }  return g >= 2;  
  }  
  
  
    
  public static int gcd(int a, int b) {  
  return b == 0 ? a : gcd(b, a % b);  
  }  
  
  public static void main(String[] args) {  
  int[] a = new int[]{1, 1, 2, 2, 3,3 };  
  System.out.println(hasGroupsSizeX(a));  
  }  
}
```

- [ ] 3.28 单词的压缩编码   [力扣(LeetCode)]( https://leetcode-cn.com/problems/short-encoding-of-words)
> 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
那么成功对给定单词列表进行编码的最小字符串长度是多少呢？

```Java
输入: words = ["time", "me", "bell"]
输出: 10
说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
```


> 方法一： 利于set集合，首先将所有单纯存储到集合中，并去重。然后检查每个单词的后缀是否也在集合当中，将存在的后缀移除，如此剩下都是不是其他单词后缀的单词，最后的结果就是这些单词长度加一的中和（因为#）

```
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> good = new HashSet(Arrays.asList(words));
        for (String word: words) {
            for (int k = 1; k < word.length(); ++k)
                good.remove(word.substring(k));
        }

        int ans = 0;
        for (String word: good)
            ans += word.length() + 1;
        return ans;
    }
}



```
> 方法二：利用字典树TrieTree，将单词反序依次插入字典树中，每个叶子节点所代表的单词长度加一的和就是我们想要的答案

```
class Solution {
    public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; --j)
                cur = cur.get(word.charAt(j));
            nodes.put(cur, i);
        }

        int ans = 0;
        for (TrieNode node: nodes.keySet()) {
            if (node.count == 0)
                ans += words[nodes.get(node)].length() + 1;
        }
        return ans;

    }
}

class TrieNode {
    TrieNode[] children;
    int count;
    TrieNode() {
        children = new TrieNode[26];
        count = 0;
    }
    public TrieNode get(char c) {
        if (children[c - 'a'] == null) {
            children[c - 'a'] = new TrieNode();
            count++;
        }
        return children[c - 'a'];
    }
}



```

