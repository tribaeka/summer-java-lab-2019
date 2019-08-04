package by.epam.training.task6;

import by.epam.training.task6.model.*;
import by.epam.training.task6.model.Currency;
import by.epam.training.task6.utilities.TransactionsByDateComparator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

        System.out.println(settings);
        System.out.println(users);
        System.out.println(events);
        System.out.println(credits);
        System.out.println(discounts);
        System.out.println(transactions);
        System.out.println();

        List<Transaction> avalabeTransactions = transactionsForCalc(settings, transactions);
        HashMap<Credit, List<Transaction>> workMap = new HashMap<>();
        credits.forEach(credit -> {
            List<Transaction> listInMap = new LinkedList<>();
            boolean firstFindTrigger = false;
            for (Transaction transaction : avalabeTransactions){
                if (credit.getId() == transaction.getCreditId()){
                    listInMap.add(transaction);
                    firstFindTrigger = true;
                }
            }
            if (!listInMap.isEmpty()) listInMap.sort(new TransactionsByDateComparator());
            if (firstFindTrigger) workMap.put(credit, listInMap);
        });
        System.out.println(workMap);

        for (Map.Entry<Credit, List<Transaction>> entry : workMap.entrySet()){
            for (Transaction transaction : entry.getValue()){
                entry.getKey().growMoneyToTransactionDate(transaction.getDate());
                System.out.println(entry.getKey()+"before payment");
                availableEventsByDate(events, transaction.getDate()).forEach(Event::applyEvent);
                System.out.println((transaction.getMoney()*transaction.getCurrency().getCurrencyCost())+"-> money");
                entry.getKey().creditRepayment(transaction);
                System.out.println(entry.getKey()+"after payment");
                Currency.setStartCost(settings);
            }

        }
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

    private static List<Event> availableEventsByDate(List<Event> events, LocalDate date){
        Iterator<Event> iterator = events.iterator();
        Set<LocalDate> dateSet = new HashSet<>();
        while (iterator.hasNext()){
            if (!dateSet.add(iterator.next().getDate())){
                iterator.remove();
            }
        }
        return events.stream().filter(event -> event.getDate().isBefore(date)).collect(Collectors.toList());
    }
}
