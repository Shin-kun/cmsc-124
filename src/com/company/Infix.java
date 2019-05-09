package com.company;

import java.util.EmptyStackException;
import java.util.Stack;

import static com.company.Helper.*;

public class Infix {
    static Stack<String> operandStack = new Stack<>();
    static Stack<Character> operatorStack = new Stack<>();

    public static boolean evaluationInfix(String expression) throws EmptyStackException {
        operandStack.clear();
        operatorStack.clear();

        operatorStack.push('#');

        for(int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (isOperand(character)) {
                operandStack.push(Character.toString(character));

            } else if (isOperator(character)) {
                while(precedence(character) <= precedence(operatorStack.peek()) && !isStartParenthesis(operatorStack.peek())) {
                    operationInfix();
                }
                operatorStack.push(character);

            } else if (isStartParenthesis(character)) {
                operatorStack.push(character);

            } else if (isEndParenthesis(character)) {
                while(!operatorStack.peek().equals('(')) {
                    if(operatorStack.peek().equals('#')) {
                        System.err.println("Wrong Infix Notation Evaluation; Please Repeat again");
                        return false;
                    }
                    operationInfix();
                }
                operatorStack.pop();
            }
       }

        // no more characters to read
        while(operatorStack.size() > 1) {
            if(operatorStack.peek().equals('(')) {
                System.err.println("Wrong Infix Notation Evaluation; Please Repeat again");
                return false;
            }
            try {
                operationInfix();
            } catch(EmptyStackException es) { }
        }

        return isOperandOnlyOneLeftInStack(operandStack, "Infix");
    }

    public static void operationInfix() {
        try {
            operatorStack.pop();
            operandStack.pop();
            operandStack.pop();
        } catch(EmptyStackException es) {
            System.err.println("Wrong Infix Notation Evaluation; Please Repeat again");
            throw es;
        }
        String newOperand = "T";
        operandStack.push(newOperand);
    }

    public static String infixToPostfix(String expression) {
        operandStack.clear();
        operatorStack.clear();

        operatorStack.push('#');

        String output = "";

        for(int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if(isOperand(character)) {
                output += character;

            } else if (isOperator(character)) {
                while(precedence(character) <= precedence(operatorStack.peek()) && !isStartParenthesis(operatorStack.peek())) {
                    output += operatorStack.peek();
                    operatorStack.pop();
                }
                operatorStack.push(character);
            } else if (isStartParenthesis(character)) {
                operatorStack.push(character);

            } else if (isEndParenthesis(character)) {
                while(!operatorStack.peek().equals('(')) {
                    output += operatorStack.peek();
                    operatorStack.pop();
                }
                operatorStack.pop();
            }
        }

        // no more characters to read
        while(!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals('#')) { break; }
            output += operatorStack.pop();
        }
        return output;
    }

    public static String infixToPrefix(String expression) {
        String output;

        StringBuilder st = new StringBuilder(expression).reverse();

        for(int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '(') {
                st.setCharAt(i, ')');
            } else if (st.charAt(i) == ')') {
                st.setCharAt(i, '(');
            }
        }

        output = infixToPostfix(st.toString());

        output = new StringBuilder(output).reverse().toString();

        return output;
    }
}
