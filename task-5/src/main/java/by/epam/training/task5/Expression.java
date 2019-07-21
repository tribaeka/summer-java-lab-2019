package by.epam.training.task5;

import java.util.*;

public class Expression {
    private String defaultExpression;
    private Set<String> results;
    private int sum;
    private String errorString = "";
    private static int[] iArr = new int[100];
    private static Set<String> splittingSet = new HashSet<>();
    public Expression(){

    }
    public Expression(String defaultExpression) {
        splittingSet.clear();
        this.defaultExpression = defaultExpression;
        this.sum = createSum();
        this.results = solveExpression();
    }

    private int createSum(){
        String[] expression = defaultExpression.split(" ");
        return Integer.parseInt(expression[0]);
    }

    public Set<String> solveExpression(){
        String[] expression = defaultExpression.split(" ");
        List<String> elements = new LinkedList<>(Arrays.asList(expression).subList(1, expression.length));
        calcSplitting(sum, sum, 0);
        Set<String> solve = restoreFromMoreElements(elements);
        if (solve.size() == 0){
            errorString = "Error: No one way";
        }
        if (elements.size() == 0){
            errorString = "Error: There are not coins";
        }
        return solve;
    }

    public Set<String> restoreFromMoreElements(List<String> elements){
        Set<String> answer = new HashSet<>();
        for (String splitting : splittingSet){
            Set<String> deeperSplittingSet = new HashSet<>(Arrays.asList(splitting.split(" ")));
            if (!Collections.disjoint(deeperSplittingSet, elements)){
                answer.add(splitting);
            }
        }
        Set<String> badAnswers = new HashSet<>();
        for (String answerItem : answer){
            for (String answerElement : answerItem.split(" ")){
                if (Collections.disjoint(Collections.singleton(answerElement), elements)){
                    badAnswers.add(answerItem);
                }
            }
        }
        answer.removeAll(badAnswers);
        answer.removeIf(value -> calcCombination(value) != sum);

        return answer;
    }


        public int calcCombination(String combination) {
            int result = 0;
            for (String item : combination.split(" ")) {
                result += Integer.parseInt(item);
            }
            return result;
        }

            public void calcSplitting(int n, int k, int i) {

        if ( n < 0 ) return;
        if ( n == 0 ) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < i; j++) {
                stringBuilder.append(iArr[j]).append(" ");
            }
            splittingSet.add(stringBuilder.toString().trim());
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

    public int getSum() {
        return sum;
    }

    public String getErrorString() {
        return errorString;
    }

    private List<Integer> parseStringToIntegerList(List<String> list){
        List<Integer> integerList = new ArrayList<>();
        for (String item : list){
            integerList.add(Integer.parseInt(item));
        }
        return integerList;
    }

    public static Set<String> getSplittingSet() {
        return splittingSet;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(defaultExpression);
        if (!errorString.isEmpty()){
            stringBuilder.append("\n").append(errorString);
        }else {
            for (String item : results){
                stringBuilder.append("\n").append(item);
            }
        }

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
