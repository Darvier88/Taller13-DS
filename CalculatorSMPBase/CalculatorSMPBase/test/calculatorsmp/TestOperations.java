package calculatorsmp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS VIVOBOOK PRO
 */
public class TestOperations {
    
    @Test
    public void testMakeFormula() {
        String formula = Operations.MakeFormula();
        assertTrue("La fórmula debería coincidir con el patrón esperado", 
                   formula.matches("\\d+[\\+\\-\\*/]\\d+([\\+\\-\\*/]\\d+)?"));
    }
    @Test
    public void testMakeFormulaNotNull() {
        String formula = Operations.MakeFormula();
        assertNotNull(formula, "La fórmula no debería ser nula");
    }

    @Test
    public void testSolveSimpleAddition() {
        String formula = "2+3";
        String resultado = Operations.Solve(formula);
        assertEquals("2+3=5", resultado, "El resultado de 2+3 debería ser 5");
    }

    @Test
    public void testSolveSimpleSubtraction() {
        String formula = "5-3";
        String resultado = Operations.Solve(formula);
        assertEquals("5-3=2", resultado, "El resultado de 5-3 debería ser 2");
    }

    @Test
    public void testSolveSimpleMultiplication() {
        String formula = "4*3";
        String resultado = Operations.Solve(formula);
        assertEquals("4*3=12", resultado, "El resultado de 4*3 debería ser 12");
    }

    @Test
    public void testSolveSimpleDivision() {
        String formula = "10/2";
        String resultado = Operations.Solve(formula);
        assertEquals("10/2=5", resultado, "El resultado de 10/2 debería ser 5");
    }

    @Test
    public void testSolveComplexExpression() {
        String formula = "2+3*4";
        String resultado = Operations.Solve(formula);
        assertEquals("2+3*4=14", resultado, "El resultado de 2+3*4 debería ser 14");
    }

    @Test
    public void testSolveDivisionByZero() {
        String formula = "10/0";
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Operations.Solve(formula);
        });
        assertTrue(exception.getMessage().contains("/ by zero"), "Dividir por cero debería lanzar una ArithmeticException");
    }
    @Test
    public void testSolveAdditionWithZero() {
        String formula = "0+5";
        String resultado = Operations.Solve(formula);
        assertEquals("0+5=5", resultado, "El resultado de 0+5 debería ser 5");
    }

    @Test
    public void testSolveSubtractionWithZero() {
        String formula = "0-3";
        String resultado = Operations.Solve(formula);
        assertEquals("0-3=-3", resultado, "El resultado de 0-3 debería ser -3");
    }

    @Test
    public void testSolveMultiplicationWithZero() {
        String formula = "0*7";
        String resultado = Operations.Solve(formula);
        assertEquals("0*7=0", resultado, "El resultado de 0*7 debería ser 0");
    }
    // Prueba para adición que da un resultado negativo
    @Test
    public void testAdditionNegativeResult() {
        String formula = "-3+1";
        String resultado = Operations.Solve(formula);
        assertEquals("-3+1=-2", resultado, "El resultado de -3+1 debería ser -2");
    }

    // Prueba para sustracción que da un resultado negativo
    @Test
    public void testSubtractionNegativeResult() {
        String formula = "3-5";
        String resultado = Operations.Solve(formula);
        assertEquals("3-5=-2", resultado, "El resultado de 3-5 debería ser -2");
    }

    // Prueba para multiplicación que da un resultado negativo
    @Test
    public void testMultiplicationNegativeResult() {
        String formula = "-2*3";
        String resultado = Operations.Solve(formula);
        assertEquals("-2*3=-6", resultado, "El resultado de -2*3 debería ser -6");
    }

    // Prueba para división que da un resultado negativo
    @Test
    public void testDivisionNegativeResult() {
        String formula = "-6/2";
        String resultado = Operations.Solve(formula);
        assertEquals("-6/2=-3", resultado, "El resultado de -6/2 debería ser -3");
    }

    // Prueba adicional para división que da un resultado negativo (dividiendo por un número negativo)
    @Test
    public void testDivisionNegativeDivisorResult() {
        String formula = "6/-2";
        String resultado = Operations.Solve(formula);
        assertEquals("6/-2=-3", resultado, "El resultado de 6/-2 debería ser -3");
    }
     @Test
    public void testComplexNegativeResult() {
        String formula = "2+3*-5/5-10";
        String resultado = Operations.Solve(formula);
        assertEquals("2+3*-5/5-10=-13", resultado, "El resultado de 2+3*-5/5-10 debería ser -13");
    }
    @Test
    public void testComplexOperationWithDivisionByZero() {
        String formula = "5+3*10/0-2";
        
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Operations.Solve(formula);
        });

        String expectedMessage = "/ by zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Debería lanzar una ArithmeticException debido a la división por 0");
    }
    @Test
    public void testSolveSubtractionWithNegativeNumbers() {
        String formula = "-2-2";
        String resultado = Operations.Solve(formula);
        assertEquals("-2-2=-4", resultado, "El resultado de -2-2 debería ser -4");
    }

    @Test
    public void testSolveMultiplicationWithNegativeNumbers() {
        String formula = "-2*-3";
        String resultado = Operations.Solve(formula);
        assertEquals("-2*-3=6", resultado, "El resultado de -2*-3 debería ser 6");
    }

    @Test
    public void testSolveDivisionWithNegativeNumbers() {
        String formula = "-6/-3";
        String resultado = Operations.Solve(formula);
        assertEquals("-6/-3=2", resultado, "El resultado de -6/-3 debería ser 2");
    }
    @Test
    public void testAdditionOverflow() {
        String formula = "2147483647+1";  // Esto excede el rango de int
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Operations.Solve(formula);
        });
        assertTrue(exception.getMessage().contains("integer overflow"), "Debería lanzar una ArithmeticException debido al desbordamiento de enteros");
    }
    @Test
    public void testMultiplicationOverflow() {
        String formula = "46341*46341";  // Esto excede el rango de int
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Operations.Solve(formula);
        });
        assertTrue(exception.getMessage().contains("integer overflow"), "Debería lanzar una ArithmeticException debido al desbordamiento de enteros");
    }
    @Test
    public void testInvalidInput() {
        String formula = "2++3";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Operations.Solve(formula);
        });
        assertTrue(exception.getMessage().contains("Invalid input"), "Debería lanzar una IllegalArgumentException debido a la entrada no válida");
    }

}
