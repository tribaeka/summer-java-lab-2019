package by.epam.training.task7.extendedCAndP.model;

public class TaskB extends Task{

    public TaskB(int numberOfAction) {
        super(numberOfAction);
    }

    @Override
    public void doVoidAction() {
        System.out.println("Pow " + getNumber()
                + " to 2 action result -> " + (int) Math.pow(getNumber(), 2));
    }
}