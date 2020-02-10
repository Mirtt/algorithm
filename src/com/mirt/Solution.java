package com.mirt;

import java.util.*;

/**
 * Lintcode
 *
 * @author Mirt
 * @date 2018/3/21.
 */
public class Solution {

    /**
     * Given a matrix of size n x m, the elements in the matrix are 0、1、2.
     * 0 for the sea, 1 for the island, and 2 for the city on the island(You can assume that 2 is built on 1, ie 2 also represents the island).
     * If two 1 are adjacent, then these two 1 belong to the same island. Find the number of islands with at least one city.
     *
     * @param grid: an integer matrix
     * @return: an integer
     */
    public int numIslandCities(int[][] grid) {
        // Write your code here
        return 0;
    }

    /**
     * The words are same rotate words if rotate the word to the right by loop, and get another.
     * Count how many different rotate word sets in dictionary.
     *
     * @param words: A list of words
     * @return: Return how many different rotate words
     */
    public int countRotateWords(List<String> words) {
        // Write your code here
        Set<String> dict = new HashSet<String>();
        //一个字符串w的rotation，先将w变成w+w，然后求新字符串的substring。
        //这样不需要考虑前后拼接问题
        for (String w : words) {
            String s = w + w;
            for (int i = 0; i < w.length(); i++) {
                dict.remove(s.substring(i, i + w.length()));
            }

            dict.add(w);
        }

        return dict.size();
    }

    /**
     * 写一个算法来判断一个数是不是"快乐数"。
     * <p>
     * 一个数是不是快乐是这么定义的：对于一个正整数，每一次将该数替换为他每个位置上的数字的平方和，然后重复这个过程直到这个数变为1，
     * 或是无限循环但始终变不到1。如果可以变为1，那么这个数就是快乐数。
     *
     * @param n: An integer
     * @return: true if this is a happy number or false
     */
    public boolean isHappy(int n) {
        // write your code here
        if (n == 1)
            return true;
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(n);
        while (n != 1) {
            n = getSquare(n);
            if (integerSet.contains(n))
                break;
            integerSet.add(n);
        }
        return n == 1;
    }

