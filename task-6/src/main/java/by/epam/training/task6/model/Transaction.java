package by.epam.training.task6.model;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private LocalDate date;
    private int userId;
    private int creditId;
    private Currency currency;
    private int money;
    //При расчете курса валюты – сначала event, потом transaction

    public Transaction(int id, String date, int userId, int creditId, String currency, int money) {
        this.id = id;
        this.date = LocalDate.parse(date);
        this.userId = userId;
        this.creditId = creditId;
        this.currency = Currency.valueOf(currency);
        this.money = money;
    }

    @Override
    public String toString() {
        return id + ";" + date + ";" + userId + ";" + creditId + ";" + currency + ";" + money;
    }
}
