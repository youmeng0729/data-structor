package com.sqq.data.algorithem.sort;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 对冒泡排序的一种改进
 * 通过一趟排序，将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后，再按次方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此得到整个数据变成有序序列
 * <p>
 * 用到了递归，用了空间换时间
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = RandomArr.generate(80000000, 80000);
        LocalTime t1 = LocalTime.now();
        sort(arr, 0, arr.length - 1);
        System.out.println("Quick Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }


    public static void sort(int[] arr, int left, int right) {
        int l = left;
        int r = right;

        int pivot = arr[(l + r) / 2];
        // while循环的目的是让比pivot小的放到左边，比pivot大的放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot才推出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot才推出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 结束
            if (l >= r) {
                break;
            }
            // 交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot)
                r -= 1;

            if (arr[r] == pivot)
                l += 1;

        }
        // 防止出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 左递归
        if (left < r) {
            sort(arr, left, r);
        }
        // 右递归
        if (right > l) {
            sort(arr, l, right);
        }
//        System.out.println(Arrays.toString(arr));

    }
}
