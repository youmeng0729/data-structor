package com.sqq.data.structor.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "300+2*6-2";
        calculate(expression);

    }

    public static void calculate(String expression) {
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 opeStack = new ArrayStack2(10);
        int index = 0;// used for scanning
        int num1;
        int num2;
        int operator;
        int result;
        char ch;
        String keepNUm = "";
        do {
            ch = expression.substring(index, index + 1).charAt(0);
            if (opeStack.isOperator(ch)) {
                if (!opeStack.isEmpty()) {
                    // if its priority <= char that is from the stack
                    if (opeStack.getPriority(ch) <= opeStack.getPriority(opeStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = opeStack.pop();
                        result = numStack.calculate(num1, num2, operator);

                        numStack.push(result);
                    }
                }
                opeStack.push(ch);
            } else {
                // the scan result char is not number, so need to transfer to number: number = char -48

                // when do with multi numbers, need to check next one whether it is number
                keepNUm += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNUm));
                } else {
                    if (opeStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNUm));
                        keepNUm = "";
                    }
                }
            }
        }
        while (++index < expression.length());

        while (!opeStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = opeStack.pop();
            result = numStack.calculate(num1, num2, operator);

            numStack.push(result);
        }
        System.out.println("Expression is: " + expression);
        System.out.println("Calculate result is:" + numStack.pop());
    }
}

class ArrayStack2 {
    private final int maxSize; // the size of this stack
    private final int[] stack;
    private int top = -1; // the top this stack

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * @return return the value on the top of the stack
     */
    public int peek() {
        return stack[top];
    }

    /**
     * @return return priority
     */
    public int getPriority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        }
        if (operator == '+' || operator == '-') {
            return 0;
        }
        return -1;
    }

    public boolean isOperator(int operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    public int calculate(int num1, int num2, int operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
                throw new RuntimeException("The operator is not correct");
        }
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
