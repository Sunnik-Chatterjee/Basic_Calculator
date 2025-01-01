package com.example.basiccalculator;
import java.util.Stack;
public class InfixToPostFix {
    private int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public String InToPost(String exp)
    {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        StringBuilder numberBuffer = new StringBuilder();
        for (char c : exp.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                //Building a number supports decimal
                numberBuffer.append(c);
            } else {
                if (numberBuffer.length() > 0) {
                    postfix.append(numberBuffer).append(" ");
                    numberBuffer.setLength(0);
                }
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                } else if (isOperator(c)) {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(c);
                }
            }
        }
        if (numberBuffer.length() > 0) {
            postfix.append(numberBuffer).append(" ");
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
    return postfix.toString().trim();
    }
    private boolean isNumber(String c){
        try{
            Double.parseDouble(c);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.trim().split("\\s+");

        for (String i : tokens) {
            // Check if i is an operand
            if (isNumber(i)){
                stack.push(Double.parseDouble(i));
            }
            // If i is an operator
            else {
                double right = stack.pop(); // Pop the top element
                double left = stack.pop(); // Pop the second top element
                double result = 0;

                switch (i) {
                    case "+":
                        result = left + right;
                        break;
                    case "-":
                        result = left - right;
                        break;
                    case "*":
                        result = left * right;
                        break;
                    case "/":
                        if(right==0){
                            return "A number cannot be divide by zero";
                        }
                        result = left / right;
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop().toString();
    }
}
