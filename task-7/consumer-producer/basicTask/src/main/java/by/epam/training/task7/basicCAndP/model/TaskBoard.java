package by.epam.training.task7.basicCAndP.model;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskBoard {
    private final static int RANDOM_END_BORDER = 100;
    private Queue<Task> board;
    private int size;
    public TaskBoard(int size) {
        this.board = new ArrayDeque<>();
        this.size = size;
    }

    public void produce() throws InterruptedException {

        while (true) {
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
        while (true) {
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
}
