package by.epam.training.task5;

import java.util.*;

public class Expression {
    private String defaultExpression;
    private List<List<Integer>> results;

    public Expression(){

    }
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
                    solution.add(restoreFromMoreElements(elements, sum));
                }
            }
        }
        return solution;

    }

    public int calcCombination(List<Integer> combination){
        if (combination.size() == 1) throw new UnsupportedOperationException();
        int result = 0;
        for (Integer item : combination){
            result += item;
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
        List<Integer> result = new ArrayList<>();
        List<Integer> intElements = parseStringToIntegerList(elements);
        int tempSum = 0;
        if (calcCombination(intElements) == sum){
            return intElements;
        }
        while (elements.size() > 1){
            Integer max = Collections.max(intElements);
            Integer min = Collections.min(intElements);
            if (calcCombination(intElements) < sum){
                intElements.removeIf(value -> value.equals(max));
                if (calcCombination(intElements) == sum){
                    return intElements;
                }
            }
        }


        return null;
    }

    public String getDefaultExpression() {
        return defaultExpression;
    }

    public List<List<Integer>> getResults() {
        return results;
    }

    private List<Integer> parseStringToIntegerList(List<String> list){
        List<Integer> integerList = new ArrayList<>();
        for (String item : list){
            integerList.add(Integer.parseInt(item));
        }
        return integerList;
    }
}
