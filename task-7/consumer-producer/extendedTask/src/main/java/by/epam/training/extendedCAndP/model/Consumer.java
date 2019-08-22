package by.epam.training.extendedCAndP.model;

import by.epam.training.extendedCAndP.model.model.TaskBoard;

public class Consumer implements Runnable {
    private TaskBoard taskBoard;

    public Consumer(TaskBoard taskBoard) {
        this.taskBoard = taskBoard;
    }

    @Override
    public void run() {
        try {
            taskBoard.consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
