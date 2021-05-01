package com.sqq.data.algorithem;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 利用 归并 的思想实现的排序方法，该算法采用经典的 分治(divide-and-conquer)策略
 * (分治法将问题成一些小的问题然后递归求解，而治的阶段则将分的阶段得到的各答案“修补”在一起）
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] arr = RandomArr.generate(80000 * 1000, 800000);
        int[] temp = new int[arr.length];
        LocalTime t1 = LocalTime.now();
        sort(arr, 0, arr.length - 1, temp);
        System.out.println("Merge Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");

    }


    public static void sort(int[] arr, int left, int right, int[] temp) {

        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }

    }

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序序列的初始化索引
        int j = mid + 1;// 初始化ij，右边有序序列的初始化索引
        int t = 0; // 指向temp数组的当前索引
        // 1. 先把左右两边（有序）的数据按照规则填充到temp数组
        //    直到左右两天的舒徐序列，有一边处理完毕
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }

        }
        // 2. 把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {// 左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {// 左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 3. 将temp数组的元素拷贝到arr
        t = 0;
        int leftTemp = left;
        while (leftTemp <= right) {
            arr[leftTemp] = temp[t];
            leftTemp += 1;
            t += 1;
        }
    }
}
