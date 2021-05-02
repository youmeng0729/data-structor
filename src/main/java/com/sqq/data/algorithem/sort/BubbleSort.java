package com.sqq.data.algorithem.sort;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * 通过对待排序序列从前向后（从下标较小的元素开始），
 * 依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，
 * 就像水底下的气泡一样逐渐向上冒
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, -9, -1, 10, -2};
//        sort2(arr);
        sort2(RandomArr.generate(80000, 80000));
    }

    /**
     * 最原始的方法
     *
     */
    public static void sort1(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("-----------");
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("***");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 优化1：如果某次排序没有发生排序，则说明现在的排序已经是好的，后面的没必要再进行
     */
    public static void sort2(int[] arr) {
        LocalTime t1 = LocalTime.now();
        int temp;
        boolean isChange = false; //标志了一次循环中是否做了排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    isChange = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("-----------");
//            System.out.println(Arrays.toString(arr));
            if (!isChange) {
                break;
            } else {
                isChange = false;
            }
        }
//        System.out.println(ChronoUnit.NANOS.between(t1, LocalTime.now()));
        System.out.println("Bubble Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }


}
