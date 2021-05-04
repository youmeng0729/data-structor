package com.sqq.data.algorithem.search;

/**
 * 二分查找：需要多次递推才能找到mid
 * mid = (left + right) / 2 --> mid = left + (right - left) / 2
 * 插值(要求数组时有序的)：
 * mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 * 本质上还是采用递归，所以还是以空间换取时间，要注意数据量深度
 */
public class InterpolationSearch {
    private static int count = 0;

    public static void main(String[] args) {
        int[] arr = new int[1000000];
        for (int i = 1; i <= 1000000; i++) {
            arr[i - 1] = i;
        }
        int search = search(arr, 0, arr.length - 1, 5929);
        System.out.println("--->" + search + "   " + count);

    }

    public static int search(int[] arr, int left, int right, int findVal) {
        count++;
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        /*
        对于数据量较大，关键字分布比较均匀的情况下，采用插值查找速度较快
        分布不均匀的情况下，该方法不一定比折半查找更好
         */
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        int midVal = arr[mid];
        if (findVal > midVal) {
            return search(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return search(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
