package by.epam.training.task6.model;

import java.time.LocalDate;

public class Discount {
    private int id;
    private TypeOfDiscounts type;
    private LocalDate date;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private double discount;

    public Discount(int id, TypeOfDiscounts type, LocalDate date, LocalDate dateFrom, LocalDate dateTo, double discount) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeOfDiscounts getType() {
        return type;
    }

    public void setType(TypeOfDiscounts type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        if (type.equals(TypeOfDiscounts.ONE)){
            return "Discount{" +
                    "id=" + id +
                    ", type=" + type +
                    ", date=" + date +
                    ", discount=" + discount +
                    '}';
        }else {
            return "Discount{" +
                    "id=" + id +
                    ", type=" + type +
                    ", dateFrom=" + dateFrom +
                    ", dateTo=" + dateTo +
                    ", discount=" + discount +
                    '}';
        }
    }
}
