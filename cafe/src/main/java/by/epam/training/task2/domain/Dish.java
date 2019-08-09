package by.epam.training.task2.domain;

public class Dish extends MenuItem {

    private boolean chefsDish;

    public Dish(String name, int cost){
        super(name, cost);
        this.chefsDish = false;
    }
    public Dish(String name, int cost, boolean chefsDish){
        super(name, cost);
        this.chefsDish = chefsDish;
    }

    private boolean isChefsDish() {
        return chefsDish;
    }

    public void setChefsDish(boolean chefsDish) {
        this.chefsDish = chefsDish;
    }

    @Override
    public int getCost() {
        if (isChefsDish()){
            super.addCost(150);
        }
        return super.getCost();
    }

    @Override
    public String toString() {
        if (isChefsDish()){
            return super.toString() + ";chef's dish";
        }
        return super.toString();
    }
}
