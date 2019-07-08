package by.epam.training.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        List<Expression> expressions = new ArrayList<>();
        expressions.add(null);
        try(Scanner fileScanner = new Scanner(new File(args[0]))) {
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                Expression expression = new Expression(line);
                expressions.add(expression);
                System.out.println(expression);
            }

            Scanner consoleScanner = new Scanner(System.in);
            int id = consoleScanner.nextInt();
            int step = consoleScanner.nextInt();
            String stepValue = expressions.get(id).getStepsMap().get(step);
            if (stepValue != null){
                System.out.println(id + ". " + stepValue);
            }else {
                System.out.println(id + ". Incorrect value");
            }

        }catch (FileNotFoundException ex){
            System.out.println("Incorrect path");
        }


    }
}
