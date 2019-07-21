package by.epam.training.task5;

import java.util.*;

public class Expression {
    private String defaultExpression;
    private List<List<Integer>> results;
    private static int[] iArr = new int[100];
    private static List<String> splittingList = new LinkedList<>();
    public Expression(){

    }
    public Expression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
        this.results = null;
    }

    public String[] solveExpression(){
        String[] expression = defaultExpression.split(" ");
        int sum = Integer.parseInt(expression[0]);
        int[] elements = new int[expression.length - 2];
        return null;
    }
    public void calcSplitting(int n, int k, int i) {

        if ( n < 0 ) return;
        if ( n == 0 ) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < i; j++) {
                stringBuilder.append(iArr[j]).append(" ");
            }
            splittingList.add(stringBuilder.toString());
        }
        else {
            if ( n >= k) {
                iArr[i] = k;
                calcSplitting(n - k, k, i + 1);
            }
            if ( k > 1) calcSplitting(n, k - 1, i);
        }
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

    public List<String> getSplittingList() {
        return splittingList;
    }
}
