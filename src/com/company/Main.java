package com.company;

import java.util.EmptyStackException;
import java.util.Scanner;

import static com.company.Infix.*;
import static com.company.Postfix.*;
import static com.company.Prefix.*;

public class Main {

    public static void main(String[] args) {

        boolean check = false;
        Scanner sc = new Scanner(System.in);

        while(!check) {
            int menu;
            do {
                System.out.println("\nPlease choose a number between 1-4: ");
                System.out.println("1. Infix");
                System.out.println("2. Prefix");
                System.out.println("3. Postfix");
                System.out.println("4. Exit");
                menu = sc.nextInt();
            } while(menu <= 0 || menu >= 5);

            if (menu == 4) { break; }

            String expression = "";
            try {
                expression = sc.next();
                switch (menu) {
                    case 1:
                        check = evaluationInfix(expression);
                        break;
                    case 2:
                        check = evaluationPrefix(expression);
                        break;
                    case 3:
                        check = evaluationPostfix(expression);
                        break;
                    default:
                        break;
                }

            } catch(EmptyStackException es) {

            } finally {
                if (check && menu == 1) {
                    System.out.println("Prefix version: ");
                    System.out.println(infixToPrefix(expression));

                    System.out.println("Post version: ");
                    System.out.println(infixToPostfix(expression));
                } else if (check && menu == 2) {
                    System.out.println("Infix version: ");
                    System.out.println(prefixToInfix(expression));

                    System.out.println("Postfix version: ");
                    System.out.println(prefixToPostfix(expression));
                } else if (check && menu == 3) {
                    System.out.println("Infix version: ");
                    System.out.println(postfixToInfix(expression));

                    System.out.println("Prefix version: ");
                    System.out.println(postfixToPrefix(expression));
                }
                check = false;
            }
        }
    }
}
