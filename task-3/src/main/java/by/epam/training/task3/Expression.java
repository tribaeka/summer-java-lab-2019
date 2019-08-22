package by.epam.training.task3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.training.task3.CalcOperations.*;

public class Expression {
    private final static String SOLVING_SUPPORT_REGEX = "(-?\\d+\\.\\d+|-?\\d+)";
    private final static String NUMBERS_SPLIT_REGEX = "[/*+\\-]";
    private final static String HIGH_PRIORITY_REGEX = "(\\d+\\.\\d+|\\d+)[*/](\\d+\\.\\d+|\\d+)";
    private final static String LOW_PRIORITY_REGEX = "(\\d+\\.\\d+|\\d+)[+-](\\d+\\.\\d+|\\d+)";
    private final static String INCORRECT_OPERATORS = "\\+|\\+\\*|-\\*|\\*-|\\*{2,}|/{2,}|\\*/|/\\*";
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
            if (hasNoValidSymbols(defaultExpression)){
                throw new UnsupportedOperationException();
            }else {
                this.result = solveExpression(defaultExpression);
            }

        }catch (NumberFormatException | UnsupportedOperationException ex){
            correct = false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefaultExpression() {
        return defaultExpression;
    }

    public void setDefaultExpression(String defaultExpression) {
        this.defaultExpression = defaultExpression;
    }

    private boolean hasNoValidSymbols(String expression){
        Set<String> noValidSet = new HashSet<>(Arrays.asList("^", "$", "!", "@", "#", "%", "&", "|", "\\", "/0"));
        for (String symbol : noValidSet){
            if (expression.contains(symbol)){
                return true;
            }
        }
        return false;
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
            if (isContainsBrackets(expression)){
                expression = calcBrackets(expression);
            }else {
                expression = highPrioritySubExpression(expression);
                expression = lowPrioritySubExpression(expression);
            }
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
        if (isContainsBrackets(expression)){
            if (expression.replaceAll("\\)", "").length()
                    != expression.replaceAll("\\(", "").length()){
                throw new UnsupportedOperationException();
            }else {
                int startIndex = expression.indexOf("(");
                int endIndex = getLastIndexOfCharRetitions(expression, ")");
                String calcBracketsPart = expression.substring(startIndex, endIndex).replaceAll("[()]", "");
                return expression.replace(expression.substring(startIndex, endIndex+1),
                        String.valueOf(solveExpression(calcBracketsPart)));
            }
        }
        return expression;
    }

    private int getLastIndexOfCharRetitions(String expression, String item){
        int index = expression.indexOf(item);
        if (index != expression.length() - 1){
            while (expression.charAt(index)
                    == expression.charAt(index + 1)){
                index++;
                if (index == expression.length() - 1){
                    break;
                }
            }
        }
        return index;
    }

    private boolean isContainsBrackets(String expression){
        return expression.contains("(") && expression.contains(")");
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
        expression = doubleOperatorProtection(expression);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()){
            return expression.replace(expression.substring(matcher.start(), matcher.end()),
                    String.valueOf(calcOperation(matcher.group())));
        }else {
            throw new UnsupportedOperationException();
        }
    }

    private String doubleOperatorProtection(String expression){
        if (expression.matches(INCORRECT_OPERATORS)){
            throw new UnsupportedOperationException();
        }else if (expression.contains(DOUBLE_MINUS)){
            expression = expression.replace(DOUBLE_MINUS, PLUS);
        }else if (expression.contains(DOUBLE_PLUS)){
            expression = expression.replace(DOUBLE_PLUS, PLUS);
        }else if (expression.contains(PLUS_MINUS)){
            expression = expression.replace(PLUS_MINUS, MINUS);
        }else if (expression.contains(MINUS_PLUS)){
            expression = expression.replace(MINUS_PLUS, MINUS);
        }
        if (expression.contains(DOUBLE_MINUS) || expression.contains(DOUBLE_PLUS)
                || expression.contains(PLUS_MINUS) || expression.contains(MINUS_PLUS)){
            expression = doubleOperatorProtection(expression);
        }
        return expression;
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

    public void printStep(int step){
        String stepValue = stepsMap.get(step);
        if (stepValue != null){
            System.out.println(id + ". " + stepValue);
        }else {
            System.out.println(id + ". Incorrect value");
        }
    }
}
