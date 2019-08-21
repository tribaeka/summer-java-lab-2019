package by.epam.training.extendedCAndP.model;

import by.epam.training.extendedCAndP.model.model.TaskBoard;

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

        TaskBoard buffer = new TaskBoard(
                Stream.of(numberOfConsumer, numberOfProducer)
                .max(Integer::compareTo)
                .get()
        );
        for (int i = 0; i < numberOfProducer; i++) {
            Thread thread = new Thread(() -> {
                try {
                    buffer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        for (int i = 0; i < numberOfConsumer; i++) {
            Thread thread = new Thread(() -> {
                try {
                    buffer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        }
    }
}