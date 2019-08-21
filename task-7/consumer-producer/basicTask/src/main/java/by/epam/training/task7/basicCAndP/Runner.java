package by.epam.training.task7.basicCAndP;

import by.epam.training.task7.basicCAndP.model.Task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Runner {
    public final static int POOL_SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        final int numberOfConsumer = 10;
        final int numberOfProducer = 2;
        Queue<Task> pool = new ConcurrentLinkedQueue<>();

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Number of consumer:");
        numberOfConsumer = scanner.nextInt();
        System.out.println("Number of producer:");
        numberOfProducer = scanner.nextInt();*/
        for (int i = 0; i < numberOfProducer; i++) {
            Thread thread = new Thread(new Producer(pool));
            thread.start();
        }
        for (int i = 0; i < numberOfConsumer; i++) {
            Thread thread = new Thread(new Consumer(pool));
            thread.start();
        }
        //System.out.println(pool);
        Thread.sleep(1000);
        System.out.println("Modifications count = " + Task.modificationsCount);
        System.out.println("Produce count = " + Producer.produceCount);
    }


}