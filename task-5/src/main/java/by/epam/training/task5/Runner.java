package by.epam.training.task5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    /*public static void main(String[] args) {
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
        for (Expression expression: expressions){
            System.out.println("Expression = "+expression.getDefaultExpression());
            System.out.println("Result = " +expression.getResults());
        }

    }*/
    static int[] iArr = new int[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iVol = sc.nextInt();
        currSlag(iVol, iVol, 0);
    }

    static void currSlag(int n, int k, int i) {

        if ( n < 0 ) return;
        if ( n == 0 ) {
            for (int j = 0; j < i; j++) System.out.print(iArr[j] + " ");
            System.out.println();
        }
        else {
            if ( n >= k) {
                iArr[i] = k;
                currSlag(n - k, k, i + 1);
            }
            if ( k > 1) currSlag(n, k - 1, i);
        }
    }
}
