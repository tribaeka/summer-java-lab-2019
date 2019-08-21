package by.epam.training.task7.basicCAndP;

public class Task {
    private int number;
    public Task(int number) {
        this.number = number;
    }

    public int getNumberOfAction() {
        return number;
    }

    public void setNumberOfAction(int number) {
        this.number = number;
    }

    public void doAction(){
        System.out.println("In "+ Thread.currentThread().getName() +" pow " + number
                + " to 2 action result -> " + (int) Math.pow(number, 2));
    }
}
