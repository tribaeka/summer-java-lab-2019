package by.epam.training.task5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        List<Expression> expressions = new LinkedList<>();
        try(Scanner fileScanner = new Scanner(new File(args[0]))) {
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                if (!line.isEmpty()){
                    expressions.add(new Expression(line));
                }
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

    }
}
