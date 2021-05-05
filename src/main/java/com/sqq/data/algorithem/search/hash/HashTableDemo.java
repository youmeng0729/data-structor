package com.sqq.data.algorithem.search.hash;

import java.util.Objects;
import java.util.Scanner;

/**
 * 散列表（Hash table，也叫做哈希表），是根据 关键码值 (key value)而直接进行访问的数据结构。
 * 也就是说，它通过把关键码值英伸到表中一个位置来访问记录，以加快查找的速度。
 * 这个映射函数叫做 散列函数，存放记录的 数组 叫做 散列表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加");
            System.out.println("list：显示");
            System.out.println("exit：推出");
            System.out.println("find：查找");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id：");
                    int id = scanner.nextInt();
                    System.out.println("输入名字：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id：");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                case "exit":
                    scanner.close();
                    break;
            }
        }
    }
}

// 创建HashTab，管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加
     */
    public void add(Emp emp) {
        // 根据emp id得到该员工插入哪条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp添加到对应的链表
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    /**
     * 遍历链表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i + 1);
        }
    }

    /**
     * 散列函数（取模法）
     */
    public int hashFun(int id) {
        return id % size;
    }

    public void findById(int id) {
        Emp emp = empLinkedListArray[hashFun(id)].findById(id);
        if (Objects.isNull(emp)) {
            System.out.println("This Employee is not in this list");
        } else {
            System.out.println("Employee is: " + emp.name);
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    private Emp head;

    /**
     * 假定id自增长，所以，添加直接到链表最后
     */
    public void add(Emp emp) {
        // 第一个员工
        if (Objects.isNull(head)) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (!Objects.isNull(curEmp.next)) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历
     */
    public void list(int no) {
        if (Objects.isNull(head)) {
            System.out.println("list " + no + " is null");
            return;
        }
        System.out.println("Current list " + no + " info: ");
        Emp curEmp = head;
        while (true) {
            System.out.printf("==> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (Objects.isNull(curEmp.next)) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    public Emp findById(int id) {
        if (Objects.isNull(head)) {
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (Objects.isNull(curEmp.next)) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}