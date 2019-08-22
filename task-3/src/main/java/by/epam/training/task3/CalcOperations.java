package by.epam.training.task3;


import static java.lang.Double.parseDouble;

public class CalcOperations {
    public final static String PLUS = "+";
    public final static String MINUS = "-";
    public final static String MULTIPLICATION = "*";
    public final static String DIVISION = "/";

    public final static String DOUBLE_PLUS = "++";
    public final static String DOUBLE_MINUS = "--";
    public final static String PLUS_MINUS = "+-";
    public final static String MINUS_PLUS = "-+";

    private enum TypeOfOperations{
        ADDITION{
            double doOperation(String[] arguments){
                return parseDouble(arguments[0]) + parseDouble(arguments[1]);
            }
        },
        SUBTRACTION{
            double doOperation(String[] arguments){
                return parseDouble(arguments[0]) - parseDouble(arguments[1]);
            }
        },
        MULTIPLICATION{
            double doOperation(String[] arguments){
                return parseDouble(arguments[0]) * parseDouble(arguments[1]);
            }
        },
        DIVISION{
            double doOperation(String[] arguments){
                return parseDouble(arguments[0]) / parseDouble(arguments[1]);
            }
        },
        INCORRECT{
            double doOperation(String[] arguments){
                throw new UnsupportedOperationException();
            }
        };

        abstract double doOperation(String[] arguments);
    }

    public static double getResultOfOperation(String[] numbers, String operation){

        return arithmeticToWord(operation).doOperation(numbers);
    }

    private static TypeOfOperations arithmeticToWord(String arithmetic){
        switch (arithmetic){
            case PLUS:
                return TypeOfOperations.ADDITION;
            case MINUS:
                return TypeOfOperations.SUBTRACTION;
            case MULTIPLICATION:
                return TypeOfOperations.MULTIPLICATION;
            case DIVISION:
                return TypeOfOperations.DIVISION;
            default:
                return TypeOfOperations.INCORRECT;
        }
    }
}
