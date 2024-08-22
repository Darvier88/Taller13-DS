/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package calculatorsmp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author micha
 */
public class OperationsTest {

    @Test
    public void testMakeFormulaNotNull() {
        String formula = Operations.MakeFormula();
        assertNotNull("La fórmula generada no debe ser nula", formula);
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
}
