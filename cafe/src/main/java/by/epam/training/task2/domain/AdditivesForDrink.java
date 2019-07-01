package by.epam.training.task2.domain;

public enum AdditivesForDrink {
    SUGAR(10),
    MILK(15),
    CHOCOLATE(20);

    private int cost;

    AdditivesForDrink(int cost){
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
