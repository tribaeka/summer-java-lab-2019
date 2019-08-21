package by.epam.training.extendedCAndP.model;

public class TaskB extends Task{

    public TaskB(int numberOfAction) {
        super(numberOfAction);
    }

    public void printAction(){
        System.out.println("now action is " + getNumberOfAction());
    }
}
