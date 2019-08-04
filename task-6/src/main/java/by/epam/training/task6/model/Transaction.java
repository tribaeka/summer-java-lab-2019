package by.epam.training.task6.model;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private LocalDate date;
    private int userId;
    private int creditId;
    private Currency currency;
    private double money;
    //При расчете курса валюты – сначала event, потом transaction

    public Transaction(int id, String date, int userId, int creditId, String currency, double money) {
        this.id = id;
        this.date = LocalDate.parse(date);
        this.userId = userId;
        this.creditId = creditId;
        this.currency = Currency.valueOf(currency);
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", creditId=" + creditId +
                ", currency=" + currency +
                ", money=" + money +
                '}';
    }
}
