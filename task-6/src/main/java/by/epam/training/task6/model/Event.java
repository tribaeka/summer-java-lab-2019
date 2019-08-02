package by.epam.training.task6.model;

import java.time.LocalDate;

public class Event {
    private int id;
    private Currency currency;
    private int cost;
    private LocalDate date;
    //can be just one event per day

    public Event(int id, Currency currency, int cost, String date) {
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
