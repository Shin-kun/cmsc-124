package com.company;

import java.util.EmptyStackException;
import java.util.Stack;

import static com.company.Helper.isEndParenthesis;
import static com.company.Helper.isOperand;
import static com.company.Helper.isOperandOnlyOneLeftInStack;
import static com.company.Helper.isOperator;
import static com.company.Helper.isStartParenthesis;
import static com.company.Infix.infixToPrefix;

public class Postfix {
    static Stack<Character> operandStack = new Stack<>();
    static Stack<Character> parStack = new Stack<>();

    public static boolean evaluationPostfix(String expression) throws EmptyStackException {
        operandStack.clear();
        parStack.clear();

        for(int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);
            if (isOperand(character)) {
                operandStack.push(character);

            } else if(isOperator(character)) {
                operationPostfix();

            } else if (isEndParenthesis(character)) {
                parStack.pop();

            } else if (isStartParenthesis(character)) {
                parStack.push(character);
            }
        }

        if(parStack.size() > 0) {
            System.err.println("Wrong Postfix Notation Evaluation; Please Repeat again");
            return false;
        }
        return isOperandOnlyOneLeftInStack(operandStack, "Postfix");
    }

    public static void operationPostfix() {
        try {
            operandStack.pop();
            operandStack.pop();
        } catch(EmptyStackException es) {
            System.err.println("Wrong Postfix Notation Evaluation; Please Repeat again");
            throw es;
        }
        operandStack.push('T');
    }

    public static String postfixToInfix(String expression) {
        Stack<String> ev = new Stack<>();
        String output;

        for(int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (isOperand(character)) {
                ev.push(Character.toString(character));

            } else if(isOperator(character)) {
                String newOperand;
                String operand1 = ev.pop();
                String operand2 = ev.pop();
                newOperand = "(" + operand2 + character + operand1 + ")";
                ev.push(newOperand);
            }
        }
        output = ev.pop();
        return output;
    }

    public static String postfixToPrefix(String expression) {
        String infix = postfixToInfix(expression);
        String output = infixToPrefix(infix);
        return output;
    }
}
