package by.epam.training.task5;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class RunTests {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(ExpressionTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }
}
