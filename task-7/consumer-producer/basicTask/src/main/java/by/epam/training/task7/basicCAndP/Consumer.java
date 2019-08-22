package by.epam.training.task7.basicCAndP;

import by.epam.training.task7.basicCAndP.model.TaskBoard;

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
