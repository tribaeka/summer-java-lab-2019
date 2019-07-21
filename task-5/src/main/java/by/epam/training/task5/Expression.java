package by.epam.training.task5;

import java.util.*;

public class Expression {
    private String defaultExpression;
    private List<List<Integer>> results;

    public Expression(){

    }
    public Expression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
        this.results = null;
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
