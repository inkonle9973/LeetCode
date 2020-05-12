从头更新吧，之前单纯的复制题目感觉没有作用，对题目还是应该多思考一些

##  2020/4/25

#### 46. 全排列

给定一个 没有重复 数字的序列，返回其所有可能的全排列。
示例:

```
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

此题应用回溯 算法求解，对其进行深度优先遍历。对于回溯算法应该弄明白三点：

* **怎么前进（进入下一状态）**

* **怎么回退（回到上一个状态）**

* **什么时候停止**

其中比较重要的是：采取何种方式处理好状态的前进和回退。在本题中，在前进时，需要查看当前数字是否已经在组合的路径中，也就是要回避非法状态；在回退时，要将当前数字从组合的路径中删除，以达到回溯、不影响进入到下一个状态的目的。

Java 里面都是**值传递**，对于列表对象来说，复制的是 `path` 这个变量的地址，所以是值传递。也就是说add的时候确实是满着的，不过递归结束就变空了，整个过程都用的一份path。

##  2020/4/26

#### 23.[合并K个排序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

合并 *k* 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
示例:

```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

此题采用分治算法 类似于归并排序 将合并整个链表数组 变为合成两个链表数组 再一直划分知道变为合成两个链表

合成两个链表方法：

**1.** 每次改变的就是每个的next只需将每个时刻最小的作为头结点再依次串起来就可以。采用递归

```
list1[0]+merge(list1[1:],list2)  list1[0]<list2[0]

list2[0]+merge(list1,list2[1:])  otherwise
```

* 时间复杂度：O(n+m)。 因为每次调用递归都会去掉 l1 或者 l2 的头元素（直到至少有一个链表为空），函数 mergeTwoList 中只会遍历每个元素一次。所以，时间复杂度与合并后的链表长度为线性关系。

* 空间复杂度：O(n+m)。调用 mergeTwoLists 退出时 l1 和 l2 中每个元素都一定已经被遍历过了，所以 n+m个栈帧会消耗 O(n+m)的空间



```
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
```



**2.** 专门创建一个head结点里面不存val 只是个虚拟的头结点，然后类似于归并 两个指针指向两个要合并的链表，然后将val值较小的依次连接到head上

* 时间复杂度：O(n+m) 。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， while 循环的次数等于两个链表的总长度。所有其他工作都是常数级别的，所以总的时间复杂度是线性的。

* 空间复杂度：O(1) 。迭代的过程只会产生几个指针，所以它所需要的空间是常数级别的。

```
private ListNode merge2Lists2(ListNode l, ListNode r) {
        ListNode prehead = new ListNode(0);
        ListNode prev = prehead;  
//这个太妙了 对Java的数据类型使用的淋漓尽致 Java都是值传递 传递的是此时的地         址，操作的都同一个链表。实现了c++里的指针作用 但是Java不是指针 只是地址
        while (l != null && r != null) {
            if (l.val <= r.val) {
                prev.next = l;
                l=l.next;
                prev = prev.next;
            } else {	·
                prev.next = r;
                r = r.next;
                prev = prev.next;

            }
        }
        prev.next = l != null ? l : r;
        return prehead.next;

    }
```



**list==null和length==0的区别** 

*1.list==null,意味着list压根没有地址，在堆内就不存在。*

*2.list.size( )= 0,意思是堆内有list,但是还没有放元素，其长度随着元素数量变化而变化，暂时为零。*

*3.list如果为null的话，说明没有进行初始化。这是list调用任何的方法都会抛出空异常。list.size( )==0说明list已经被new过，但      是里面没有值。*

*4.判断时一定要注意先后顺序，如果连杯子（list）都没有，直接判断是否有水（list.size( )）,是会报NullPointerException异常的。*

##  2020/4/27

#### [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。
**示例1:**

```
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```

**示例2:**

```
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```



此题Sweetiee提供了两种思路，三种实现方法

**思路一：「旋转数组中找目标值」 转化成了 「有序数组中找目标值」**

* 首先找到数组中的最小值相当于分割点，以此可以分成两个有序数组，然后进行二分查找

