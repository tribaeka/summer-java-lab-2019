package by.epam.training.extendedCAndP.model;

public class TaskA extends Task {

    public TaskA(int numberOfAction) {
        super(numberOfAction);
    }

    public boolean isDefaultAction(){
        return getNumberOfAction() == DEFAULT_NUMBER_OF_ACTION;
    }
}
