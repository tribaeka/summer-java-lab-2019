package by.epam.training.task6;

import by.epam.training.task6.model.*;
import by.epam.training.task6.model.Currency;
import by.epam.training.task6.utilities.BadDiscountException;
import by.epam.training.task6.utilities.TransactionsByDateComparator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


import static by.epam.training.task6.utilities.JSONParser.*;

public class Runner {

    private final static String MAIN_DB_FILENAME = "db.json";
    private final static String SETTINGS_FILENAME = "settings.json";

    public static void main(String[] args) throws NoSuchFileException {
        File dateBaseFile = new File(DIRECTORY_PATH + MAIN_DB_FILENAME);
        File settingsFile = new File(DIRECTORY_PATH + SETTINGS_FILENAME);
        isFileExist(dateBaseFile);
        isFileExist(settingsFile);

        Settings settings = uploadSettings(settingsFile);
        Currency.setStartCost(settings);
        transactionsUploadingFromSubDBFiles(dateBaseFile, settings.pickingDepartments());

        JSONObject dataBase = new JSONObject(
                PathToString(Paths.get(dateBaseFile.getPath())))
                .getJSONObject(JSON_DATA_KEY);
        List<User> users = parseKey(dataBase, JSON_USERS_KEY, User.class);
        List<Event> events = parseKey(dataBase, JSON_EVENTS_KEY, Event.class);
        List<Credit> credits = parseKey(dataBase, JSON_CREDITS_KEY, Credit.class);
        List<Discount> discounts = parseKey(dataBase, JSON_DISCOUNTS_KEY, Discount.class);
        List<Transaction> transactions = parseKey(dataBase, JSON_TRANSACTIONS_KEY, Transaction.class);

        /*System.out.println(settings);
        System.out.println(users);
        System.out.println(events);
        System.out.println(credits);
        System.out.println(discounts);
        System.out.println(transactions);
        System.out.println();*/

        List<Transaction> availableTransactions = transactionsForCalc(settings, transactions);
        HashMap<Credit, List<Transaction>> paymentMap = new HashMap<>();
        credits.forEach(credit -> {
            List<Transaction> listInMap = new LinkedList<>();
            availableTransactions.stream()
                                 .filter(transaction -> credit.getId() == transaction.getCreditId())
                                 .forEach(listInMap::add);
            if (!listInMap.isEmpty()) {
                listInMap.sort(new TransactionsByDateComparator());
                paymentMap.put(credit, listInMap);
            }
        });
        paymentMap.forEach((key, values) -> values.forEach(transaction -> {
            processTransaction(transaction, key, events, discounts);
            Currency.setStartCost(settings);
        }));

        showResults(credits, showFor(users, settings), settings.getSortBy());

    }

    private static void isFileExist(File file) throws NoSuchFileException {
        if (!file.exists()){
            throw new NoSuchFileException(file.getName() + " is not exist!");
        }
    }

    private static void transactionsUploadingFromSubDBFiles(File mainDBFile, String useDepartmentsRegexp){
        String subDBMatchRegexp = SUB_DB_PREFIX + "(" + useDepartmentsRegexp + ")" + SUB_DB_POSTFIX;
        List<JSONArray> subDBTransactions = getSubDBTransactionArrays(subDBMatchRegexp);

        JSONObject dateBase = new JSONObject(PathToString(Paths.get(mainDBFile.getPath())));
        JSONObject data = dateBase.getJSONObject(JSON_DATA_KEY);
        JSONArray DBTransactions = data.getJSONArray(JSON_TRANSACTIONS_KEY);

        subDBTransactions.forEach(array -> {
            for (Object item : array) DBTransactions.put(item);
        });

        data.put(JSON_TRANSACTIONS_KEY, DBTransactions);
        dateBase.put(JSON_DATA_KEY, data);
        writeStringInFile(mainDBFile, dateBase.toString(4));
        clearSubDBFiles(subDBMatchRegexp);
    }

