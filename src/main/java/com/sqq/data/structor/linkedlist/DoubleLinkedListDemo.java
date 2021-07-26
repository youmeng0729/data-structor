package com.sqq.data.structor.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("Start ... ");
        HeroNode2 h1 = new HeroNode2(1, "Song Jiang", "Ji Shi Yu");
        HeroNode2 h2 = new HeroNode2(2, "Lu JunYi", "Yu QiLin");
        HeroNode2 h3 = new HeroNode2(3, "Wu Yong", "Zhi DuoXing");
        HeroNode2 h4 = new HeroNode2(4, "Lin Chong", "Bao ZiTou");

        DoubleLinkedList list = new DoubleLinkedList();
        list.addWithoutSort(h1);
        list.addWithoutSort(h2);
        list.addWithoutSort(h3);
        list.addWithoutSort(h4);
        list.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // delete node
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("List is empty");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
            return;
        }
        System.out.println("This Node:\" + heroNode + \"  doesn't exist!");
    }

    // add node to linked list(without sort)
    public void addWithoutSort(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) { // when return from while, temp will forward to the end of the linked list
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // modify by no
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("This Linked List is empty");
            return;
        }
        HeroNode2 temp = head.next;
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

    public void list() {
        if (head.next == null) {
            System.out.println("linked list is empty");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode2() {
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}