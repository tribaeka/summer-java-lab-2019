package by.epam.training.extendedCAndP.model.model;

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
        boolean trigger = true;
        while (true) {
            synchronized (this) {
                while (board.size() >= size) {
                    wait();
                }
                if (trigger) {
                    board.add(new TaskA(random()));
                    trigger = false;
                } else {
                    board.add(new TaskB(random()));
                    trigger = true;
                }

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
                Task consumeTask = board.poll();
                if (consumeTask.isVoidAction()) consumeTask.doVoidAction();
                else System.out.println(consumeTask.getNumber() + " is less than 50 ? ->" + consumeTask.doBooleanAction());
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
