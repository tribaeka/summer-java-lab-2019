package by.epam.training.task5;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExpressionTest {
    private Expression expression;
    @Before
    public void init(){
        expression = new Expression();
    }

    @Test
    public void basicCalcCombination() {
        assertEquals(3, expression.calcCombination(Arrays.asList(1, 2)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void oneNumberCalcCombination() {
        expression.calcCombination(Collections.singletonList(1));
    }

    @Test
    public void withSevenNumbersCalcCombination() {
        assertEquals(7, expression.calcCombination(Arrays.asList(1, 1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void restoreFromOneElement(){
        assertEquals(Arrays.asList(3, 3), expression.restoreFromOneElement("3", 6));
        assertEquals(Arrays.asList(2, 2, 2), expression.restoreFromOneElement("2", 6));
        assertEquals(Arrays.asList(1, 1, 1, 1, 1, 1), expression.restoreFromOneElement("1", 6));
    }

    @Test
    public void restoreFromMoreElements() {
        assertThat(expression.restoreFromMoreElements(Arrays.asList("1", "2", "3"), 6), is(Arrays.asList(1, 2, 3)));
    }
}
