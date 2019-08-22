package by.epam.training.task7.extendedCAndP.model;

public class Task {
    private int number;
    private boolean isVoidAction = true;

    public Task(int number) {
        this.number = number;
    }

    public Task(int number, boolean isVoidAction) {
        this.number = number;
        this.isVoidAction = isVoidAction;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isVoidAction() {
        return isVoidAction;
    }

    public void setVoidAction(boolean voidAction) {
        isVoidAction = voidAction;
    }

    public void doVoidAction(){
    }

    public boolean doBooleanAction(){
        return true;
    }

}
