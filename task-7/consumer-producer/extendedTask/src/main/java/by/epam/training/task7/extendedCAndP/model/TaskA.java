package by.epam.training.task7.extendedCAndP.model;

public class TaskA extends Task {

    public TaskA(int numberOfAction) {
        super(numberOfAction, false);
    }

    @Override
    public boolean doBooleanAction() {
        return getNumber() <= 50;
    }
}
