package com.sqq.data.util;

public class RandomArr {
    public static int[] generate(int num, int l) {
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * l);
        }
        return arr;
    }
}
