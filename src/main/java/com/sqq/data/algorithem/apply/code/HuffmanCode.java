package com.sqq.data.algorithem.apply.code;


import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        /*String str = "i like like like java do you like a java";
        byte[] bytes = huffmanZip(str);
        // System.out.println(Arrays.toString(bytes));
        byte[] decode = decode(huffmanCodes, bytes);
        // System.out.println(new String(decode));*/
        /*String srcFile = "C:\\Users\\youme\\Desktop\\图片1.jpg";
        String dstFIle = "C:\\Users\\youme\\Desktop\\dst.zip";
        zipFile(srcFile, dstFIle);*/
        String zipFile = "C:\\Users\\youme\\Desktop\\dst.zip";
        String dstFile = "C:\\Users\\youme\\Desktop\\图片2.jpg";
        unZipFile(zipFile, dstFile);
    }

    /**
     * 解压文件
     *
     * @param zipFile
     * @param dstFile
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(codes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 压缩文件
     *
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);

            // 文件对应的编码表
            byte[] huffmanZip = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出六关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 以对象流的方式写入 赫夫曼编码，是为了以后恢复文件时使用
            oos.writeObject(huffmanZip);
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * @param huffmanCodes 哈夫曼编码表 map
     * @param huffmanBytes 需要解码的字节数组
     * @return 原来的字符串对应的字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            sBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = sBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * byte转换成二进制字符串
     *
     * @param flag 标志是否需要补高位，最后一个字节不需补高位
     * @param b    传入的byte
     * @return b 对应的二进制字符串（补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        // 如果是正数，还需要补高位
        if (flag) {
            temp |= 256; // ????
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(String str) {
        byte[] contentBytes = str.getBytes();
        // System.out.println(contentBytes.length);
        return huffmanZip(contentBytes);
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // System.out.println(nodes);

        Node huffmanTree = createHuffmanTree(nodes);
        preOrder(huffmanTree);

        // System.out.println("-------------------------------------");
        getCodes(huffmanTree);

        return zip(bytes, huffmanCodes);
    }


    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1. 利用huffmanCodes 将Byte转换成huffman编码对应的字符串
        StringBuilder sBuilder = new StringBuilder();
        // 2. 遍历bytes
        for (byte b : bytes) {
            sBuilder.append(huffmanCodes.get(b));
        }
        // System.out.println(sBuilder);
        // System.out.println(sBuilder.length());

        int len = (sBuilder.length() + 7) / 8;
        int index = 0;
        byte[] huffmanCodeBytes = new byte[len];
        for (int i = 0; i < sBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > sBuilder.length()) {
                strByte = sBuilder.substring(i);
            } else {
                strByte = sBuilder.substring(i, i + 8);
            }

            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }


    static Map<Byte, String> huffmanCodes = new HashMap<>();

    static StringBuilder stringBuffer = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuffer);
        getCodes(root.right, "1", stringBuffer);
        return huffmanCodes;
    }

    /**
     * @param node
     * @param code         路径：左子结点是0，右子结点是1
     * @param stringBuffer 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuffer) {
        StringBuilder buffer = new StringBuilder(stringBuffer);
        buffer.append(code);
        if (node != null) {
            // 判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {//非叶子结点
                getCodes(node.left, "0", buffer);
                getCodes(node.right, "1", buffer);
            } else {
                huffmanCodes.put(node.data, buffer.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }

        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(right);
            nodes.remove(left);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        // System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
