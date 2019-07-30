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
}
