package by.epam.training.task5;

import java.util.*;

public class Expression {
    private String defaultExpression;
    private Set<String> results;
    private static int[] iArr = new int[100];
    private static Set<String> splittingSet = new HashSet<>();
    public Expression(){

    }
    public Expression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
        this.results = solveExpression();
    }

    public Set<String> solveExpression(){
        String[] expression = defaultExpression.split(" ");
        int sum = Integer.parseInt(expression[0]);
        List<String> elements = new LinkedList<>(Arrays.asList(expression).subList(1, expression.length));
        Set<String> answer = new HashSet<>();
        calcSplitting(sum, sum, 0);
        for (String splitting : splittingSet){
            for (String element : elements){
                if (containsOnly(splitting, element)){
                    answer.add(splitting.trim());
                }
            }
        }
        return answer;
    }
    
    public boolean containsOnly(String line, String item){
        String[] withoutSpacesLine = line.split(" ");
        boolean triger = true;
        for (String lineItem : withoutSpacesLine){
            if (!lineItem.equals(item)){
                triger = false;
            }
        }
        return triger;
    }
    
    public void calcSplitting(int n, int k, int i) {

        if ( n < 0 ) return;
        if ( n == 0 ) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < i; j++) {
                stringBuilder.append(iArr[j]).append(" ");
            }
            splittingSet.add(stringBuilder.toString());
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

    public Set<String> getResults() {
        return results;
    }

    private List<Integer> parseStringToIntegerList(List<String> list){
        List<Integer> integerList = new ArrayList<>();
        for (String item : list){
            integerList.add(Integer.parseInt(item));
        }
        return integerList;
    }

    public Set<String> getSplittingList() {
        return splittingSet;
    }
}
