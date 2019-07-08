package by.epam.training.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.training.task3.CalcOperations.*;

public class Expression {
    private final static String SOLVING_SUPPORT_REGEX = "(\\d+\\.\\d+|\\d+)";
    private final static String NUMBERS_SPLIT_REGEX = "[/*+\\-]";
    private final static String HIGH_PRIORITY_REGEX = "(\\d+\\.\\d+|\\d+)[*/](\\d+\\.\\d+|\\d+)";
    private final static String LOW_PRIORITY_REGEX = "(\\d+\\.\\d+|\\d+)[+-](\\d+\\.\\d+|\\d+)";
    private static int ID_GENERATOR = 1;

    private int id;
    private String defaultExpression;
    private double result;
    private int steps;
    private Map<Integer, String> stepsMap;
    private boolean correct = true;

    public Expression(String line) {
        this.id = ID_GENERATOR;
        ID_GENERATOR++;
        this.steps = 0;
        this.stepsMap = new HashMap<>();
        this.defaultExpression = line.replaceAll("\\s+","");
        stepsMap.put(steps, defaultExpression);
        try {
            this.result = solveExpression(defaultExpression);
        }catch (NumberFormatException ex){
            correct = false;
        }

    }

    public String getDefaultExpression() {
        return defaultExpression;
    }

    public void setDefaultExpression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    private void addStep(String state){
        steps++;
        stepsMap.put(steps, state);
    }

    public Map<Integer, String> getStepsMap() {
        return stepsMap;
    }

    public void setStepsMap(Map<Integer, String> stepsMap) {
        this.stepsMap = stepsMap;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public double solveExpression(String expression) {
        while (!expression.matches(SOLVING_SUPPORT_REGEX)){
            expression = calcBrackets(expression);
            expression = highPrioritySubExpression(expression);
            expression = lowPrioritySubExpression(expression);
        }
        return Double.parseDouble(expression);
    }

    private double calcOperation(String subLine){
        addStep(subLine);
        String operation = null;
        Pattern pattern = Pattern.compile(NUMBERS_SPLIT_REGEX);
        Matcher matcher = pattern.matcher(subLine);
        if (matcher.find()){
            operation = matcher.group();
        }
        String[] numbers = subLine.split(NUMBERS_SPLIT_REGEX);

        return CalcOperations.getResultOfOperation(numbers, operation);
    }

    private String calcBrackets(String expression){
        if (expression.contains("(") && expression.contains(")")){
            int startIndex = expression.indexOf("(");
            int endIndex = expression.indexOf(")");
            String priorityExpr = expression.substring(startIndex + 1, endIndex);
            return expression.replace(expression.substring(startIndex, endIndex + 1),
                    String.valueOf(calcOperation(priorityExpr)));
        }
        return expression;
    }

    private String highPrioritySubExpression(String expression) {
        if (expression.contains(MULTIPLICATION) || expression.contains(DIVISION)){
            return rearrangeSubExpression(expression, HIGH_PRIORITY_REGEX);
        }
        return expression;
    }

    private String lowPrioritySubExpression(String expression) {
        if (expression.contains(PLUS) || expression.contains(MINUS)){
            return rearrangeSubExpression(expression, LOW_PRIORITY_REGEX);
        }
        return expression;
    }

    private String rearrangeSubExpression(String expression, String regexp){
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()){
            return expression.replace(expression.substring(matcher.start(), matcher.end()),
                    String.valueOf(calcOperation(matcher.group())));
        }else {
            throw new NumberFormatException();
        }
    }
    @Override
    public String toString() {
        if (correct){
            if (result == Math.round(result)){
                return String.format("%d. %-3dsteps;%-4dresult; %s", id, steps, (int) result, defaultExpression);
            }else {
                return String.format("%d. %-3dsteps;%-#4.2fresult; %s", id, steps, result, defaultExpression);
            }

        }else {
            return String.format("%d. Incorrect expression", id);
        }
    }
}
