package by.epam.training.task5;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class ExpressionTest {

    @Test
    public void basicSolveExpression() {
        Expression expression = new Expression("6 3 2");
        assertArrayEquals(new int[]{3,2}, expression.solveExpression());
    }
}
