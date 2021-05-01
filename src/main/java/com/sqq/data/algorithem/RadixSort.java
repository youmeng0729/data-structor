package com.sqq.data.algorithem;

import com.sqq.data.util.RandomArr;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 标准的空间换时间的算法
 * https://code.i-harness.com/zh-CN/q/e98fa9
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = RandomArr.generate(8000 * 1000, 80000);
        LocalTime t1 = LocalTime.now();
        sort(arr);
        System.out.println("Radix Sort Run time is: " + Duration.between(t1, LocalTime.now()).getSeconds() + " s");
    }

    public static void sort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            int[][] bucket = new int[10][arr.length];
            // 表示每个桶放入的元素个数
            int[] beCounts = new int[10];
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的个位
                int digitOfElement = arr[j] / n % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][beCounts[digitOfElement]] = arr[j];//?
                beCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < beCounts.length; k++) {
                if (beCounts[k] > 0) {
                    for (int l = 0; l < beCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                beCounts[k] = 0;
            }
        }
//        System.out.println(Arrays.toString(arr));
    }
}
