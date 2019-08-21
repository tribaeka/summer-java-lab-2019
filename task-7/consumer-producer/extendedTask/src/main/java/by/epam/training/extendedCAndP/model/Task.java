package by.epam.training.extendedCAndP.model;

public class Task {
    protected static final int DEFAULT_NUMBER_OF_ACTION = 5;
    private int numberOfAction;

    public Task(int numberOfAction) {
        this.numberOfAction = numberOfAction;
    }

    public int getNumberOfAction() {
        return numberOfAction;
    }

    public void setNumberOfAction(int numberOfAction) {
        this.numberOfAction = numberOfAction;
    }

    public void doAction(){
        System.out.println("do nothing");
    }
    @Override
    public String toString() {
        return "Task{" +
                "numberOfAction=" + numberOfAction +
                '}';
    }
}
