package com.company;

import java.util.Stack;

public class Helper {

    public static boolean isOperator(char operator) {
        return  operator == '+' ||
                operator == '-' ||
                operator == '*' ||
                operator == '/' ||
                operator == '^' ||
                operator == '%';
    }

    public static boolean isOperand(char operand) {
        return  (operand >= 'a' && operand <= 'z') ||
                (operand >= 'A' && operand <= 'Z');
    }

    public static int precedence(char operator) {
        if (operator == '#') { return 0; }
        else if (operator == '+' || operator == '-') { return 1; }
        else if (operator == '*' || operator == '%' || operator == '/') { return 2; }
        else if (operator == '^') { return 3; }

        return -1;
    }

    public static boolean isStartParenthesis(char startPar) {
        return startPar == '(';
    }

    public static boolean isEndParenthesis(char endPar) {
        return endPar == ')';
    }

    public static boolean isOperandOnlyOneLeftInStack(Stack st, String notation) {
        if(st.size() == 1) {
            return true;
        }
        System.err.println("Wrong " + notation + " Notation Evaluation; Please Repeat again");
        return false;
    }
}
