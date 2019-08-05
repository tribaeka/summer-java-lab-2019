package by.epam.training.task6.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static by.epam.training.task6.utilities.JSONParser.JSON_USERS_KEY;
import static by.epam.training.task6.utilities.JSONParser.JSON_TYPE_KEY;

public class Settings {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ShowForType showFor;
    private String[] useDepartments;
    private double startCostEUR;
    private double startCostUSD;

    public enum ShowForType{
        ID{
            @Override
            public boolean checkFor(User user) {
                return getUsers().stream().map(Integer::parseInt).collect(Collectors.toList()).contains(user.getId());
            }
        },
        NAME{
            @Override
            public boolean checkFor(User user) {
                return getUsers().contains(user.getFullname());
            }
        };

        private List<String> users = new ArrayList<>();

        public List<String> getUsers() {
            return users;
        }

        public void setUsers(JSONArray jsonArray) {
            jsonArray.toList().forEach(object -> users.add(object.toString()));
        }

        public abstract boolean checkFor(User user);
    }

    public Settings(LocalDate dateFrom, LocalDate dateTo, String showFor,
                    String[] useDepartments, double startCostEUR, double startCostUSD) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        JSONObject showForJSON = new JSONObject(showFor);
        this.showFor = ShowForType.valueOf(showForJSON.opt(JSON_TYPE_KEY).toString());
        this.showFor.setUsers(showForJSON.getJSONArray(JSON_USERS_KEY));
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

    public ShowForType getShowFor() {
        return showFor;
    }

    public void setShowFor(ShowForType showFor) {
        this.showFor = showFor;
    }

    public String[] getUseDepartments() {
        return useDepartments;
    }

    public String pickingDepartments(){
        if (useDepartments == null){
            return ".*";
        }
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
