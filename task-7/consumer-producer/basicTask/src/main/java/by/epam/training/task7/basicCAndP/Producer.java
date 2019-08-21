package by.epam.training.task7.basicCAndP;

import by.epam.training.task7.basicCAndP.model.Task;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static by.epam.training.task7.basicCAndP.Runner.POOL_SIZE;

public class Producer implements Runnable{
    public final static int RANDOM_END_BORDER = 100;
    public static volatile AtomicInteger magicSize = new AtomicInteger(0);
    public static int produceCount = 0;
    private Queue<Task> produceQueue;

    public Producer(Queue<Task> taskList) {
        this.produceQueue = taskList;
    }

    @Override
    public void run() {
        while (magicSize.getAndIncrement() < POOL_SIZE){
            int i = random();
            System.out.println("producer " + Thread.currentThread().getName() + " add ->" + i);
            produceQueue.add(new Task(i));
            addCount();
            Thread.yield();
        }
    }

    private synchronized static int random(){
        int max = RANDOM_END_BORDER;
        return (int) (Math.random() * ++max);
    }

    private synchronized void addCount(){
        produceCount++;
    }

    public static int getProduceCount() {
        return produceCount;
    }
}
