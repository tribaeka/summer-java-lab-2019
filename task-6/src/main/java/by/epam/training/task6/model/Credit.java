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
    private int numberOfTransactions = 0;
    private State state = State.IN_PROGRESS;

    private enum State{
        IN_PROGRESS, DONE;
        private LocalDate doneDate = null;

        public LocalDate getDoneDate() {
            return doneDate;
        }

        public void setDoneDate(LocalDate doneDate) {
            this.doneDate = doneDate;
        }

        @Override
        public String toString() {
            if (this == State.DONE){
                return this.name() + " - " + doneDate;
            }else return this.name();
        }
    }

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

        public abstract LocalDate getDateTo(LocalDate date);
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
        if (money < 0) return 0;
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(double defaultRate) {
        this.defaultRate = defaultRate;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
        if (checkProgress()) state.setDoneDate(transaction.getDate());
    }

    public boolean applyDiscount(Discount discount){
        if (discount.getDiscount() > rate) return false;
        rate -= discount.getDiscount();
        return true;
    }

    public void restoreRate(){
        rate = defaultRate;
    }

    public void addTransaction(){
        numberOfTransactions++;
    }

    public void removeTransaction(){
        numberOfTransactions--;
    }

    private boolean checkProgress(){
        if (money <= 0){
            state = State.DONE;
            return true;
        }
        return false;
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
