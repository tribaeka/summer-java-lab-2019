package by.epam.training.task6.model;

import java.time.LocalDate;

public class Credit {
    private int id;
    private int userId;
    private LocalDate date;
    private Period period;
    private int money;
    private int rate;

    private enum  Period {
        DAY{
            public LocalDate getDateTo(LocalDate date){
                return date.plusDays(1);
            }
        },
        WEEK{
            public LocalDate getDateTo(LocalDate date){
                return date.plusWeeks(1);
            }
        },
        MONTH{
            public LocalDate getDateTo(LocalDate date){
                return date.plusMonths(1);
            }
        },
        YEAR{
            public LocalDate getDateTo(LocalDate date){
                return date.plusYears(1);
            }
        };

        public LocalDate getDateTo(LocalDate date){
            return date;
        }
    }

    public Credit(int id, int userId, String date, Period period, int money, int rate) {
        this.id = id;
        this.userId = userId;
        this.date = LocalDate.parse(date);
        this.period = period;
        this.money = money;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateTo(){
        return period.getDateTo(date);
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", dateTo=" + getDateTo() +
                ", period=" + period +
                ", money=" + money +
                ", rate=" + rate +
                '}';
    }
}
