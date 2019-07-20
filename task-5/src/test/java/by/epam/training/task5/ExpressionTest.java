package by.epam.training.task5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTest {
    private Expression expression;
    @Before
    public void init(){
        expression = new Expression("6 3 2");
    }
    @Test
    public void basicSolveExpression() {
        assertArrayEquals(new int[]{3,2}, expression.solveExpression());
    }

    @Test
    public void basicCalcCombination() {
        assertEquals(3, expression.calcCombination(new String[]{"1","2"}));
    }
}
