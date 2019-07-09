package by.epam.training.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        List<Expression> expressions = new ArrayList<>();

        try(Scanner fileScanner = new Scanner(new File(args[0]))) {
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                Expression expression = new Expression(line);
                expressions.add(expression);
                System.out.println(expression);
            }

            Scanner consoleScanner = new Scanner(System.in);
            int id = consoleScanner.nextInt() - 1;
            int step = consoleScanner.nextInt();
            expressions.get(id).printStep(step);
        }catch (FileNotFoundException ex){
            System.out.println("Incorrect path");
        }
    }
}