* 对于旋转数组 nums = [4,5,6,7,0,1,2]
  首先根据 nums[0] 与 target 的关系判断 target 是在左段还是右段。

      例如 target = 5, 目标值在左半段，因此在 [4, 5, 6, 7, inf, inf, inf] 这个有序数组里找就行了；
      例如 target = 1, 目标值在右半段，因此在 [-inf, -inf, -inf, -inf, 0, 1, 2] 这个有序数组里找就行了。

  如此，我们又双叒叕将「旋转数组中找目标值」 转化成了 「有序数组中找目标值」

**思路二：直接对旋转数组进行二分查找**

* 首先复习一下有序数组的二分查找：

  ``` java
  public int search(int[] nums, int target) {
      int lo = 0, hi = nums.length - 1, mid = 0;
      while (lo <= hi) {
          mid = lo + ((hi - lo) >> 1);
          if (nums[mid] == target) {
              return mid;
          }
          if (nums[mid] < target) {
              lo = mid + 1;
          } else {
              hi = mid - 1;
          }
      }
      return -1;
  }
  ```

  如上述代码所示，我们根据 nums[mid] 与 target 的大小关系，可以得知 target 是在 mid 的左边还是右边，从而来调整左右边界 lo 和 hi。

  但是，对于旋转数组，我们无法直接根据 nums[mid] 与 target 的大小关系来判断 target 是在 mid 的左边还是右边，因此需要「分段讨论」。于是方法三呼之欲出！

  **方法三：先根据 nums[mid] 与 nums[lo] 的关系判断 mid 是在左段还是右段，接下来再判断 target 是在 mid 的左边还是右边，从而来调整左右边界 lo 和 hi。**

  

#### [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

。
**示例1:**

```java
输入: [3,4,5,1,2]
输出: 1
```

**示例2:**

```java
输入: [4,5,6,7,0,1,2]
输出: 0
```

这题看似很简单 但是感觉边界问题值得考虑 应该仔细分析一下

引用题解 [二分查找：为什么左右不对称？只比较mid与right的原因](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-cha-zhao-wei-shi-yao-zuo-you-bu-dui-cheng-z/)



#### [154. 寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

注意数组中可能存在重复的元素。

示例 1：

```java
输入: [1,3,5]
输出: 1
```


示例 2：

```java
输入: [2,2,2,0,1]
输出: 0
```

##  2020/4/28

#### [56 - I. 数组中数字出现的次数](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/)

一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 ```java
示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
 ```

``` java
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
```

由于此题要求时间复杂度为O(n)，并且空间复杂度为O(1)，所以不能使用排序，而且不能开辟新的辅助数组，只能使用有限的变量。

引用题解 [力扣官方题解](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/)

题解采用位运算 

    如果除了一个数字以外，其他数字都出现了两次，那么如何找到出现一次的数字？

答案很简单：全员进行异或操作即可。考虑异或操作的性质：对于两个操作数的每一位，相同结果为 000，不同结果为 111。那么在计算过程中，成对出现的数字的所有位会两两抵消为 000，最终得到的结果就是那个出现了一次的数字。

那么这一方法如何扩展到找出两个出现一次的数字呢？

如果我们可以把所有数字分成两组，使得：

    两个只出现一次的数字在不同的组中；
    
    相同的数字会被分到相同的组中。

那么对两个组分别进行异或操作，即可得到答案的两个数字。**这是解决这个问题的关键。**



##  2020/4/29

#### [1095. 山脉数组中查找目标值](https://leetcode-cn.com/problems/find-in-mountain-array/)

这是一个 交互式问题 ）

给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。

如果不存在这样的下标 index，就请返回 -1。

 

何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：

首先，A.length >= 3

其次，在 0 < i < A.length - 1 条件下，存在 i 使得：

    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]

 



















你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：

    MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
    MountainArray.length() - 会返回该数组的长度

 



















注意：

对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。

为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。

```java
示例 1：

输入：array = [1,2,3,4,5,3,1], target = 3
输出：2
解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
    
示例 2：

输入：array = [0,1,2,4,2,1], target = 3
输出：-1
解释：3 在数组中没有出现，返回 -1。


```

此题也是标准的二分查找题。

##  2020/4/30

#### [202. 快乐数](https://leetcode-cn.com/problems/happy-number/)

编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。

如果 n 是快乐数就返回 True ；不是，则返回 False 。

