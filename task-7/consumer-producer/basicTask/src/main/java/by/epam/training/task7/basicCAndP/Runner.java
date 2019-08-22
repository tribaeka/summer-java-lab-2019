package by.epam.training.task7.basicCAndP;

import by.epam.training.task7.basicCAndP.model.TaskBoard;

import java.util.Scanner;
import java.util.stream.Stream;


public class Runner {
    public static void main(String[] args){
        final int numberOfConsumer;
        final int numberOfProducer;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of consumer:");
        numberOfConsumer = scanner.nextInt();
        System.out.println("Number of producer:");
        numberOfProducer = scanner.nextInt();
        scanner.close();

        TaskBoard pool = new TaskBoard(
                Stream.of(numberOfConsumer, numberOfProducer)
                .max(Integer::compareTo)
                .get()
        );
        for (int i = 0; i < numberOfProducer; i++) {
            new Thread(new Producer(pool)).start();
        }

        for (int i = 0; i < numberOfConsumer; i++) {
            new Thread(new Consumer(pool)).start();

        }
    }
}