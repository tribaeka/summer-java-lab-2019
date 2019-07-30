package by.epam.training.task6.model;

import java.time.LocalDate;
import java.util.Arrays;

public class Settings {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String showFor;
    private String[] useDepartments;
    private double startCostEUR;
    private double startCostUSD;

    public Settings(LocalDate dateFrom, LocalDate dateTo, String showFor,
                    String[] useDepartments, double startCostEUR, double startCostUSD) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.showFor = showFor;
        this.useDepartments = useDepartments;
        this.startCostEUR = startCostEUR;
        this.startCostUSD = startCostUSD;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", showFor=" + showFor +
                ", useDepartments=" + Arrays.toString(useDepartments) +
                ", startCostEUR=" + startCostEUR +
                ", startCostUSD=" + startCostUSD +
                '}';
    }
}
