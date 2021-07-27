package com.sqq.data.structor.linkedlist;

/**
 * Use to Solve Josephu problem
 */
public class SingCircleLinkedListDemo {
    public static void main(String[] args) {
        SingCircleLinkedList list = new SingCircleLinkedList();
        list.addBoy(5);
        list.list();
        list.countBoy(1, 2, 5);
    }

}

class SingCircleLinkedList {
    private Boy first;


    /**
     * 出圈方法
     *
     * @param startNo  从哪个孩子开始数
     * @param countNum 数几下
     * @param nums     有多少小孩在队列中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("The params is not correct!");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // move helper and first to the startNo position
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // count off countNum - 1 time
        while (true) {
            if (helper == first) {
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("The target Boy is:" + first.getNo());
            // move the target
            first = first.getNext();
            helper.setNext(first);
        }
        // there is only one boy when returns from the loop
        System.out.println("The last Boy is: " + first.getNo());
    }

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("Num is not correct");
        }
        first = new Boy(1);
        first.setNext(first);
        Boy curBoy = first;
        for (int i = 2; i <= nums; i++) {
            Boy boy = new Boy(i);
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }

    }

    public void list() {
        if (first == null) {
            System.out.println("List is empty");
            return;
        }
        Boy temp = first;
        while (true) {
            System.out.println("Boy Number is -->" + temp.getNo());
            if (temp.getNext() == first) {
                System.out.println("Over");
                break;
            }
            temp = temp.getNext();
        }
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}