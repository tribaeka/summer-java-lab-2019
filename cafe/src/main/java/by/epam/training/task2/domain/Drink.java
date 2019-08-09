package by.epam.training.task2.domain;

import static by.epam.training.task2.domain.AdditivesForDrink.*;

public class Drink extends MenuItem {

    public Drink(String name, int cost){
        super(name, cost);
    }

    public Drink addSugar(){
        this.addCost(SUGAR.getCost());
        return this;
    }
    public Drink addMilk(){
        this.addCost(MILK.getCost());
        return this;
    }
    public Drink addChocolate(){
        this.addCost(CHOCOLATE.getCost());
        return this;
    }
}
