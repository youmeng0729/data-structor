package com.sqq.data.algorithem.search;

/**
 * 线性查找
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int search = search(arr, 5);
        if (search == -1) {
            System.out.println("non");
        } else {
            System.out.println("ok");
        }

    }

    public static int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
