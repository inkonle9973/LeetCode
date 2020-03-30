## Leetcode
### Days

----



#### 3.27 卡牌

 [力扣(LeetCode)]( https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/) 

> 题目：给定一副牌，每张牌上都写着一个整数。
>
> 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
>
> - 每组都有 X 张牌。
>
> - 组内所有的牌上都写着相同的整数。
>
> 仅当你可选的 `X >= 2` 时返回 `true`。
>
 ```
输入：[1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 ```

> 题解：利用最大公约数算法（辗转相除法）。  

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

#### 3.30 圆圈中最后剩下的数字

[力扣(Leetcode)](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

> 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
>
> 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

```
输入: n = 5, m = 3
输出: 3
```

```
输入: n = 10, m = 17
输出: 2
```

> 题解：本次题解引用来自  [sweetieeyi](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/)
>
> 方法一：采用**`ArrayList`**  
>

```
class Solution {
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }
}
```

> 方法二: 本题是著名的约瑟夫环问题

因为数据是放在数组里，所以我在数组后面加上了数组的复制，以体现是环状的。我们先忽略图片里的箭头：

![image.png](Leetcode.assets/9dda886441be8d249abb76e35f53f29fd6e780718d4aca2ee3c78f947fb76e75-image-1585548407812.png)

很明显我们每次删除的是第 mmm 个数字，我都标红了。

第一轮是 [0, 1, 2, 3, 4] ，所以是 [0, 1, 2, 3, 4] 这个数组的多个复制。这一轮 2 删除了。

第二轮开始时，从 3 开始，所以是 [3, 4, 0, 1] 这个数组的多个复制。这一轮 0 删除了。

第三轮开始时，从 1 开始，所以是 [1, 3, 4] 这个数组的多个复制。这一轮 4 删除了。

第四轮开始时，还是从 1 开始，所以是 [1, 3] 这个数组的多个复制。这一轮 1 删除了。

最后剩下的数字是 3。

图中的绿色的线指的是新的一轮的开头是怎么指定的，每次都是固定地向前移位 mmm 个位置。

然后我们从最后剩下的 3 倒着看，我们可以反向推出这个数字在之前每个轮次的位置。

最后剩下的 3 的下标是 0。

第四轮反推，补上 mmm 个位置，然后模上当时的数组大小 222，位置是(0 + 3) % 2 = 1。

第三轮反推，补上 mmm 个位置，然后模上当时的数组大小 333，位置是(1 + 3) % 3 = 1。

第二轮反推，补上 mmm 个位置，然后模上当时的数组大小 444，位置是(1 + 3) % 4 = 0。

第一轮反推，补上 mmm 个位置，然后模上当时的数组大小 555，位置是(0 + 3) % 5 = 3。

所以最终剩下的数字的下标就是3。因为数组是从0开始的，所以最终的答案就是3。

总结一下反推的过程，就是 (当前index + m) % 上一轮剩余数字的个数。

代码就很简单了。

```
class Solution {
    public int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}

```



