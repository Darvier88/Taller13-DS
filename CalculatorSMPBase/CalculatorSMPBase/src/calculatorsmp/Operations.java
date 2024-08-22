/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorsmp;
import java.util.Stack;
/**
 *
 * @author Djurado
 */
public class Operations {

    private static String[] op = { "+", "-", "*", "/" };// Operation set

    public static String MakeFormula() {
        StringBuilder build = new StringBuilder();
        int count = (int) (Math.random() * 2) + 1; // Genera entre 1 y 2 operadores
        int number1 = (int) (Math.random() * 99) + 1;
        build.append(number1);
        for (int i = 0; i < count; i++) {
            int operation = (int) (Math.random() * 4); // Selecciona un operador
            int number2 = (int) (Math.random() * 99) + 1;
            build.append(op[operation]).append(number2);
        }
        return build.toString();
    }

    public static String Solve(String formula) {
    // Validación de la entrada: Verificar si hay operadores consecutivos (excepto combinaciones válidas como +-, *-, /-, --)
    if (formula.matches(".*([\\+\\*/]{2,}|[\\+\\*/]{1}[^\\d-]).*")) {
        throw new IllegalArgumentException("Invalid input: operadores consecutivos");
    }

    Stack<Integer> numberStack = new Stack<>(); // Pila para almacenar números
    Stack<Character> operatorStack = new Stack<>(); // Pila para almacenar operadores

    int len = formula.length();
    for (int i = 0; i < len; i++) {
        char ch = formula.charAt(i);

        // Manejo de números negativos
        if (ch == '-' && (i == 0 || "+-*/(".indexOf(formula.charAt(i - 1)) >= 0)) {
            int num = 0;
            i++;
            char nextChar = formula.charAt(i);
            while (i < len && Character.isDigit(nextChar)) {
                num = num * 10 + (nextChar - '0');
                i++;
                if (i < len) {
                    nextChar = formula.charAt(i);
                }
            }
            i--; // Retrocede para compensar el incremento adicional del bucle
            numberStack.push(-num);
        } else if (Character.isDigit(ch)) {
            int num = 0;
            while (i < len && Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
                i++;
                if (i < len) {
                    ch = formula.charAt(i);
                }
            }
            i--; // Retrocede para compensar el incremento adicional del bucle
            numberStack.push(num);
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            while (!operatorStack.isEmpty() && hasPrecedence(ch, operatorStack.peek())) {
                int result = applyOp(operatorStack.pop(), numberStack.pop(), numberStack.pop());
                numberStack.push(result);
            }
            operatorStack.push(ch);
        }
    }

    while (!operatorStack.isEmpty()) {
        int result = applyOp(operatorStack.pop(), numberStack.pop(), numberStack.pop());
        numberStack.push(result);
    }

    return formula + "=" + numberStack.pop();
}



    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '+' || op2 == '-') {
            return op1 == '+' || op1 == '-';
        } else if (op2 == '*' || op2 == '/') {
            return true;
        }
        return false;
    }

    private static int applyOp(char op, int b, int a) {
    switch (op) {
        case '+':
            if ((a > 0 && b > Integer.MAX_VALUE - a) || (a < 0 && b < Integer.MIN_VALUE - a)) {
                throw new ArithmeticException("integer overflow");
            }
            return a + b;
        case '-':
            if ((a < 0 && b > Integer.MAX_VALUE + a) || (a > 0 && b < Integer.MIN_VALUE + a)) {
                throw new ArithmeticException("integer overflow");
            }
            return a - b;
        case '*':
            if (a != 0 && b != 0) {
                if ((b > 0 && (a > Integer.MAX_VALUE / b || a < Integer.MIN_VALUE / b))
                        || (b < 0 && (a < Integer.MAX_VALUE / b || a > Integer.MIN_VALUE / b))) {
                    throw new ArithmeticException("integer overflow");
                }
            }
            return a * b;
        case '/':
            if (b == 0) {
                System.out.println("Intentando dividir " + a + " por cero");
                throw new ArithmeticException("Cannot divide by zero");
            }
            // Verificación de desbordamiento en división
            if (a == Integer.MIN_VALUE && b == -1) {
                throw new ArithmeticException("integer overflow");
            }
            return a / b;
    }
        return 0;
    }



}