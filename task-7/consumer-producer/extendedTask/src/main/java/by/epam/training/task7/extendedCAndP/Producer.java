package by.epam.training.task7.extendedCAndP;

import by.epam.training.task7.extendedCAndP.model.TaskBoard;

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
