package by.epam.training.task5;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class ExpressionTest {
    private Expression expression;
    @Before
    public void init(){
        expression = new Expression("6 3 2");
    }

    @Test
    public void basicCalcSplitting(){
        Set<String> expected = new HashSet<>(Arrays.asList(
                "6 ",
                "5 1 ",
                "4 2 ",
                "4 1 1 ",
                "3 3 ",
                "3 2 1 ",
                "3 1 1 1 ",
                "2 2 2 ",
                "2 2 1 1 ",
                "2 1 1 1 1 ",
                "1 1 1 1 1 1 "));
        expression.calcSplitting(6, 6 ,0);
        assertThat(expression.getSplittingList(), is(expected));

    }

    @Test
    public void solveExpression(){
        assertThat(expression.solveExpression(), containsInAnyOrder("2 2 2", "3 3"));
    }

    @Test
    public void baseContainsOnly(){
        assertTrue(expression.containsOnly("1 1 1 1 1 1 1 1 ", "1"));
    }
}
