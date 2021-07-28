package com.sqq.data.structor.stack;

import java.util.Scanner;

/**
 * TODO use linked list to implement stack
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("Show:");
            System.out.println("Exit:");
            System.out.println("Push:");
            System.out.println("Pop:");
            System.out.println("Please input your choose");

            key = scanner.next();
            switch (key) {
                case "Show":
                    stack.list();
                    break;
                case "Push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "Pop":
                    try {
                        int pop = stack.pop();
                        System.out.println("Result is:" + pop);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("Please check your input");
                    break;
            }
        }

        System.out.println("Exit~~~");
    }
}

class ArrayStack {
    private int maxSize; // the size of this stack
    private int[] stack;
    private int top = -1; // the top this stack

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is Full");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            throw new RuntimeException("Stack is Empty");
        }
        return stack[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }


}
