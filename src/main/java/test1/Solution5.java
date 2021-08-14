package test1;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Solution5 {
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        /*
         * 思路：用双端队列实现
         */
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length < 1 || size <= 0 || size > num.length) return res;

        // queue记录索引
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            // 超出范围的去掉
            while (!queue.isEmpty() && queue.peek() < i - size + 1) queue.poll();

            // 当前值大于之前的值，之前的不可能是最大值，可以删掉
            while (!queue.isEmpty() && num[i] >= num[queue.getLast()]) queue.removeLast();

            queue.add(i);

            if (i >= size - 1) {//此时开始是第一个滑动窗口
                res.add(num[queue.peek()]);
            }
        }
        return res;
    }

    public static ArrayList<Integer> maxInWindows_1(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length < 1 || size <= 0 || size > num.length) return res;

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - size + 1) deque.poll();

            while (!deque.isEmpty() && num[i] >= num[deque.getLast()]) deque.removeLast();


            deque.add(i);

            if (i >= size - 1) {
                res.add(num[deque.peek()]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 2, 6, 2, 5, 1};
        ArrayList<Integer> res = maxInWindows(arr, 3);
        for (Integer i : res) {
            System.out.print(i + " ");
        }
    }
}
