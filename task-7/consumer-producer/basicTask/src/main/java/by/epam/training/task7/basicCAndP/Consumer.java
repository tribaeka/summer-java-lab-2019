package by.epam.training.task7.basicCAndP;

import by.epam.training.task7.basicCAndP.model.Task;


import java.util.Queue;

import static by.epam.training.task7.basicCAndP.Producer.magicSize;
import static by.epam.training.task7.basicCAndP.Runner.POOL_SIZE;

public class Consumer implements Runnable{
    private Queue<Task> consumeQueue;

    public Consumer(Queue<Task> taskList) {
        this.consumeQueue = taskList;
    }
    @Override
    public void run() {
        while (magicSize.get() < POOL_SIZE){
            synchronized (this){
                if (consumeQueue.peek() != null) consumeQueue.poll().doAction();

            }
            Thread.yield();
        }
    }
}
