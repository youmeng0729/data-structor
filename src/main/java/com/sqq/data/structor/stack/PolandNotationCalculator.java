package com.sqq.data.structor.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * suffix expression
 */
public class PolandNotationCalculator {
    public static void main(String[] args) {
//        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> listString = getSuffixExpressionList(suffixExpression);
//        System.out.println(listString);
//        int calculator = calculator(listString);
//        System.out.println(calculator);
        String suffixExpression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(suffixExpression);
        System.out.println(list);
        List<String> suffix = transferInfixToSuffix(list);
        System.out.println(suffix);
        int res = calculatorUseSuffix(suffix);
        System.out.println(res);
    }

    /**
     * transfer a infix expression to suffix expression list
     */
    public static List<String> transferInfixToSuffix(List<String> infixExpression) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        // Traversal infixExpression
        for (String ele : infixExpression) {
            if (ele.matches("\\d+")) {
                s2.add(ele);
            } else if (ele.equals("(")) {
                s1.push(ele);
            } else if (ele.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                // priority problem\
                // TODO
                while (s1.size() != 0 && (Operation.getValue(s1.peek()) >= Operation.getValue(ele))) {
                    s2.add((s1.pop()));
                }
                s1.push(ele);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


    /**
     * without any char to split the expression
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        StringBuilder builder = new StringBuilder();
        do {
            if (((c = s.charAt(i)) < 48) || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                // if c is a number, need to consider it is a multi-number;
                while (i  < s.length() && (s.charAt(i) >= 48) && ((c = s.charAt(i)) <= 57)) {
                    builder.append(c);
                    i++;
                }
                ls.add(builder.toString());
            }
        } while (i < s.length());

        return ls;
    }


    /**
     * transfer suffix expression to a list; using space to split
     *
     * @return suffix expression list
     */
    public static List<String> getSuffixExpressionList(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, strings);
        return list;
    }

    public static int calculatorUseSuffix(List<String> expList) {
        Stack<String> stack = new Stack<>();
        for (String ele : expList) {
            if (ele.matches("\\d+")) {
                stack.push(ele);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                switch (ele) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("Operator is not correct");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String operation) {
        switch (operation) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            default:
                return 0;
        }
    }
}