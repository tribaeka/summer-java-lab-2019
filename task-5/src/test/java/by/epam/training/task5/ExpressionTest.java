package by.epam.training.task5;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ExpressionTest {

    @Test
    public void basicSolveExpression() {
        Expression testExpression = new Expression("6 3 2");
        Set<String> expected = new HashSet<>(Arrays.asList(
                "2 2 2",
                "3 3"
        ));
        assertThat(testExpression.solveExpression(), is(expected));
    }

    @Test
    public void noOneWayExpression() {
        Expression testExpression = new Expression("9 4");
        assertTrue(!testExpression.getErrorString().isEmpty());
        assertEquals("Error: No one way", testExpression.getErrorString());
    }

    @Test
    public void noCoinsExpression() {
        Expression testExpression = new Expression("9");
        assertTrue(!testExpression.getErrorString().isEmpty());
        assertEquals("Error: There are not coins", testExpression.getErrorString());
    }

    @Test
    public void restoreFromMoreElements() {
    }

    @Test
    public void calcCombination() {
        Expression testExpression = new Expression("6 3 2");
        Set<String> expected = new HashSet<>(Arrays.asList(
                "6",
                "5 1",
                "4 2",
                "4 1 1",
                "3 3",
                "3 2 1",
                "3 1 1 1",
                "2 2 2",
                "2 2 1 1",
                "2 1 1 1 1",
                "1 1 1 1 1 1"));
        assertThat(Expression.getSplittingSet(), is(expected));
    }

    @Test
    public void oneElementCalcCombination() {
        Expression testExpression = new Expression("6 3 2");
        assertEquals(2, testExpression.calcCombination("2"));
    }

    @Test
    public void moreElementsCalcCombination() {
        Expression testExpression = new Expression("6 3 2");
        assertEquals(7, testExpression.calcCombination("1 1 1 1 1 1 1"));

    }

    @Test
    public void withNullElementsCombination() {
        Expression testExpression = new Expression("6 3 2");
        assertEquals(2, testExpression.calcCombination("0 2 0"));
    }
}
