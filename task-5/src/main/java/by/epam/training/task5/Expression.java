package by.epam.training.task5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Expression {
    private String defaultExpression;
    private List<List<Integer>> results;

    public Expression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
        this.results = solveExpression();
    }

    public List<List<Integer>> solveExpression(){
        List<List<Integer>> solution = new ArrayList<>();
        String[] expression = defaultExpression.split(" ");
        int sum = Integer.parseInt(expression[0]);
        List<String> elements = new LinkedList<>();
        for (int i = expression.length - 1; i > 0; i--){
            if (!restoreFromOneElement(expression[i], sum).isEmpty()){
                solution.add(restoreFromOneElement(expression[i], sum));
            }
        }
        if (expression.length > 2){
            for (int i = expression.length - 1; i > 0; i--){
                elements.add(expression[i]);
                if (elements.size() > 1){

                }
            }
        }
        return solution;

    }

    public int calcCombination(List<String> combination){
        if (combination.size() == 1) throw new UnsupportedOperationException();
        int result = 0;
        for (String item : combination){
            result += Integer.parseInt(item);
        }
        return result;
    }

    public List<Integer> restoreFromOneElement(String element, int sum){
        List<Integer> result = new LinkedList<>();
        int tempSum = 0;
        int item = Integer.parseInt(element);
        while (tempSum < sum){
            tempSum += item;
            result.add(item);
        }
        if (tempSum == sum){
            return result;
        }else {
            result.clear();
            return result;
        }
    }

    public List<Integer> restoreFromMoreElements(List<String> elements, int sum){
        List<Integer> result = new LinkedList<>();
        int tempSum = 0;
        return null;
    }

    public String getDefaultExpression() {
        return defaultExpression;
    }

    public List<List<Integer>> getResults() {
        return results;
    }
}
