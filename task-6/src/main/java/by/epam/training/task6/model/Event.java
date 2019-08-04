package by.epam.training.task6.model;

import java.time.LocalDate;
import java.util.Objects;

public class Event {
    private int id;
    private Currency currency;
    private double cost;
    private LocalDate date;

    public Event(int id, Currency currency, double cost, String date) {
        this.id = id;
        this.currency = currency;
        this.cost = cost;
        this.date = LocalDate.parse(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void applyEvent(){
        currency.setCurrencyCost(cost / 1000);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, cost, date);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", currency=" + currency +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
