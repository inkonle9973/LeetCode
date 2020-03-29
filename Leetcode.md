## Leetcode
### Days

----



#### 3.27 卡牌

最大公约数概念：辗转相除法  

> 最大公约数算法(gcd)
```
public  int gcd(int a, int b) {  
  return b == 0 ? a : gcd(b, a % b);  
  }  
```
>引出最小公倍数算法(lcm)
```
public  int lcm(int a, int b){
  return a*b/gcd(a,b);
}
```
>本题的整体求解
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


​    
  public static int gcd(int a, int b) {  
  return b == 0 ? a : gcd(b, a % b);  
  }  

  public static void main(String[] args) {  
  int[] a = new int[]{1, 1, 2, 2, 3,3 };  
  System.out.println(hasGroupsSizeX(a));  
  }  
}
```

#### 3.28 单词的压缩编码  

 [力扣(LeetCode)]( https://leetcode-cn.com/problems/short-encoding-of-words) 

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

#### 3.29 地图分析

[力扣(Leetcode)](https://leetcode-cn.com/problems/as-far-from-land-as-possible/)

> 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
>
> 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
>
> 如果我们的地图上只有陆地或者海洋，请返回 -1
>

```
输入：[[1,0,1],[0,0,0],[1,0,1]]
输出：2
解释： 
海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
```

>题解：这道题可以用BFS来求解。首先将多有陆地入队，然后从各个陆地同时开始一层一层向海洋扩散，那么最后扩散到的海洋就是最远的海洋，并且这个海洋一定是被最近的陆地扩散到的。


```
class Solution {

    public int maxDistance(int[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        // 先把所有的陆地都入队。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean hasOcean = false;
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0], y = point[1];
            // 取出队列的元素，将其四周的海洋入队。
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1; // 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                hasOcean = true;
                queue.offer(new int[] {newX, newY});
            }
        }

        // 没有陆地或者没有海洋，返回-1。
        if (point == null || !hasOcean) {
            return -1;
        }

        // 返回最后一次遍历到的海洋的距离。
        return grid[point[0]][point[1]] - 1;

    }
}

引用：
作者：sweetiee
链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/jian-dan-java-miao-dong-tu-de-bfs-by-sweetiee/
```

