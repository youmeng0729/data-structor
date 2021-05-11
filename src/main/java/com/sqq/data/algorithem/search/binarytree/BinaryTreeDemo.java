package com.sqq.data.algorithem.search.binarytree;

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
        HeroNode node5 = new HeroNode(5, "Lu Zhishen");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);

        node3.setLeft(node5);

        bt.setRoot(node1);

        System.out.println("*****Preposition*****");
        bt.preOrder();
        System.out.println("*****Infix*****");
        bt.infixOrder();
        System.out.println("*****Post*****");
        bt.postOrder();
        System.out.println("*****Preposition*****");
        HeroNode h1 = bt.preOrderSearch(5);
        System.out.println("--->" + h1);
        System.out.println("*****Infix*****");
        HeroNode h2 = bt.infixOrderSearch(5);
        System.out.println("--->" + h2);
        System.out.println("*****Post*****");
        HeroNode h3 = bt.postOrderSearch(5);
        System.out.println("--->" + h3);
        System.out.println("************");
        bt.delete(3);
        bt.preOrder();
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

    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    public void delete(int no) {
        if (this.root != null) {
            if (root.getNo() == no) {
                root = null;
            }
            this.root.delete(no);
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

    /**
     * - 前序查找
     * 1. 判断当前节点的no是否等于要查找的
     * 2. 相等，则返回
     * 3. 不相等，则判断当前节点的左子节点是否为空，不为空则递归查找
     * 4. 如果左递归前序找到则返回，否则，右子节点是否为空，不为空则递归查找
     * - 中序查找
     * 1. 判断当前节点的左子节点是否为空，不为空则递归中序查找
     * 2. 找到则返回；否则就和当前节点比较，相等则返回，
     * 3. 否则继续右递归中序查找
     * - 后序查找
     * 1. 判断当前节点的左子节点是否为空，不为空则递归后序查找
     * 2. 找到则返回；否则就和当前节点的右子节点是否为空，不为空则右递归后续查找，
     * 3. 否则和当前节点比较
     */
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.infixOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode res = null;
        if (this.left != null) {
            res = this.left.postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.no == no) {
            res = this;
        }
        return res;
    }

    /**
     * 如果删除的是叶子节点，删除叶子节点
     * 非叶子节点，删除该子树
     */
    public void delete(int no) {
        if (this.left != null && this.left.no == no) {
            {
                this.left = null;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            {
                this.right = null;
                return;
            }
        }
        if (this.left != null) this.left.delete(no);
        if (this.right != null) this.right.delete(no);
    }
}
