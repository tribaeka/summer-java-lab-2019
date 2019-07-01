package by.epam.training.task2.builders;

import by.epam.training.task2.domain.Order;

public class Customer {

    private final String name;
    private final String mobilePhone;
    private final String homePhone;
    private final String address;
    private Order order;

    public static class Builder{
        private final String name;
        private final String mobilePhone;


        private String homePhone = "";
        private String address = "";
        private Order order = null;

        public Builder(String name, String mobilePhone){
            System.out.println("\tIn builder constructor");
            this.name = name;
            this.mobilePhone = mobilePhone;
            System.out.println("Setting required fields(name, mobilePhone) ->\n"
            + name + "\n"
            + mobilePhone);
        }

        public Builder addHomePhone(String val){
            homePhone = val;
            System.out.println("Setting not required filed(homePhone) ->\n" + val);
            return this;
        }

        public Builder addAddress(String val){
            address = val;
            System.out.println("Setting not required filed(address) ->\n" + val);
            return this;
        }

        public Builder addOrder(Order val){
            order = val;
            System.out.println("Setting not required filed(order) ->\n" + val);
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }

    private Customer(Builder builder){
        System.out.println("\tTransfer builder field to customer's constructor");
        this.name = builder.name;
        this.mobilePhone = builder.mobilePhone;
        this.homePhone = builder.homePhone;
        this.address = builder.address;
        this.order = builder.order;
    }

    public String getName() {
        return name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getAddress() {
        return address;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.order.setActive(true);
    }

    private Memento undoOrder;

    private class Memento{
        private Order lastOrder;

        public Memento() {
            this.lastOrder = order;
        }

        public Order getOrder() {
            return lastOrder;
        }
    }

    public void payOrder(){
        order.setActive(false);
        undoOrder = new Memento();
        order = null;
    }

    public void restoreLastOrder(){
        this.order = undoOrder.getOrder();
        this.order.setActive(true);
    }
}
