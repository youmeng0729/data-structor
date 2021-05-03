package com.sqq.data.algorithem.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路分析：
 * 1. 首先去欸的那个该数组的中间的下标
 * mid = (left + right) / 2
 * 2. 然后让需要查找的数findVal 和 arr[mid]比较
 * 2.1 findVal > arr[mid]，说明你要查找的数在mid的右边，因此需要递归向右查找
 * 2.2 findVal < arr[mid]，说明你要查找的数在mid的左边，因此需要递归向左查找
 * 2.3 findVal == arr[mid]，说明找到，返回
 * <p>
 * 结束递归条件
 * 1. 找到
 * 2. 递归完整个数组，仍然没有找到--> left > right
 */
public class BinarySearch {
    // 使用二分查找，数组必须是有序的
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int val = search(arr, 0, arr.length - 1, 8);
        System.out.println("--->" + val);
        int[] arr2 = {1, 8, 10, 89, 1000, 1234, 1234};
        List<Integer> list = search2(arr2, 0, arr2.length - 1, 1234);
        System.out.println("--->" + list);

    }

    /**
     * @param arr   数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param value 要查找的值
     * @return 所在下标
     */
    public static int search(int[] arr, int left, int right, int value) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (value > midVal) {
            // 向右递归
            return search(arr, mid + 1, right, value);
        } else if (value < midVal) {
            //向左递归
            return search(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    public static List<Integer> search2(int[] arr, int left, int right, int value) {
        if (left > right) return new ArrayList<>();
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (value > midVal) {
            // 向右递归
            return search2(arr, mid + 1, right, value);
        } else if (value < midVal) {
            //向左递归
            return search2(arr, left, mid - 1, value);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            // 左边
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == value) {
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);
            // 右边
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == value) {
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
