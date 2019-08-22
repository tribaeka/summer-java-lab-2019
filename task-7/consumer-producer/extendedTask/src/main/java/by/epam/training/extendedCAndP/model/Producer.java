package by.epam.training.extendedCAndP.model;

import by.epam.training.extendedCAndP.model.model.TaskBoard;

public class Producer implements Runnable {
    private TaskBoard taskBoard;

    public Producer(TaskBoard taskBoard) {
        this.taskBoard = taskBoard;
    }

    @Override
    public void run() {
        try {
            taskBoard.produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
