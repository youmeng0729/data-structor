package com.sqq.data.algorithem.search;

import java.util.Arrays;

/**
 * 斐波那契（黄金分割)查找算法
 * mid 不再时中间或者插值得到，而是位于黄金分割点附近，即mid=low+F(k-1)-1
 * F：斐波那契数列
 * F(k-1) -1 的理解
 * 1. 由斐波那契数列F[k]=F[k-1]+F[k-2]的性质，可以得到：F[k]-1=(F[k-1]-1) + (F[k-2]-1) + 1，说明，只要顺序表的长度为F[k]-1，则
 * 可以将该表分为长度为F[k-1]-1和F[k-2]-1两段。所以中间位置mid = low+F（k-1)-1
 * 2. 类似的，每一字段也可以用相同的方式分割
 * 3. 但顺序表长度n不一定刚好等于F[k]-1，所以需要将原来的书虚表长度n增加至F[k]-1。k只要能使得F[k]-1恰好大于或者等于即可，
 * 顺序表长度增加后，新增的位置，都赋为n位置的值即可
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int search = search(arr, 89);
        System.out.println("--->" + search);
    }

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    // 非递归方式
    // mid = low + F[k-1] - 1
    public static int search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; // 斐波那契分割数值的下标
        int mid;
        int[] f = fib();

        // 1.
        while (high > f[k] - 1) k++;
        // 2.
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        /*
        3. 主要查找的地方
         */
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                // ?
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2; // ?
            } else {
                if (mid <= high) return mid;
                return high;
            }
        }
        return -1; // 没有找到
    }
}
