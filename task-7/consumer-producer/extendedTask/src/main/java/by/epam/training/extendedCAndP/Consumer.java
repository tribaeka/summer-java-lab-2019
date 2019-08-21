package by.epam.training.extendedCAndP;

import by.epam.training.task7.basicCAndP.model.Task;

import java.util.List;

public class Consumer implements Runnable{
    private List<Task> consumeList;

    public Consumer(List<Task> taskList) {
        this.consumeList = taskList;
    }
    @Override
    public void run() {

    }
}
