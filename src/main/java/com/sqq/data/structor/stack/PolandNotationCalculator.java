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
        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);
        int calculator = calculator(listString);
        System.out.println(calculator);
    }

    public static List<String> getListString(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, strings);

        return list;
    }

    public static int calculator(List<String> expList) {
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

