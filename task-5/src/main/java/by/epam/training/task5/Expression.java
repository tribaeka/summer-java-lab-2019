package by.epam.training.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Expression {
    private String defaultExpression;
    private String[] results;
    private static List<Integer> combo = new ArrayList<>();
    public Expression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
        this.results = null;
    }

    public int[] solveExpression(){
        String[] expression = defaultExpression.split(" ");
        //int sum = Integer.parseInt(expression[0]);
        int[] elements = new int[expression.length - 1];
        for (int i = 1; i < expression.length; i++){
            elements[i - 1] = Integer.parseInt(expression[i]);
        }

        return elements;
    }


}
