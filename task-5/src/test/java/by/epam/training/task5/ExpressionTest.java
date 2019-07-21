package by.epam.training.task5;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExpressionTest {
    private Expression expression;
    @Before
    public void init(){
        expression = new Expression();
    }

    @Test
    public void basicCalcSplitting(){
        List<String> expected = new LinkedList<>(Arrays.asList(
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

}
