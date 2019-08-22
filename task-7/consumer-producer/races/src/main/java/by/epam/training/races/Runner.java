package by.epam.training.races;

import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Runner {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final int numberOfMembers;
        final int lengthOfTrack;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of members:");
        numberOfMembers = scanner.nextInt();
        System.out.println("Length of track:");
        lengthOfTrack = scanner.nextInt();
        scanner.close();
        final CyclicBarrier gate = new CyclicBarrier(numberOfMembers);

        for (int i = 0; i < numberOfMembers; i++) {
            new Thread(new Member(gate, lengthOfTrack)).start();
        }
        gate.await();

    }
}
