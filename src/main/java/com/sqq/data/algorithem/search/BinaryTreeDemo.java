package com.sqq.data.algorithem.search;

/**
 * - 前序遍历：先输出父节点，再遍历左子树和右子树
 * 1. 先输出当前节点（初始的时候是root节点）
 * 2. 如果左子节点不为空，则递归继续前序遍历
 * - 中序遍历：先遍历左子树，再输出父节点，再遍历右子树
 * 1. 如果当前节点的左子节点不为空，则递归中序遍历
 * 2. 输出当前节点
 * 3. 如果当前节点的右子节点不为空，则递归中序遍历
 * - 后序遍历：先遍历左子树，再遍历右子树，最后输出父节点
 * 1. 如果当前节点的左子节点不为空，则递归后序遍历
 * 2. 如果当前节点的右子节点不为空，则递归后序遍历
 * 3. 输出当前节点
 * - 小结：看输出父节点的顺序，就确定前序、中序还是后序
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        HeroNode node1 = new HeroNode(1, "Song Jiang");
        HeroNode node2 = new HeroNode(2, "Wu Yong");
        HeroNode node3 = new HeroNode(3, "Lu Junyi");
        HeroNode node4 = new HeroNode(4, "Lin Chong");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);

        bt.setRoot(node1);

        System.out.println("*****Preposition*****");
        bt.preOrder();
        System.out.println("*****Infix*****");
        bt.infixOrder();
        System.out.println("*****Post*****");
        bt.postOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }
    }

    /**
     * 中序
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
    }

    /**
     * 后序
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}
