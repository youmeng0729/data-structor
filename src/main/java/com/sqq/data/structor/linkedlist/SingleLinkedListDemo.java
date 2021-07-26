package com.sqq.data.structor.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1, "Song Jiang", "Ji Shi Yu");
        HeroNode h2 = new HeroNode(2, "Lu JunYi", "Yu QiLin");
        HeroNode h3 = new HeroNode(3, "Wu Yong", "Zhi DuoXing");
        HeroNode h4 = new HeroNode(4, "Lin Chong", "Bao ZiTou");
        SingleLinkedList list = new SingleLinkedList();
        list.addWithoutSort(h1);
        list.addWithoutSort(h2);
        list.addWithoutSort(h3);
        list.addWithoutSort(h4);
        list.list();

        SingleLinkedList list2 = new SingleLinkedList();
        list2.addWithSort(h1);
        list2.addWithSort(h4);
        list2.addWithSort(h3);
        list2.addWithSort(h2);
        list2.addWithSort(h2);
        list2.list();
        h2.name = h2.name + "-----";
        list2.update(h2);
        list2.list();
        System.out.println(list2.getLength());
        System.out.println("*****************");
        list2.delete(h1);
        list2.list();
        System.out.println("*****************");
        list2.delete(h4);
        list2.list();

        System.out.println(list2.getLength());
    }
}

// Manage HeroNode
class SingleLinkedList {
    // init a head node
    private HeroNode head = new HeroNode(0, "", "");

    // add node to linked list(without sort)
    public void addWithoutSort(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) { // when return from while, temp will forward to the end of the linked list
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * @param heroNode
     */
    public void addWithSort(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false; // flag for this node exist
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.println("This Hero:" + heroNode + " is already exist!!");
            return;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    // delete node
    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = heroNode.next;
            return;
        }
        System.out.println("This Node:\" + heroNode + \"  doesn't exist!");

    }

    // modify by no
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("This Linked List is empty");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                return;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
            return;
        }
        System.out.println("Can't find this Hero");
    }

    public int getLength() {
        return getLength(head);
    }

    // get linked list size
    public int getLength(HeroNode heroNode) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    public void list() {
        if (head.next == null) {
            System.out.println("linked list is empty");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode() {}

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}