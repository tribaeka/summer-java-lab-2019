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
        DAY, WEEK, MONTH, YEAR
    }

    public Credit(int id, int userId, String date, Period period, int money, int rate) {
        this.id = id;
        this.userId = userId;
        this.date = LocalDate.parse(date);
        this.period = period;
        this.money = money;
        this.rate = rate;
    }
}
