package by.epam.training.task6.model;

import java.time.LocalDate;

public class Credit {
    private int id;
    private int userId;
    private LocalDate date;
    private Period period;
    private double money;
    private double rate;
    private double defaultRate;

    private enum  Period {
        DAY(null){
            public LocalDate getDateTo(LocalDate date){
                return date.plusDays(1);
            }
        },
        WEEK(null){
            public LocalDate getDateTo(LocalDate date){
                return date.plusWeeks(1);
            }
        },
        MONTH(null){
            public LocalDate getDateTo(LocalDate date){
                return date.plusMonths(1);
            }
        },
        YEAR(null){
            public LocalDate getDateTo(LocalDate date){
                return date.plusYears(1);
            }
        };
        private LocalDate rateWasAddedTo;

        Period(LocalDate rateWasAddedTo) {
            this.rateWasAddedTo = rateWasAddedTo;
        }

        public LocalDate getRateWasAddedTo() {
            return rateWasAddedTo;
        }

        public void setRateWasAddedTo(LocalDate rateWasAddedTo) {
            this.rateWasAddedTo = rateWasAddedTo;
        }

        public LocalDate getDateTo(LocalDate date){
            return date;
        }
    }

    public Credit(int id, int userId, String date, Period period, double money, double rate) {
        this.id = id;
        this.userId = userId;
        this.date = LocalDate.parse(date);
        this.period = period;
        this.period.setRateWasAddedTo(this.date);
        this.money = money;
        this.rate = rate;
        this.defaultRate = rate;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private double roundMoney(){
        return Math.round(money * 100.0) / 100.0;
    }

    public void growMoneyToTransactionDate(LocalDate transactionDate){
        while (period.getRateWasAddedTo().isBefore(transactionDate)){
            money = money + money * (rate / 100);
            period.setRateWasAddedTo(period.getDateTo(period.getRateWasAddedTo()));
        }
        money = roundMoney();
    }

    public void creditRepayment(Transaction transaction){
        money -= transaction.getMoney() * transaction.getCurrency().getCurrencyCost();
    }

    public boolean applyDiscount(Discount discount){
        if (discount.getDiscount() > rate) return false;
        rate -= discount.getDiscount();
        return true;
    }

    public void restoreRate(){
        rate = defaultRate;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", period=" + period +
                ", money=" + roundMoney() +
                ", rate=" + rate +
                '}';
    }
}
