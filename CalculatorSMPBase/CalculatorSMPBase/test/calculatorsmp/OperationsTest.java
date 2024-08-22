/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package calculatorsmp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author micha
 */
public class OperationsTest {
    
    public OperationsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testMakeFormulaNotNull() {
        String formula = Operations.MakeFormula();
        assertNotNull(formula, "La fórmula generada no debe ser nula");
        
    }
    
    @Test
    public void testSolveSimpleAddition() {
        String result = Operations.Solve("2+3");
        assertEquals("2+3=5", result, "La solución para 2+3 debe ser 5");
    }

    @Test
    public void testSolveSimpleSubtraction() {
        String result = Operations.Solve("7-4");
        assertEquals("7-4=3", result, "La solución para 7-4 debe ser 3");
    }
    
    
    @Test
    public void testSolveSimpleMultiplication() {
        String result = Operations.Solve("3*4");
        assertEquals("3*4=12", result, "La solución para 3*4 debe ser 12");
    }

    @Test
    public void testSolveSimpleDivision() {
        String result = Operations.Solve("8/2");
        assertEquals("8/2=4", result, "La solución para 8/2 debe ser 4");
    }
   
    
}
