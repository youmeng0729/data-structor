package com.sqq.data.algorithem;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * - 第一次从arr[0]~ar[n-1]中选择最小值，与arr[0]交换；
 * - 第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换；
 * - 第三次……；
 * - 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，...；
 * - 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换；
 * - 总共通过n-1次，得到一个按排序码从小到大排列的有序列表
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        sort(RandomArr.generate(80000, 80000));
    }

    public static void sort(int[] arr) {
        LocalTime t1 = LocalTime.now();
        int minIndex;
        int min;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                arr[minIndex] = arr[i];
                arr[i] = min;
//                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("Select Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }

}