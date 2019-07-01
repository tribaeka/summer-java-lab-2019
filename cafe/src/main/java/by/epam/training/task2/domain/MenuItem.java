package by.epam.training.task2.domain;

public abstract class MenuItem {
    private String name;
    private int cost;

    public MenuItem(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void addCost(int addedCost){
        this.cost += addedCost;
    }

    @Override
    public String toString() {
        return name + ";" + Utilites.toUSD(cost);
    }
}
