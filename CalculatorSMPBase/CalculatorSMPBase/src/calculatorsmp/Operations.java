/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorsmp;

import java.util.Stack;

public class Operations {

    private static String[] op = { "+", "-", "*", "/" }; // Operation set

    public static String MakeFormula() {
        StringBuilder build = new StringBuilder();
        int count = (int) (Math.random() * 2) + 1; // generate random count
        int start = 0;
        int number1 = (int) (Math.random() * 99) + 1;
        build.append(number1);
        while (start <= count) {
            int operation = (int) (Math.random() * 4); // generate operator
            int number2 = (int) (Math.random() * 99) + 1;
            build.append(op[operation]).append(number2);
            start++;
        }
        return build.toString();
    }

    public static String Solve(String formula) {
        Stack<Integer> numberStack = new Stack<>(); // Stack to store numbers
        Stack<Character> operatorStack = new Stack<>(); // Stack to store operators

        int len = formula.length();
        for (int i = 0; i < len; i++) {
            char ch = formula.charAt(i);

            if (Character.isDigit(ch)) {
                int num = 0;
                while (Character.isDigit(ch)) {
                    num = num * 10 + (ch - '0');
                    i++;
                    if (i < len) {
                        ch = formula.charAt(i);
                    } else {
                        break;
                    }
                }
                i--;
                numberStack.push(num);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operatorStack.isEmpty() && hasPrecedence(ch, operatorStack.peek())) {
                    numberStack.push(applyOp(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
                }
                operatorStack.push(ch);
            }
        }

        while (!operatorStack.isEmpty()) {
            numberStack.push(applyOp(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
        }

        return formula + "=" + numberStack.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }
}
