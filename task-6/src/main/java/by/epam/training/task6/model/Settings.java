package by.epam.training.task6.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

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

    public String getShowFor() {
        return showFor;
    }

    public void setShowFor(String showFor) {
        this.showFor = showFor;
    }

    public String[] getUseDepartments() {
        return useDepartments;
    }

    public String pickingDepartments(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < useDepartments.length; i++){
            stringBuilder.append("(").append(useDepartments[i]).append(")");
            if (i != useDepartments.length - 1){
                stringBuilder.append("|");
            }
        }
        return stringBuilder.toString();
    }

    public void setUseDepartments(String[] useDepartments) {
        this.useDepartments = useDepartments;
    }

    public double getStartCostEUR() {
        return startCostEUR;
    }

    public void setStartCostEUR(double startCostEUR) {
        this.startCostEUR = startCostEUR;
    }

    public double getStartCostUSD() {
        return startCostUSD;
    }

    public void setStartCostUSD(double startCostUSD) {
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
