package com.sqq.data.algorithem.apply;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 * 给定n个全职作为n个叶子结点，构造一棵二叉树，若该树的带权路径长度（wpl）达到最小，称这样的二叉树为最优二叉树，也成为赫夫曼树
 * 赫夫曼树是带权路径长度最短的树，权值较大的结点距离根较近
 *
 * 路径长度：通路中分支的数据，若规定根节点的而层数为1，则从跟结点到第L层结点的路径长度为L-1
 * 结点的带权路径长度为：从根节点到该结点之间的路径长度与该结点的权的乘积
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrderList();
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        int count = 0;
        while (nodes.size() > 1) {
            count++;
            Collections.sort(nodes);
            System.out.println("nodes=" + nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);

            nodes.add(parent);

            Collections.sort(nodes);

            System.out.println(count + " -->result: " + nodes);
        }

        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {

    public void preOrderList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderList();
        }
        if (this.right != null) {
            this.right.preOrderList();
        }
    }

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