**示例：**

 ```java
输入：19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 ```

> 遇到链表找环的问题，就可以采用双指针的思路，而且一些找循环也可以使用。
>
> 此题不要使用哈希集合取巧，开辟过多的空间，甚至可能超出空间额度。

##  2020/5/1

#### [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

**示例：**

```java 
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

> 归并排序在链表中的使用

##  2020/5/2

#### [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

**示例 1:**

```java
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```java
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 3:**

```java
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```



> 此题采用滑动窗口的思想，用哈希map来存取当前字符串，可见不一定非要安照字符串来使用数据结构比如数组，或者队列，做题时只需完成相应功能即可，思路要更加开阔。



## 2020/5/3

#### [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

```java
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

进阶:
如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
```

滑动窗口，此题很有意思，我做的时候想的是下一个数是否会对maxsum产生贡献，从而无法实现连续，而题解是目前的sum对以后是否会产生增量，从而得出了结果。



## 2020/5/4

#### [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/)

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

**示例:**

```java
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```

有点类似于层序遍历，每次找到能跳到最远的位置，然后遍历此位置到能跳的最远位置中的每个位置，看在哪个位置能跳的最远更新为下一跳的位置。

## 2020/5/7

#### [572. 另一个树的子树](https://leetcode-cn.com/problems/subtree-of-another-tree/)

给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。



**示例:**

```java
给定的树 s:

     3
    / \
   4   5
  / \
 1   2

给定的树 t：

   4 
  / \
 1   2

返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
```

给定的树 s：

       3
      / \
     4   5
      / \
     1   2
        /
       0
    
    给定的树 t：
    
       4
      / \
     1   2
    
    返回 false。

这个题上限很高，目前时间不多，不能花大量时间怼算法，等到以后总结一下，对于此题采用递归的思想，

**判断t是否是s的子树**

* t是否为s左子树的子树
* t是否为s右子树的子树
* t和s是否是同一课树

**判断是否为同一科树**

* s的value与t的value是否相等

* s的左子树与t的左子树是否是同一颗树

* s的右子树与t的右子树是否是同一颗树

  

## 2020/5/8

#### [221. 最大正方形](https://leetcode-cn.com/problems/maximal-square/)

在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。



**示例:**

```java
输入: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
```

这是一个动态规划的题，动态规划作为一个大类很值得研究，此题是一个dp数组为二维数组的动态规划

dp模板：

* 确定状态
* 确定dp函数定义
* 确定选择并择优：对于每个状态做出什么选择，改变当前状态（假设dp[0~i-1]已知，推出dp[i])
* 最后确定baseline



## 2020/5/9

#### [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx/)

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。



**示例:**

```java
输入: 4
输出: 2

    
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。
```

二分查找，此次不同的是特意定义了一个变量存储结果，而不是用left；

提交答案时涉及到了类型问题，因为两个int相乘可能会超出int范围，所以需对其进行（long）转型。

## 2020/5/10

#### [236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

![img](README.assets/binarytree.png)



**示例:**

```java
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```

递归思想，只要两个节点在一个节点的左子树和右子树中分别存在，就找到了公共祖先

## 2020/5/11

#### [50. Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)

实现 [pow(*x*, *n*)](https://www.cplusplus.com/reference/valarray/pow/) ，即计算 x 的 n 次幂函数。

**示例:**

```java
输入: 2.00000, 10
输出: 1024.00000

输入: 2.10000, 3
输出: 9.26100

输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
```

int类型的长度范围是-2147483648——2147483647，不要轻易对一个int类型取负，因为若是-2147483648取负会出问题，取负后仍是-2147483648，可以定义long类型来存取-n。

## 2020/5/12

#### [155. 最小栈](https://leetcode-cn.com/problems/min-stack/)

设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

* push(x) —— 将元素 x 推入栈中。
* pop() —— 删除栈顶的元素。
* top() —— 获取栈顶元素。
* getMin() —— 检索栈中的最小元素。



**示例:**

```java
输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```

三种方法：

* 辅助栈（可以选择同步的，也可选择不同步的 只有当datastack弹出的元素和minstack栈顶元素相等才弹出
* 使用一个node（pair）作为存入栈中的元素
* 采用链表自己定义一个栈

