package by.epam.training.extendedCAndP;

import by.epam.training.task7.basicCAndP.model.Task;

import java.util.List;

import static by.epam.training.task7.basicCAndP.Runner.HALF_POOL_SIZE;

public class Producer implements Runnable{
    private List<Task> produceList;

    public Producer(List<Task> taskList) {
        this.produceList = taskList;
    }

    @Override
    public void run() {
        while (produceList.size() <= HALF_POOL_SIZE){
            produceList.add();
        }
    }
}
