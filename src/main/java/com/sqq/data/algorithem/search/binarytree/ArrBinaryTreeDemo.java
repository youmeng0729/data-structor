package com.sqq.data.algorithem.search.binarytree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree abt = new ArrBinaryTree(arr);
        abt.preOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 完成顺序存储二叉树的前序遍历
     * 顺序存储二叉树的特点
     * 1. 通常只考虑完全二叉树
     * 2. 第n个元素的左子节点为2*n+1
     * 3. 第n个元素的右子节点为2*n+2
     * 4. 第n个元素的父节点为(n-1)/2
     *
     * @param index 数组下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.println(arr[index]);

        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2 < arr.length)) {
            preOrder(index * 2 + 2);
        }
    }
}