    private int getSquare(int n) {
        String num = String.valueOf(n);
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            sum += Integer.valueOf(num.substring(i, i + 1)) * Integer.valueOf(num.substring(i, i + 1));
        }
        return sum;
    }

    /**
     * ReverseWords.reverseWords("The greatest victory is that which requires no battle");
     * should return "battle no requires which that is victory greatest The"
     *
     * @param str
     * @return
     */
    public static String reverseWords(String str) {
        //write your code here...
        if (str == null || str.length() < 1)
            return str;
        StringBuffer reverseWords = new StringBuffer();
        Stack<Character> word = new Stack<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ' && !word.empty()) {
                int len = word.size();
                for (int j = 0; j < len; j++) {
                    reverseWords.append(word.pop());
                }
                if (reverseWords.length() > 0) {
                    reverseWords.append(' ');
                }
                continue;
            }
            word.add(str.charAt(i));
        }
        int len = word.size();
        for (int i = 0; i < len; i++) {
            reverseWords.append(word.pop());
        }
        return reverseWords.toString();
    }

    public String compress(String str) {
        // write your code here
        if (str == null || str.length() < 2) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        List<Character> charsets = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        charsets.add(str.charAt(0));
        count.add(1);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == charsets.get(charsets.size() - 1)) {
                count.set(count.size() - 1, count.get(count.size() - 1) + 1);
            } else {
                charsets.add(str.charAt(i));
                count.add(1);
            }
        }
        for (int i = 0; i < count.size(); i++) {
            sb.append(charsets.get(i));
            sb.append(count.get(i));
        }
        return str.length() > sb.length() ? sb.toString() : str;
    }

    /**
     * 给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)
     * <p>
     * 对于字符串 "abcdefg".
     * <p>
     * offset=0 => "abcdefg"
     * offset=1 => "gabcdef"
     * offset=2 => "fgabcde"
     * offset=3 => "efgabcd"
     *
     * @param str
     * @param offset
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        if (str.length < 1)
            return;
        offset = offset % str.length;
        int index = 0;
        for (int i = str.length - offset; i < str.length; i++) {
            int k = i;
            while (k > index) {
                swap(str, k, --k);
            }
            index++;
        }
    }

    private char[] swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
        return str;
    }

    /**
     * 你站在一个无穷数轴上的 0 位置。在位置目标上有一个目标。
     * 在每一个动作中，你可以向左或向右。在第n次移动中(从1开始)，你行走n步。
     * 返回到达目的地所需的最小步骤数。
     *
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        // Write your code here
        if (target == 0)
            return 0;
        if (target < 0)
            target = -target;
        int n = 1;
        while (n * (n + 1) < 2 * target || (n * (n + 1) / 2 - target) % 2 != 0) {
            n++;
        }
        return n;
    }

    /**
     * 设计一种方法，将一个字符串中的所有空格替换成 %20 。你可以假设该字符串有足够的空间来加入新的字符，且你得到的是“真实的”字符长度。
     * <p>
     * 你的程序还需要返回被替换后的字符串的长度。
     * <p>
     * 样例
     * 对于字符串"Mr John Smith", 长度为 13
     * <p>
     * 替换空格之后，参数中的字符串需要变为"Mr%20John%20Smith"，并且把新长度 17 作为结果返回。
     *
     * @param string
     * @param length
     * @return
     */
    public int replaceBlank(char[] string, int length) {
        // write your code here
        if (string == null || length < 1)
            return 0;
        List<Character> clone = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            clone.add(string[i]);
            if (string[i] == ' ') {
                clone.remove(clone.size() - 1);
                clone.add('%');
                clone.add('2');
                clone.add('0');
            }
        }
        string = new char[clone.size()];
        for (int i = 0; i < clone.size(); i++) {
            string[i] = clone.get(i);
        }
        return clone.size();
    }

    /**
     * 给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一。
     * <p>
     * <p>
     * <p>
     * 样例
     * 给出数组[1,1,1,1,2,2,2]，返回 1
     * <p>
     * 挑战
     * 要求时间复杂度为O(n)，空间复杂度为O(1)
     *
     * @param nums
     * @return
     */
    public int majorityNumber(List<Integer> nums) {
        // write your code here
        Collections.sort(nums);
        return nums.get(nums.size() / 2);
    }

    /**
     * 给定一个单词序列，检查它是否构成一个有效单词广场。
     * 一个有效的单词广场满足：如果第k行和第k列读取相同的字符串,并且0≤k<max(numRows numColumns)。
     * <p>
     * 样例
     * 给定
     * [
     * "abcd",
     * "bnrt",
     * "crmy",
     * "dtye"
     * ]
     * 返回 true
     * <p>
     * 解释:
     * 第一行和第一列都是“abcd”。
     * 第二行和第二列都是“bnrt”。
     * 第三行和第三列都是“crmy”。
     * 第四行和第四列都是“dtye”。
     * <p>
     * 因此，这是一个有效的单词广场.
     *
     * @param words
     * @return
     */
    public boolean validWordSquare(String[] words) {
        // Write your code here
        for (int i = 1; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                if (words[j].charAt(i) != words[i].charAt(j))
                    return false;
            }
        }
        return true;
    }

    /**
     * 给定一个模式和一个字符串str，查找str是否遵循相同的模式。
     * 这里遵循的意思是一个完整的匹配，在一个字母的模式和一个非空的单词str之间有一个双向连接的模式对应。
     * <p>
     * 样例
     * 给定模式= "abba"， str = "dog cat cat dog"，返回true。给定模式= "abba"， str = "dog cat cat fish"，返回false。
     * 给定模式= "aaaa"， str = "dog cat cat dog"，返回false。给定模式= "abba"， str = "dog dog dog dog"，返回false。
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        // write your code here
        Map<Character, String> map = new HashMap<>();
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length)
            return false;
        map.put(pattern.charAt(0), strs[0]);
        for (int i = 1; i < pattern.length(); i++) {
            Character k = pattern.charAt(i);
            if (map.get(k) == null) {
                if (map.containsValue(strs[i]))
                    return false;
                map.put(k, strs[i]);
            } else {
                String s = map.get(k);
                if (!s.equals(strs[i]))
                    return false;
            }
        }
        return true;
    }

    /**
     * 输出所有长度为n的排列
     * <p>
     * 例： 输入 abc,3
     * 输出 abc,acb,bac,bca,cab,cba
     *
     * @param characters
     * @return
     */
    public List<List<Character>> combination(List<Character> characters, int n) {
        if (characters.size() < n)
            return new ArrayList<>(0);
        List<List<Character>> result = dfs4c(new ArrayList<>(), new ArrayList<>(), characters, n);

        return result;
    }

    private List<List<Character>> dfs4c(List<List<Character>> result, List<Character> target, List<Character> characters, int maxDepth) {
        if (target.size() == maxDepth) {
            result.add(new ArrayList<>(target));
            return result;
        }
        for (int i = 0; i < characters.size(); i++) {
            if (!target.contains(characters.get(i))) {
                target.add(characters.get(i));
                dfs4c(result, target, characters, maxDepth);
                target.remove(target.size() - 1);
            }
        }
        return result;
    }

    /**
     * 830. 字符串排序
     * 给出一个字符串，以最常用的字符为第一关键字，字典顺序为第二关键字排序字符串。
     * <p>
     * 样例
     * 给出 str = “bloomberg” , 返回 “bbooeglmr”。
     * <p>
     * 解释：
     * 'b'出现次数最多，且字典序最小，排名第一，其次是'o'。
     *
     * @param str: the string that needs to be sorted
     * @return: sorted string
     */
    public String stringSort(String str) {
        // Write your code here
        if (str == null || str.length() < 1)
            return str;
        int[] words = new int[26];
        for (int i = 0; i < str.length(); i++) {
            words[str.charAt(i) - 'a'] += 1;
        }
        int n = str.length();
        char[] result = new char[str.length()];
        int index = 0;
        while (n > 0) {
            for (char i = 0; i < words.length; i++) {
                if (words[i] == n) {
                    for (int j = 0; j < n; j++) {
                        result[index] = (char) ('a' + i);
                        index++;
                    }
                }
            }
            n--;
        }
        return new String(result);
    }


    /**
     * 684. 缺少的字符串
     * 给出两个字符串，你需要找到缺少的字符串
     * <p>
     * 样例
     * 给一个字符串 str1 = This is an example, 给出另一个字符串 str2 = is example
     * 返回 ["This", "an"]
     *
     * @param str1: a given string
     * @param str2: another given string
     * @return: An array of missing string
     */
    public List<String> missingString(String str1, String str2) {
        // Write your code here
        List<String> result = new ArrayList<>();
        if (str1 == null || str2 == null)
            return new ArrayList<>(0);
        String[] strings1 = str1.split(" ");
        String[] strings2 = str2.split(" ");
        for (int i = 0; i < strings1.length; i++) {
            boolean isMiss = true;
            for (int j = 0; j < strings2.length; j++) {
                if (strings1[i].equals(strings2[j])) {
                    isMiss = false;
                    break;
                }
            }
            if (isMiss)
                result.add(strings1[i]);
        }
        return result;
    }

    /**
     * 868. 子数组的最大平均值
     * 给定一个由n个整数组成的数组，找到给定长度k的连续子数组，该子数组具有最大平均值。你需要输出最大平均值。
     * <p>
     * 样例
     * 给定nums = [1,12,-5,-6,50,3]， k = 4，返回12.75
     * <p>
     * 解释：
     * 最大平均为(12-5-6+50)/4 = 51/4 = 12.75。
     *
     * @param nums: an array
     * @param k:    an integer
     * @return: the maximum average value
     */
    public double findMaxAverage(int[] nums, int k) {
        // Write your code here
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double max = sum;
        int preIndex = 0;
        int sufIndex = k;
        while (sufIndex < nums.length) {
            sum += (nums[sufIndex] - nums[preIndex]);
            if (max < sum)
                max = sum;
            preIndex++;
            sufIndex++;
        }
        return max / k;
    }

    /**
     * 1401. 抽搐词
     * 我们正常的单词不会有连续两个以上相同的字母，如果出现连续三个或以上的字母，那么这是一个抽搐词。
     * 现在给一个单词，从左至右求出所有抽搐字母的起始点和结束点。
     * <p>
     * 样例
     * 给出 str = "whaaaaatttsup", 返回 [[2,6],[7,9]]。
     * <p>
     * 解释：
     * "aaaa"和"ttt"是抽搐字母，输出他们的起始点和结束点。
     *
     * @param str: the origin string
     * @return: the start and end of every twitch words
     */
    public int[][] twitchWords(String str) {
        // Write your code here
        int start = 0;
        int end = start;
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                end++;
            } else {
                if (end - start > 1)
                    result.add(new Integer[]{start, end});
                end++;
                start = end;
            }
        }
        if (end - start > 1)
            result.add(new Integer[]{start, end});
        int[][] rs = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            rs[i][0] = result.get(i)[0];
            rs[i][1] = result.get(i)[1];
        }
        return rs;
    }

    /**
     * 914. 翻转游戏
     * 翻转游戏：给定一个只包含两种字符的字符串：+和-，你和你的小伙伴轮流翻转"++"变成"--"。
     * 当一个人无法采取行动时游戏结束，另一个人将是赢家。
     * <p>
     * 编写一个函数，计算字符串在一次有效移动后的所有可能状态。
     * <p>
     * 样例
     * 给定 s = "++++", 在一次有效移动后，它会变成下列状态之一：
     * <p>
     * [
     * "--++",
     * "+--+",
     * "++--"
     * ]
     * 如果无法移动，则返回一个空列表[].
     *
     * @param s: the given string
     * @return: all the possible states of the string after one valid move
     */
    public List<String> generatePossibleNextMoves(String s) {
        // write your code here
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String pre = s.substring(0, i);
            String suf = s.substring(i);
            int t = suf.indexOf("++");
            if (t >= 0) {
                i = t + pre.length();
                result.add(pre + suf.replaceFirst("\\+\\+", "--"));
            }
        }
        return result;
    }

    /**
     * 365. 二进制中有多少个1
     * 计算在一个 32 位的整数的二进制表示中有多少个 1.
     * <p>
     * 样例
     * 给定 32 (100000)，返回 1
     * <p>
     * 给定 5 (101)，返回 2
     * <p>
     * 给定 1023 (1111111111)，返回 10
     * <p>
     * 挑战
     * If the integer is n bits with m 1 bits. Can you do it in O(m) time?
     *
     * @param num: An integer
     * @return: An integer
     */
    public int countOnes(int num) {
        // write your code here
        boolean negative = false;
        if (num < 0) {
            num = -num - 1;
            negative = true;
        }
        int c = 0;
        while (num != 0) {
            int t = num & 1;
            if (t == 1) {
                c++;
            }
            num >>= 1;
        }
        return negative ? 32 - c : c;
    }

    /**
     * 159. 寻找旋转排序数组中的最小值
     * 假设一个旋转排序的数组其起始位置是未知的（比如0 1 2 4 5 6 7 可能变成是4 5 6 7 0 1 2）。
     * <p>
     * 你需要找到其中最小的元素。
     * <p>
     * 你可以假设数组中不存在重复的元素。
     * <p>
     * 样例
     * 给出[4,5,6,7,0,1,2]  返回 0
     *
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    // 可以使用二分查找！！！！
    public int findMin(int[] nums) {
        // write your code here
        if (nums == null || nums.length < 1)
            return -1;
        int l = nums.length;
        if (nums[0] < nums[l - 1])
            return nums[0];
        int start = 0, end = l - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return nums[start] < nums[end] ? nums[start] : nums[end];
    }

    /**
     * 73. 前序遍历和中序遍历树构造二叉树
     * 根据前序遍历和中序遍历树构造二叉树.
     * <p>
     * 样例
     * 给出中序遍历：[1,2,3]和前序遍历：[2,1,3]. 返回如下的树:
     * <p>
     * 2
     * / \
     * 1   3
     *
     * @param preorder: A list of integers that postorder traversal of a tree
     * @param inorder:  A list of integers that inorder traversal of a tree
     * @return: Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
        if (preorder == null || preorder.length < 1)
            return null;
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode treeNode = new TreeNode(preorder[0]);
        int t = 0; // remember split
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0])
                t = i;
        }
        int[] leftInorder = new int[t];
        int[] rightInorder = new int[inorder.length - t - 1];
        int[] leftPreorder = new int[t];
        int[] rigthPreorder = new int[preorder.length - t - 1];
        for (int i = 0; i < t; i++) {
            leftInorder[i] = inorder[i];
            leftPreorder[i] = preorder[i + 1];
        }
        for (int i = 0; i < inorder.length - t - 1; i++) {
            rightInorder[i] = inorder[t + i + 1];
            rigthPreorder[i] = preorder[t + i + 1];
        }
        treeNode.left = buildTree(leftPreorder, leftInorder);
        treeNode.right = buildTree(rigthPreorder, rightInorder);
        return treeNode;
    }

    /**
     * 38. 搜索二维矩阵 II
     * 写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。
     * <p>
     * 这个矩阵具有以下特性：
     * <p>
     * 每行中的整数从左到右是排序的。
     * 每一列的整数从上到下是排序的。
     * 在每一行或每一列中没有重复的整数。
     * 样例
     * 考虑下列矩阵：
     * <p>
     * [
     * <p>
     * [1, 3, 5, 7],
     * <p>
     * [2, 4, 7, 8],
     * <p>
     * [3, 5, 9, 10]
     * <p>
     * ]
     * <p>
     * 给出target = 3，返回 2
     * <p>
     * 挑战
     * 要求O(m+n) 时间复杂度和O(1) 额外空间
     *
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix.length < 1 || matrix[0].length < 1)
            return 0;
        int c = matrix.length - 1;
        int r = matrix[0].length;
        int m = c;
        int n = 0;
        int count = 0;
        while (m >= 0 && n < r) {
            if (matrix[m][n] == target) {
                m--;
                n++;
                count++;
            } else if (matrix[m][n] > target) {
                m--;
            } else {
                n++;
            }
        }
        return count;
    }

    /**
     * 373. 奇偶分割数组
     * 分割一个整数数组，使得奇数在前偶数在后。
     * <p>
     * 样例
     * 给定 [1, 2, 3, 4]，返回 [1, 3, 2, 4]。
     * <p>
     * 挑战
     * 在原数组中完成，不使用额外空间。
     *
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        // write your code here
        if (nums.length < 2)
            return;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 0) {
                if (nums[j] % 2 == 0) {
                    j--;
                    continue;
                }
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                j--;
            } else {
                i++;
            }
        }
    }

    /**
     * 174. 删除链表中倒数第n个节点
     * 给定一个链表，删除链表中倒数第n个节点，返回链表的头节点。
     * <p>
     * <p>
     * <p>
     * 样例
     * 给出链表1->2->3->4->5->null和 n = 2.
     * <p>
     * 删除倒数第二个节点之后，这个链表将变成1->2->3->5->null.
     * <p>
     * 挑战
     * O(n)时间复杂度
     *
     * @param head: The first node of linked list.
     * @param n:    An integer
     * @return: The head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null)
            return head;
        ListNode p = head;
        while (n > 0) {
            p = p.next;
            n--;
            if (p == null && n != 0)
                return head;
        }
        ListNode q = head;
        while (p.next != null) {
            q = q.next;
            p = p.next;
        }
        q.next = q.next.next;
        return head;
    }

    /**
     * 245. 子树
     * 有两个不同大小的二叉树： T1 有上百万的节点； T2 有好几百的节点。请设计一种算法，判定 T2 是否为 T1的子树。
     * <p>
     * 样例
     * 下面的例子中 T2 是 T1 的子树：
     * <p>
     * 1                3
     * / \              /
     * T1 = 2   3      T2 =  4
     * /
     * 4
     * 下面的例子中 T2 不是 T1 的子树：
     * <p>
     * 1               3
     * / \               \
     * T1 = 2   3       T2 =    4
     * /
     * 4
     *
     * @param T1: The roots of binary tree T1.
     * @param T2: The roots of binary tree T2.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        if (T1 == null && T2 != null) {
            return false;
        }
        String preOrderT1 = preOrderTreeStr(T1).toString();
        String preOrderT2 = preOrderTreeStr(T2).toString();
        String inOrderT1 = inOrderTreeStr(T1).toString();
        String inOrderT2 = inOrderTreeStr(T2).toString();

        return preOrderT1.contains(preOrderT2) && inOrderT1.contains(inOrderT2);
    }

    private StringBuilder preOrderTreeStr(TreeNode t) {
        if (t == null) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        sb.append(preOrderTreeStr(t.left));
        sb.append(preOrderTreeStr(t.right));
        return sb;
    }

    private StringBuilder inOrderTreeStr(TreeNode t) {
        if (t == null) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(inOrderTreeStr(t.left));
        sb.append(t.val);
        sb.append(inOrderTreeStr(t.right));
        return sb;
    }

    /**
     * 371. 用递归打印数字
     * 用递归的方法找到从1到最大的N位整数。
     * <p>
     * 样例
     * 给出 N = 1, 返回[1,2,3,4,5,6,7,8,9].
     * <p>
     * 给出 N = 2, 返回[1,2,3,4,5,6,7,8,9,10,11,...,99].
     * <p>
     * 挑战
     * 用递归完成，而非循环的方式。
     *
     * @param n: An integer
     * @return: An array storing 1 to the largest number with n digits.
     */
    public List<Integer> numbersByRecursion(int n) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        num(n, 0, result);
        return result;
    }

    private void num(int n, int now, List<Integer> res) {
        if (n == 0) {
            if (now > 0)
                res.add(now);
            return;
        }
        for (int i = 0; i < 10; i++) {
            num(n - 1, 10 * now + i, res);
        }
    }

    /**
     * 140. 快速幂
     * 计算a^n % b，其中a，b和n都是32位的整数。
     * <p>
     * 样例
     * 例如 2^31 % 3 = 2
     * <p>
     * 例如 100^1000 % 1000 = 0
     * <p>
     * 挑战
     * O(logn)
     *
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        long ans = 1, tmp = a;

        while (n != 0) {
            if (n % 2 == 1) {
                ans = (ans * tmp) % b;
            }
            tmp = (tmp * tmp) % b;
            n = n / 2;
        }

        return (int) ans % b;
    }

    /**
     * 372. Delete Node in a Linked List
     * 给定一个单链表中的一个等待被删除的节点(非表头或表尾)。请在在O(1)时间复杂度删除该链表节点。
     * <p>
     * 样例
     * Linked list is 1->2->3->4, and given node 3, delete the node in place 1->2->4
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        // write your code here
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 376. 二叉树的路径和
     * 给定一个二叉树，找出所有路径中各节点相加总和等于给定 目标值 的路径。
     * <p>
     * 一个有效的路径，指的是从根节点到叶节点的路径。
     * <p>
     * 样例
     * 给定一个二叉树，和 目标值 = 5:
     * <p>
     * 1
     * / \
     * 2   4
     * / \
     * 2   3
     * 返回：
     * <p>
     * [
     * [1, 2, 2],
     * [1, 4]
     * ]
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        if (root == null)
            return new ArrayList<>();
        return dfsSum(root, new ArrayList<>(), new ArrayList<>(), target);
    }

    private List<List<Integer>> dfsSum(TreeNode t, List<List<Integer>> rs, List<Integer> data, int target) {
        if (t == null) {
            return rs;
        }
        if (t.left == null && t.right == null) {
            if (target - t.val == 0) {
                data.add(t.val);
                rs.add(new ArrayList<>(data));
                data.remove(data.size() - 1);
            }
            return rs;
        }
        data.add(t.val);
        dfsSum(t.left, rs, data, target - t.val);
        dfsSum(t.right, rs, data, target - t.val);
        data.remove(data.size() - 1);
        return rs;
    }

    /**
     * 375. 克隆二叉树
     * 深度复制一个二叉树。
     * <p>
     * 给定一个二叉树，返回一个他的 克隆品 。
     * <p>
     * 样例
     * 给定一个二叉树：
     * <p>
     * 1
     * /  \
     * 2    3
     * / \
     * 4   5
     * 返回其相同结构相同数值的克隆二叉树：
     * <p>
     * 1
     * /  \
     * 2    3
     * / \
     * 4   5
     *
     * @param root: The root of binary tree
     * @return: root of new tree
     */
    public TreeNode cloneTree(TreeNode root) {
        // write your code here
        if (root == null) {
            return null;
        }
        TreeNode clone = new TreeNode(root.val);
        clone.left = deepCloneTree(clone.left, root.left);
        clone.right = deepCloneTree(clone.right, root.right);
        return clone;
    }

    private TreeNode deepCloneTree(TreeNode clone, TreeNode treeNode) {
        if (treeNode != null) {
            clone = new TreeNode(treeNode.val);
            clone.left = deepCloneTree(clone.left, treeNode.left);
            clone.right = deepCloneTree(clone.right, treeNode.right);
        }
        return clone;
    }

    /**
     * 68. 二叉树的后序遍历
     * 给出一棵二叉树，返回其节点值的后序遍历。
     * <p>
     * 样例
     * 给出一棵二叉树 {1,#,2,3},
     * <p>
     * 1
     * \
     * 2
     * /
     * 3
     * 返回 [3,2,1]
     * <p>
     * 挑战
     * 你能使用非递归实现么？
     *
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> rs = new ArrayList<>();
        if (root == null)
            return rs;
        postOrderDfs(root, rs);
        return rs;
    }

    private void postOrderDfs(TreeNode root, List<Integer> rs) {
        if (root == null)
            return;
        postOrderDfs(root.left, rs);
        postOrderDfs(root.right, rs);
        rs.add(root.val);
    }

    /**
     * 378. 将二叉查找树转换成双链表
     * 将一个二叉查找树按照中序遍历转换成双向链表。
     * <p>
     * 样例
     * 给定一个二叉查找树：
     * <p>
     * 4
     * / \
     * 2   5
     * / \
     * 1   3
     * 返回 1<->2<->3<->4<->5。
     *
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        // write your code here
        List<Integer> rs = new ArrayList<>();
        inOrderDfs(root, rs);
        if (rs.isEmpty())
            return null;
        if (rs.size() == 1)
            return new DoublyListNode(rs.get(0));
        DoublyListNode node = new DoublyListNode(rs.get(0));
        DoublyListNode index = node;
        DoublyListNode temp = new DoublyListNode(rs.get(1));
        for (int i = 2; i < rs.size(); i++) {
            index.next = temp;
            temp.prev = index;
            index = index.next;
            temp = new DoublyListNode(rs.get(i));
        }
        index.next = temp;
        temp.prev = index;
        return node;
    }

    private void inOrderDfs(TreeNode root, List<Integer> rs) {
        if (root == null)
            return;
        inOrderDfs(root.left, rs);
        rs.add(root.val);
        inOrderDfs(root.right, rs);
    }

    /**
     * 374. 螺旋矩阵
     * 给定一个包含 m x n 个要素的矩阵，（m 行, n 列），按照螺旋顺序，返回该矩阵中的所有要素。
     * <p>
     * 样例
     * 给定如下矩阵：
     * <p>
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 应返回 [1,2,3,6,9,8,7,4,5]。
     *
     * @param matrix: a matrix of m x n elements
     * @return: an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // write your code here
        List<Integer> rs = new ArrayList<>();
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return rs;
        int m = matrix.length; //行数
        int n = matrix[0].length; //列数
        int rowStart = 0;
        int rowEnd = m - 1;
        int colStart = 0;
        int colEnd = n - 1;
        while (m > 0 && n > 0) {
            for (int i = colStart; i <= colEnd; i++) {
                rs.add(matrix[rowStart][i]);
            }
            rowStart++;
            m--;
            if (m == 0)
                break;
            for (int i = rowStart; i <= rowEnd; i++) {
                rs.add(matrix[i][colEnd]);
            }
            colEnd--;
            n--;
            if (n == 0)
                break;
            for (int i = colEnd; i >= colStart; i--) {
                rs.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            m--;
            if (m == 0)
                break;
            for (int i = rowEnd; i >= rowStart; i--) {
                rs.add(matrix[i][colStart]);
            }
            colStart++;
            n--;
        }
        return rs;
    }

    /**
     * 105. 复制带随机指针的链表
     * 给出一个链表，每个节点包含一个额外增加的随机指针可以指向链表中的任何节点或空的节点。
     * <p>
     * 返回一个深拷贝的链表。
     * <p>
     * 挑战
     * 可否使用O(1)的空间
     *
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }

    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        return newHead;
    }

    /**
     * 209. First Unique Character in a String
     * 给出一个字符串，找出第一个只出现一次的字符。
     * <p>
     * 样例
     * 对于 "abaccdeff", 'b'为第一个只出现一次的字符.
     *
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        if (str == null)
            return ' ';
        int[] temp = new int[256];
        for (int i = 0; i < str.length(); i++) {
            temp[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (temp[str.charAt(i)] == 1)
                return str.charAt(i);
        }
        return ' ';
    }

    /**
     * 532. 逆序对
     * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
     * 概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。
     * <p>
     * 样例
     * 序列 [2, 4, 1, 3, 5] 中，有 3 个逆序对 (2, 1), (4, 1), (4, 3)，则返回 3 。
     *
     * @param A: an array
     * @return: total of reverse pairs
     */
    //todo
    public long reversePairs(int[] A) {
        // write your code here
        return 0L;
    }

    /**
     * 379. 将数组重新排序以构造最小值
     * 给定一个整数数组，请将其重新排序，以构造最小值。
     * <p>
     * 样例
     * 给定 [3, 32, 321]，通过将数组重新排序，可构造 6 个可能性数字：
     * <p>
     * 3+32+321=332321
     * 3+321+32=332132
     * 32+3+321=323321
     * 32+321+3=323213
     * 321+3+32=321332
     * 321+32+3=321323
     * 其中，最小值为 321323，所以，将数组重新排序后，该数组变为 [321, 32, 3]。
     * <p>
     * 挑战
     * 在原数组上完成，不使用额外空间。
     *
     * @param nums: n non-negative integer array
     * @return: A string
     */
    public String minNumber(int[] nums) {
        // write your code here
        if (nums.length < 2)
            return nums.length < 1 ? "" : String.valueOf(nums[0]);
        String[] numStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }
        Comparator<String> c = (o1, o2) -> {
            String ab = o1.concat(o2);
            String ba = o2.concat(o1);
            return ba.compareTo(ab);
        };
        Arrays.sort(numStr, c);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (!numStr[i].equals("0")) {
                sb.append(numStr[i]);
            }
            nums[i] = Integer.parseInt(numStr[i]);
        }
        return sb.toString().isEmpty() ? "0" : sb.toString();
    }

    /**
     * 380. 两个链表的交叉
     * 请写一个程序，找到两个单链表最开始的交叉节点。
     * <p>
     * 样例
     * 下列两个链表：
     * <p>
     * A:          a1 → a2
     * ↘
     * c1 → c2 → c3
     * ↗
     * B:     b1 → b2 → b3
     * 在节点 c1 开始交叉。
     * <p>
     * 挑战
     * 需满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     * <p>
     * 注意事项
     * 如果两个链表没有交叉，返回null。
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     *
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // write your code here
        if (headA == null || headB == null)
            return null;
        int aLen = 0;
        int bLen = 0;
        ListNode p = headA;
        ListNode q = headB;
        while (p != null) {
            p = p.next;
            aLen++;
        }
        while (q != null) {
            q = q.next;
            bLen++;
        }
        int dif = aLen > bLen ? aLen - bLen : bLen - aLen;
        p = headA;
        q = headB;
        if (aLen > bLen) {
            while (dif > 0) {
                p = p.next;
                dif--;
            }
        } else {
            while (dif > 0) {
                dif--;
                q = q.next;
            }
        }
        while (p != null) {
            if (p == q)
                return p;
            p = p.next;
            q = q.next;
        }
        return null;
    }

    /**
     * 381. 螺旋矩阵 II
     * 给你一个数n生成一个包含1-n^2的螺旋形矩阵
     * <p>
     * 样例
     * n = 3
     * 矩阵为
     * <p>
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     *
     * @param n: An integer
     * @return: a square matrix
     */
    public int[][] generateMatrix(int n) {
        // write your code here
        if (n < 1)
            return new int[0][0];
        if (n == 1)
            return new int[][]{{1}};
        int[][] result = new int[n][n];
        int row = n - 1;
        int col = n - 1;
        int rIndex = 0;
        int cIndex = 0;
        int num = 1;
        int n2 = n * n;
        while (num < n2 + 1) {
            for (; cIndex <= col; cIndex++) {
                result[rIndex][cIndex] = num++;
            }
            cIndex--;
            rIndex++;
            for (; rIndex <= row; rIndex++) {
                result[rIndex][cIndex] = num++;
            }
            rIndex--;
            cIndex--;
            for (; cIndex >= n - col - 1; cIndex--) {
                result[rIndex][cIndex] = num++;
            }
            rIndex--;
            cIndex++;
            for (; rIndex > n - row - 1; rIndex--) {
                result[rIndex][cIndex] = num++;
            }
            row--;
            col--;
            rIndex++;
            cIndex++;
        }
        return result;
    }

    /**
     * 82. 落单的数
     * 给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
     * <p>
     * <p>
     * <p>
     * 样例
     * 给出 [1,2,2,1,3,4,3]，返回 4
     * <p>
     * 挑战
     * 一次遍历，常数级的额外空间复杂度
     *
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        // write your code here
        int r = 0;
        for (int a : A) {
            r ^= a;
        }
        return r;
    }

    /**
     * 61. 搜索区间
     * 给定一个包含 n 个整数的排序数组，找出给定目标值 target 的起始和结束位置。
     * <p>
     * 如果目标值不在数组中，则返回[-1, -1]
     * <p>
     * 样例
     * 给出[5, 7, 7, 8, 8, 10]和目标值target=8,
     * <p>
     * 返回[3, 4]
     * <p>
     * 挑战
     * 时间复杂度 O(log n)
     *
     * @param A:      an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        int begin = binarySearchFirst(A, target);
        int end = binarySearchLast(A, target);

        return new int[]{begin, end};
    }

    private int binarySearchFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midN = nums[mid];
            if (midN == target) {
                if (mid == start)
                    return start;
                if (nums[mid - 1] != target)
                    return mid;
                end = mid - 1;
            }
            if (midN > target)
                end = mid - 1;
            if (midN < target)
                start = mid + 1;
        }
        return -1;
    }

    private int binarySearchLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int midN = nums[mid];
            if (midN == target) {
                if (mid == end)
                    return start;
                if (nums[mid + 1] != target)
                    return mid;
                start = mid + 1;
            }
            if (midN > target)
                end = mid - 1;
            if (midN < target)
                start = mid + 1;
        }
        return -1;
    }

    /**
     * 88. Lowest Common Ancestor of a Binary Tree
     * 给定一棵二叉树，找到两个节点的最近公共父节点(LCA)。
     * <p>
     * 最近公共祖先是两个节点的公共的祖先节点且具有最大深度。
     * <p>
     * 样例
     * 对于下面这棵二叉树
     * <p>
     * 4
     * / \
     * 3   7
     * / \
     * 5   6
     * LCA(3, 5) = 4
     * <p>
     * LCA(5, 6) = 7
     * <p>
     * LCA(6, 7) = 7
     * <p>
     * 注意事项
     * 假设给出的两个节点都在树中存在
     *
     * @param root: The root of the binary search tree.
     * @param A:    A TreeNode in a Binary.
     * @param B:    A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null) {
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        if (root == A || root == B) {
            return root;
        }
        if (left != null && right != null) {
            return root;
        }

        return right == null ? left : right;
    }

    /**
     * 层次遍历树
     *
     * @param t 需要遍历的树
     * @return 每一层包含在一个数组里，且内层数组中节点的根节点为同一个
     */
    public List<List<Integer>> test(TreeNode t) {
        List<List<Integer>> rs = new ArrayList<>();
        if (t == null)
            return rs;
        Queue<TreeNode> layer = new LinkedList<>();
        List<Integer> data = new ArrayList<>();
        data.add(t.val);
        rs.add(data);
        layer.offer(t);
        while (!layer.isEmpty()) {
            List<Integer> d = new ArrayList<>();
            TreeNode tmp = layer.poll();
            if (tmp.left != null) {
                d.add(tmp.left.val);
                layer.offer(tmp.left);
            }
            if (tmp.right != null) {
                d.add(tmp.right.val);
                layer.offer(tmp.right);
            }
            if (tmp.right != null || tmp.left != null)
                rs.add(d);
        }
        return rs;
    }

    /**
     * 1478. 最接近target的值
     * 给出一个数组，在数组中找到两个数，使得它们的和最接近目标值但不超过目标值，返回它们的和
     * <p>
     * 样例
     * Input:target = 15
     * array = [1,3,5,11,7]
     * Output:14
     * 注意事项
     * 如果没有满足要求的结果就返回 -1.
     *
     * @param target: the target
     * @param array:  an array
     * @return: the closest value
     */
    public int closestTargetValue(int target, int[] array) {
        // Write your code here
        if (array == null || array.length < 2)
            return -1;
        int rs = -1;
        int diff = Integer.MAX_VALUE;
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int t = target - array[i] - array[j];
                if (t >= 0 && t < diff) {
                    rs = array[i] + array[j];
                    diff = target - rs;
                }
            }
        }
        return rs;
    }

    /**
     * 39. 恢复旋转排序数组
     * 给定一个旋转排序数组，在原地恢复其排序。
     * <p>
     * 样例
     * [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
     * <p>
     * 挑战
     * 使用O(1)的额外空间和O(n)时间复杂度
     * <p>
     * 说明
     * 什么是旋转数组？
     * <p>
     * 比如，原始数组为[1,2,3,4], 则其旋转数组可以是[1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
     *
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() < 1)
            return;
        int splitPoint = -1;
        int indexNum = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < indexNum) {
                splitPoint = i;
                break;
            }
        }
        if (splitPoint < 0) {
            return;
        }
        reverseArray(nums, 0, splitPoint - 1);
        reverseArray(nums, splitPoint, nums.size() - 1);
        reverseArray(nums, 0, nums.size() - 1);
    }

    private void reverseArray(List<Integer> nums, int begin, int end) {
        int i = begin;
        int j = end;
        while (i < j) {
            int arr_i = nums.get(i);
            int arr_j = nums.get(j);
            nums.set(i++, arr_j);
            nums.set(j--, arr_i);
        }
    }

    /**
     * 1. A + B 问题
     * 给出两个整数 aa 和 bb , 求他们的和。
     * <p>
     * 样例
     * 如果 a=1 并且 b=2，返回3。
     * <p>
     * 挑战
     * 显然你可以直接 return a + b，但是你是否可以挑战一下不这样做？（不使用++等算数运算符）
     * <p>
     * 说明
     * a和b都是 32位 整数么？
     * <p>
     * 是的
     * 我可以使用位运算符么？
     * <p>
     * 当然可以
     * 注意事项
     * 你不需要从输入流读入数据，只需要根据aplusb的两个参数a和b，计算他们的和并返回就行。
     */
    public int aplusb(int a, int b) {
        // write your code here
        int c = a ^ b;// 不需要进位
        int d = (a & b) << 1;// 需要进位
        return d == 0 ? c : aplusb(c, d);
    }

    /**
     * 147. 水仙花数
     * 水仙花数的定义是，这个数等于他每一位上数的幂次之和 见维基百科的定义
     * <p>
     * 比如一个3位的十进制整数153就是一个水仙花数。因为 153 = 13 + 53 + 33。
     * <p>
     * 而一个4位的十进制数1634也是一个水仙花数，因为 1634 = 14 + 64 + 34 + 44。
     * <p>
     * 给出n，找到所有的n位十进制水仙花数。
     * <p>
     * 样例
     * 比如 n = 1, 所有水仙花数为：[0,1,2,3,4,5,6,7,8,9]。
     * 而对于 n = 2, 则没有2位的水仙花数，返回 []。
     * <p>
     * 注意事项
     * 你可以认为n小于8。
     *
     * @param n: The number of digits
     * @return: All narcissistic numbers with n digits
     */
    public List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> rs = new ArrayList<>();
        if (n <= 0)
            return rs;
        if (n == 1) {
            rs.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
            return rs;
        }
        int start = (int) Math.pow(10, n - 1);
        int end = (int) Math.pow(10, n);
        for (int i = start; i < end; i++) {
            int num = i;
            int sum = 0;
            while (num != 0) {
                int k = num % 10;
                num /= 10;
                sum += Math.pow(k, n);
            }
            if (i == sum) {
                rs.add(i);
            }
        }
        return rs;
    }

    /**
     * 1651. 区间异或 I
     * cat-only-icon
     * CAT 专属题目
     * 给定数组A（下标从0到n-1，n为数组长度），和一个查询列表。每一项查询包括两个整数i和k。
     * 对于每次查询，计算Ai, A(i + 1), ..., A(i+k-1)的异或值。结果保存在列表中。
     * <p>
     * 样例
     * 对于数组[1,2,3,4] 和 查询[(0,2),(1,2)] 返回[3,1].
     * <p>
     * 注意事项
     * 在大部分编程语言中你可以使用 '^'来进行异或运算。
     * 在这个问题中，k永远等于2。
     * 数组长度小于10000，查询次数小于1000。
     * 保证Ai<1000,i+k<n。
     *
     * @param A:
     * @param query:
     * @return: nothing
     */
    public List<Integer> intervalXOR(int[] A, List<Interval> query) {
        List<Integer> rs = new ArrayList<>();
        if (query == null)
            return rs;
        for (Interval i : query) {
            int start = i.start;
            int end = i.start + i.end - 1;
            int sum = 0;
            for (int t = start; t <= end; t++) {
                sum ^= A[t];
            }
            rs.add(sum);
        }
        return rs;
    }

    /**
     * 1776. 梯形的面积
     * <p>
     * 梯形面积的计算公式是(a + b) * h / 2;现在给出a, b, h。返回梯形的面积。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入 : a = 2, b = 4, h = 4
     * 输出 : 12
     * 解析：area = (2 + 4) * 4 / 2 = 12
     * 样例 2:
     * <p>
     * 输入 : a = 4, b = 6, h = 2
     * 输出 : 10
     * 解析：area = (4 + 6) * 2 / 2 = 10
     * 注意事项
     * 面积的数据类型是double
     *
     * @param a:
     * @param b:
     * @param h:
     * @return: Return the area of trapezoid
     */
    public double AreaOfTrapezoid(int a, int b, int h) {
        // Write your code here
        return (a + b) * h / 2.0d;
    }

    /**
     * 16. 带重复元素的排列
     * <p>
     * 给出一个具有重复数字的列表，找出列表所有不同的排列。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入：[1,1]
     * 输出：
     * [
     * [1,1]
     * ]
     * 样例 2：
     * <p>
     * 输入：[1,2,2]
     * 输出：
     * [
     * [1,2,2],
     * [2,1,2],
     * [2,2,1]
     * ]
     * 挑战
     * 使用递归和非递归分别完成该题。
     *
     * @param : A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here

        // 这一步排序的作用 在dfs中 可以保证不出现重复列表
        Arrays.sort(nums);
        List<List<Integer>> list = dfs4N(new ArrayList<>(), new ArrayList<>(), nums, new boolean[nums.length]);

        return new ArrayList<>(list);
    }

    public List<List<Integer>> dfs4N(List<List<Integer>> rs, List<Integer> target, int[] nums, boolean[] visited) {
        if (target.size() == nums.length) {
            rs.add(new ArrayList<>(target));
            return rs;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 由于nums已经排序 所以此时如果 i-1 和 i 的数字一样，并且i-1没有被保存过 说明 在 遍历到i-1 和 i的时候已经出现过这种组合 故此时应该排除
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;

            target.add(nums[i]);
            dfs4N(rs, target, nums, visited);
            target.remove(target.size() - 1);

            visited[i] = false;

        }
        return rs;
    }

    /**
     * 29. 交叉字符串
     * 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入:
     * "aabcc"
     * "dbbca"
     * "aadbbcbcac"
     * 输出:
     * true
     * 样例 2：
     * <p>
     * 输入:
     * ""
     * ""
     * "1"
     * 输出:
     * false
     * 样例 3：
     * <p>
     * 输入:
     * "aabcc"
     * "dbbca"
     * "aadbbbaccc"
     * 输出:
     * false
     * 挑战
     * 要求时间复杂度为O(n2)或者更好
     *
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        // 需要用到动态规划。。这部分不太会 不做了
        return false;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<List<Integer>> r = new Solution().permuteUnique(new int[]{1, 2, 2});
        System.out.println(r);
        System.out.println(r.size());
        System.out.println(System.currentTimeMillis() - start);
    }

    private static TreeNode initTree() {
        TreeNode t1 = new TreeNode(-1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(41);
        t1.left.right = new TreeNode(-4);
        t1.right.left = new TreeNode(-12);
        t1.right.right = new TreeNode(6);
        return t1;
    }

    private static int[][] initMatrix() {
        int k = 1;
        int[][] matrix = new int[4][3];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 4; j++) {
                matrix[i - 1][j - 1] = k++;
            }
        }
        return matrix;
    }
}


class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class DoublyListNode {
    int val;
    DoublyListNode next, prev;

    DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}

class MyQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public MyQueue() {
        // do intialization if necessary
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        inStack.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (!outStack.isEmpty()) {
            return outStack.pop();
        } else {
            Iterator<Integer> iterator = inStack.iterator();
            while (iterator.hasNext()) {
                outStack.push(inStack.pop());
                iterator.next();
            }
            return outStack.pop();
        }

    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        if (!outStack.isEmpty()) {
            return outStack.peek();
        } else {
            Iterator<Integer> iterator = inStack.iterator();
            while (iterator.hasNext()) {
                outStack.push(inStack.pop());
                iterator.next();
            }
            return outStack.peek();
        }
    }
}

/**
 * 12. 带最小值操作的栈
 * 实现一个带有取最小值min方法的栈，min方法将返回当前栈中的最小值。
 * <p>
 * 你实现的栈将支持push，pop 和 min 操作，所有操作要求都在O(1)时间内完成。
 * <p>
 * 样例
 * 如下操作：push(1)，pop()，push(2)，push(3)，min()， push(1)，min() 返回 1，2，1
 */
class MinStack {

    private Stack<Integer> stack;

    private Stack<Integer> minStack;

    public MinStack() {
        // do intialization if necessary
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        minStack.pop();
        return stack.pop();
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
        return minStack.peek();
    }

}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}