    private static List<Transaction> transactionsForCalc(Settings settings, List<Transaction> transactions){
        LocalDate dateFrom;
        LocalDate dateTo;

        if (settings.getDateFrom() != null) dateFrom = settings.getDateFrom();
        else dateFrom = LocalDate.MIN;

        if (settings.getDateTo() != null) dateTo = settings.getDateTo();
        else dateTo = LocalDate.MAX;

        return transactions.stream()
                .filter(item -> item.getDate().isAfter(dateFrom))
                .filter(item -> item.getDate().isBefore(dateTo))
                .collect(Collectors.toList());
    }

    private static List<Event> availableEvents(List<Event> events, LocalDate date){
        Iterator<Event> iterator = events.iterator();
        Set<LocalDate> dateSet = new HashSet<>();
        while (iterator.hasNext()){
            if (!dateSet.add(iterator.next().getDate())){
                iterator.remove();
            }
        }
        return events.stream().filter(event -> event.getDate().isBefore(date)).collect(Collectors.toList());
    }

    private static List<Discount> availableDiscounts(List<Discount> discounts, LocalDate date){
        return discounts.stream().filter(discount -> discount.getType().isAvailable(discount, date)).collect(Collectors.toList());
    }

    private static void processTransaction(Transaction transaction, Credit credit,
                                           List<Event> events, List<Discount> discounts){
        boolean badDiscount = false;
        credit.growMoneyToTransactionDate(transaction.getDate());
        availableEvents(events, transaction.getDate()).forEach(Event::applyEvent);
        for (Discount discount : availableDiscounts(discounts, transaction.getDate())){
            if (!credit.applyDiscount(discount)){
                try {
                    throw new BadDiscountException();
                } catch (BadDiscountException e) {
                    transaction.setMoney(0);
                    badDiscount = true;
                }
            }
        }
        credit.creditRepayment(transaction);
        credit.restoreRate();
        credit.addTransaction();
        if (badDiscount) {
            transaction.restoreMoney();
            credit.removeTransaction();
        }
    }

    private static List<User> showFor(List<User> users, Settings settings){
        return users.stream().filter(user -> settings.getShowFor().checkFor(user)).collect(Collectors.toList());
    }

    private static void showResults(List<Credit> credits, List<User> users, Settings.SortByFormat sortByFormat){
        List<TableOutConsoleFormat> tableData = new ArrayList<>();

        for (User user : users){
            for (Credit credit : credits){
                if (user.getId() == credit.getUserId()){
                    tableData.add(new TableOutConsoleFormat(credit.getId(), user.getId(), user.getFullName(), credit.getNumberOfTransactions(),
                            credit.getMoney(), credit.getState(), user.getBirthday()));
                }
            }
        }
        tableData.stream().sorted(sortByFormat.getComparator()).forEach(System.out::println);
    }

    public static class TableOutConsoleFormat{
        private final int creditId;
        private final int userId;
        private final String fullName;
        private final int numberOfTransactions;
        private final double debt;
        private final Credit.State state;
        private final LocalDate birthday;

        public TableOutConsoleFormat(int creditId, int userId, String fullName, int numberOfTransactions,
                                     double debt, Credit.State state, LocalDate birthday) {
            this.creditId = creditId;
            this.userId = userId;
            this.fullName = fullName;
            this.numberOfTransactions = numberOfTransactions;
            this.debt = debt;
            this.state = state;
            this.birthday = birthday;
        }

        public int getCreditId() {
            return creditId;
        }

        public int getUserId() {
            return userId;
        }

        public String getFullName() {
            return fullName;
        }

        public int getNumberOfTransactions() {
            return numberOfTransactions;
        }

        public double getDebt() {
            return debt;
        }

        public Credit.State getState() {
            return state;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        @Override
        public String toString() {
            return String.format("|%-2d|%-2d|%-20s|%-2d|%-8.0f|%-20s|",
                    creditId, userId, fullName, numberOfTransactions,
                    debt, state);
        }
    }

}
