package by.epam.training.task2.domain;


import java.util.ArrayList;
import java.util.List;

public class Order {

    private boolean active;
    private List<MenuItem> itemsOfOrder;

    public Order(){
        this.itemsOfOrder = new ArrayList<>();
        this.active = false;

    }

    public Order(List<MenuItem> list){
        this.itemsOfOrder = list;
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<MenuItem> getItemsOfOrder() {
        return itemsOfOrder;
    }

    public void setItemsOfOrder(List<MenuItem> itemsOfOrder) {
        this.itemsOfOrder = itemsOfOrder;
    }

    public void addItemToOrder(MenuItem item){
        System.out.println("adding new item -> "+ item);
        this.itemsOfOrder.add(item);
    }

    private int getTotalCost(){
        int totalCost = 0;

        for (MenuItem item : itemsOfOrder){
            totalCost += item.getCost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return "Oder list: " + itemsOfOrder + "\nTotal cost :" + Utilites.toUSD(getTotalCost());
    }
}
