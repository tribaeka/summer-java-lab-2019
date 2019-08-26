package by.epam.training.task7.basicCAndP.model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskBoard {
    private final static int RANDOM_END_BORDER = 100;
    private final static int NUMBER_OF_OPERATIONS = 100;
    private Queue<Task> board;
    private AtomicInteger operationsCounter;
    private int size;

    public TaskBoard(int size) {
        this.board = new ArrayDeque<>();
        this.size = size;
        this.operationsCounter = new AtomicInteger(0);
    }

    public void produce() throws InterruptedException {

        while (allConsumesIsNotDone(operationsCounter)) {
            synchronized (this) {
                while (board.size() >= size) {
                    wait();
                }
                board.add(new Task(random()));
                notify();
                Thread.sleep(250);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (operationsCounter.getAndIncrement() < NUMBER_OF_OPERATIONS) {
            synchronized (this) {
                while (board.size() == 0) {
                    wait();
                }
                board.poll().doAction();
                notify();
                Thread.sleep(250);
            }
        }
    }

    private synchronized static int random(){
        int max = RANDOM_END_BORDER;
        return (int) (Math.random() * ++max);
    }

    private static boolean allConsumesIsNotDone(AtomicInteger operationsCounter){
        return operationsCounter.get() < NUMBER_OF_OPERATIONS;
    }
}
