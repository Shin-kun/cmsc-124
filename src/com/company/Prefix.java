package com.company;

import java.util.EmptyStackException;
import java.util.Stack;

import static com.company.Helper.*;
import static com.company.Infix.infixToPostfix;

public class Prefix {
    static Stack<Character> operandStack = new Stack<>();
    static Stack<Character> parStack = new Stack<>();

    public static boolean evaluationPrefix(String expression) throws EmptyStackException {
        operandStack.clear();
        parStack.clear();

        for(int i = expression.length() - 1; i >= 0; i--) {
            char character = expression.charAt(i);
            if (isOperand(character)) {
                operandStack.push(character);

            } else if(isOperator(character)) {
                operationPrefix();

            } else if (isEndParenthesis(character)) {
                parStack.push(character);

            } else if (isStartParenthesis(character)) {
                parStack.pop();
            }
        }

        if(parStack.size() > 0) {
            System.err.println("Wrong Prefix Notation Evaluation; Please Repeat again");
            return false;
        }

        return isOperandOnlyOneLeftInStack(operandStack, "Prefix");
    }

    public static void operationPrefix() {
        try {
            operandStack.pop();
            operandStack.pop();
        } catch(EmptyStackException es) {
            System.err.println("Wrong Prefix Notation Evaluation; Please Repeat again");
            throw es;
        }
        operandStack.push('T');
    }

    public static String prefixToInfix(String expression) {
        Stack<String> ev = new Stack<>();
        String output;

        for(int i = expression.length() - 1; i >= 0; i--) {
            char character = expression.charAt(i);

            if (isOperand(character)) {
                ev.push(Character.toString(character));

            } else if(isOperator(character)) {
                String newOperand;
                String operand1 = ev.pop();
                String operand2 = ev.pop();
                newOperand = "(" + operand1 + character + operand2 + ")";
                ev.push(newOperand);
            }
        }
        output = ev.pop();
        return output;
    }

    public static String prefixToPostfix(String expression) {
        String infix = prefixToInfix(expression);
        String output = infixToPostfix(infix);
        return output;
    }
}
