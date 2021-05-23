package com.sqq.data.algorithem.search.binarytree.threaded;

public class ThreadedBinaryTreeDemo {

}

class BinaryTree {
    private HeroNode root;

    /**
     * 为了实现线索化，需要创建要给指向当前节点得前驱节点得指针
     * 在递归线索化时，pre总是保留前一个节点
     */
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 编写中序线索化的方法
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());
        /**
         * 1. leftType==0表示指向的是左子树；如果是1，表示指向前驱节点
         * 2. rightType==0表示指向的是右子树；如果是1，表示指向后继节点
         */
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        /**
         * ?????
         */
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        threadedNodes(node.getRight());
    }

    /**
     * 使用线性遍历
     */
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
    }

    /**
     * 后序遍历
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

    /**
     * 1. leftType==0表示指向的是左子树；如果是1，表示指向前驱节点
     * 2. rightType==0表示指向的是右子树；如果是1，表示指向后继节点
     */
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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