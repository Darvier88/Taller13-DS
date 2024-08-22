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
        String result = Operations.Solve("2+3");
        assertEquals("La solución para 2+3 debe ser 5", "2+3=5", result);
    }

    @Test
    public void testSolveSimpleSubtraction() {
        String result = Operations.Solve("7-4");
        assertEquals("La solución para 7-4 debe ser 3", "7-4=3", result);
    }

    @Test
    public void testSolveSimpleMultiplication() {
        String result = Operations.Solve("3*4");
        assertEquals("La solución para 3*4 debe ser 12", "3*4=12", result);
    }

    @Test
    public void testSolveSimpleDivision() {
        String result = Operations.Solve("8/2");
        assertEquals("La solución para 8/2 debe ser 4", "8/2=4", result);
    }

    @Test
    public void testSolveComplexExpression() {
        String formula = "2+3*4";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 2+3*4 debería ser 14", "2+3*4=14", resultado);
    }

     @Test
    public void testSolveDivisionByZero() {
        String formula = "10/0";
        try {
            Operations.Solve(formula);
        } catch (ArithmeticException e) {
            assertTrue("Dividir por cero debería lanzar una ArithmeticException", 
                       e.getMessage().contains("by zero"));
            return;
        }
        // Si no se lanza la excepción, falla la prueba
        fail("Se esperaba una ArithmeticException pero no se lanzó ninguna");
    }
    @Test
    public void testSolveAdditionWithZero() {
        String formula = "0+5";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 0+5 debería ser 5", "0+5=5", resultado);
    }

    @Test
    public void testSolveSubtractionWithZero() {
        String formula = "0-3";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 0-3 debería ser -3", "0-3=-3", resultado);
    }

    @Test
    public void testSolveMultiplicationWithZero() {
        String formula = "0*7";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 0*7 debería ser 0", "0*7=0", resultado);
    }

    // Prueba para adición que da un resultado negativo
    @Test
    public void testAdditionNegativeResult() {
        String formula = "-3+1";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de -3+1 debería ser -2", "-3+1=-2", resultado);
    }

    // Prueba para sustracción que da un resultado negativo
    @Test
    public void testSubtractionNegativeResult() {
        String formula = "3-5";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 3-5 debería ser -2", "3-5=-2", resultado);
    }

    // Prueba para multiplicación que da un resultado negativo
    @Test
    public void testMultiplicationNegativeResult() {
        String formula = "-2*3";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de -2*3 debería ser -6", "-2*3=-6", resultado);
    }

    // Prueba para división que da un resultado negativo
    @Test
    public void testDivisionNegativeResult() {
        String formula = "-6/2";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de -6/2 debería ser -3", "-6/2=-3", resultado);
    }

    // Prueba adicional para división que da un resultado negativo (dividiendo por un número negativo)
    @Test
    public void testDivisionNegativeDivisorResult() {
        String formula = "6/-2";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 6/-2 debería ser -3", "6/-2=-3", resultado);
    }

    @Test
    public void testComplexNegativeResult() {
        String formula = "2+3*-5/5-10";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de 2+3*-5/5-10 debería ser -11", "2+3*-5/5-10=-11", resultado);
    }

    @Test
    public void testComplexOperationWithDivisionByZero() {
        String formula = "5+3*10/0-2";

        try {
            Operations.Solve(formula);
            fail("Se esperaba una ArithmeticException debido a la división por 0, pero no se lanzó ninguna excepción.");
        } catch (ArithmeticException e) {
            String expectedMessage = "by zero";
            String actualMessage = e.getMessage();
            assertTrue("Debería lanzar una ArithmeticException debido a la división por 0",
                       actualMessage.contains(expectedMessage));
        }
    }

    @Test
    public void testSolveSubtractionWithNegativeNumbers() {
        String formula = "-2-2";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de -2-2 debería ser -4", "-2-2=-4", resultado);
    }

    @Test
    public void testSolveMultiplicationWithNegativeNumbers() {
        String formula = "-2*-3";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de -2*-3 debería ser 6", "-2*-3=6", resultado);
    }

    @Test
    public void testSolveDivisionWithNegativeNumbers() {
        String formula = "-6/-3";
        String resultado = Operations.Solve(formula);
        assertEquals("El resultado de -6/-3 debería ser 2", "-6/-3=2", resultado);
    }

    @Test
    public void testAdditionOverflow() {
        String formula = "2147483647+1";  // Esto excede el rango de int
        try {
            Operations.Solve(formula);
            fail("Se esperaba una ArithmeticException debido al desbordamiento de enteros, pero no se lanzó ninguna excepción.");
        } catch (ArithmeticException e) {
            assertTrue("Debería lanzar una ArithmeticException debido al desbordamiento de enteros",
                       e.getMessage().contains("integer overflow"));
        }
    }

    @Test
    public void testMultiplicationOverflow() {
        String formula = "46341*46341";  // Esto excede el rango de int
        try {
            Operations.Solve(formula);
            fail("Se esperaba una ArithmeticException debido al desbordamiento de enteros, pero no se lanzó ninguna excepción.");
        } catch (ArithmeticException e) {
            assertTrue("Debería lanzar una ArithmeticException debido al desbordamiento de enteros",
                       e.getMessage().contains("integer overflow"));
        }
    }

    @Test
    public void testInvalidInput() {
        String formula = "2++3";
        try {
            Operations.Solve(formula);
            fail("Se esperaba una IllegalArgumentException debido a la entrada no válida, pero no se lanzó ninguna excepción.");
        } catch (IllegalArgumentException e) {
            assertTrue("Debería lanzar una IllegalArgumentException debido a la entrada no válida",
                       e.getMessage().contains("Invalid input"));
        }
    }

}
