package by.epam.training.races;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Member implements Runnable{
    private static final Logger logger = LogManager.getLogger();
    private static final int RANDOM_END_BORDER = 10;
    private CyclicBarrier gate;
    private int speed;
    private int distanceToFinish;
    private int time;

    public Member(CyclicBarrier gate, int distanceToFinish) {
        this.speed = random();
        this.distanceToFinish = distanceToFinish;
        this.gate = gate;
        this.time = 0;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " an the start");
        try {
            gate.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        while (distanceToFinish > 0){
            distanceToFinish -= speed;
            time++;
            if (distanceToFinish <= 0){
                logger.info(String.format("Racer on -> %s; finished the race; past time = %d seconds" ,
                        Thread.currentThread().getName(), time));
            }else {
                logger.info(String.format("Racer on -> %s; distance to finish -> %d; past time = %d seconds" ,
                        Thread.currentThread().getName(), distanceToFinish, time));
            }

        }
    }

    private synchronized static int random(){
        int max = RANDOM_END_BORDER;
        int random = (int) (Math.random() * ++max);
        if (random != 0) return random;
        else return random();
    }
}
