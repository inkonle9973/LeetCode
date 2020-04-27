从头更新吧，之前单纯的复制题目感觉没有作用，对题目还是应该多思考一些

####  2020/4/25
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

####  2020/4/26

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

####  2020/4/27

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

  


