package com.sqq.data.algorithem;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 把n个待排序的元素看成为一个有序表和一个无序表，
 * 开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中去除第一个元素，
 * 把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
        int[] arr = RandomArr.generate(80000, 80000);
        sort(arr);
    }

    public static void sort(int[] arr) {
        LocalTime t1 = LocalTime.now();
//        int insertVal = arr[1];
//        int insertIndex = 0;
//        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
//            arr[insertIndex + 1] = arr[insertIndex];
//            insertIndex--;
//        }
        // 推出while循环，找到了插入位置
//        arr[insertIndex + 1] = insertVal;
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 推出while循环，找到了插入位置
            arr[insertIndex + 1] = insertVal;
        }
//        System.out.println(Arrays.toString(arr));

        System.out.println("Insert Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }
}
