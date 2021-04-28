package com.sqq.data.algorithem;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 对插入排序的升级
 * 希尔排序使把记录按下标的一定“增量”分组，对每组使用“直接插入排序算法排序；
 * 随着增量组件减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = RandomArr.generate(80000, 800000);
        sortChange(arr);
        sortMove(RandomArr.generate(80000, 800000));
    }

    /**
     * 交换法，速度慢
     */
    public static void sortChange(int[] arr) {
        LocalTime t1 = LocalTime.now();
        int temp;
//        for (int i = 5; i < arr.length; i++) {
//            for (int j = i - 5; j >= 0; j -= 5) {
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
        System.out.println("Shell sortChange Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }

    /**
     * 移位法
     */
    public static void sortMove(int[] arr) {
        LocalTime t1 = LocalTime.now();
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    if (j != i) {
                        arr[j] = temp;
                    }
                }
            }
        }
        System.out.println("Shell sortMove Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }
}
