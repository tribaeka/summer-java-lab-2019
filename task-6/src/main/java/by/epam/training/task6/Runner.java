package by.epam.training.task6;

import by.epam.training.task6.model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
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
        transactionsUploadingFromSubDBFiles(dateBaseFile);

        JSONObject dataBase = new JSONObject(
                PathToString(Paths.get(dateBaseFile.getPath())))
                .getJSONObject(JSON_DATA_KEY);
        List<User> users = parseKey(dataBase, JSON_USERS_KEY, User.class);
        System.out.println(users);
        List<Event> events = parseKey(dataBase, JSON_EVENTS_KEY, Event.class);
        System.out.println(events);
        List<Credit> credits = parseKey(dataBase, JSON_CREDITS_KEY, Credit.class);
        System.out.println(credits);
        List<Discount> discounts = parseKey(dataBase, JSON_DISCOUNTS_KEY, Discount.class);
        System.out.println(discounts);
        List<Transaction> transactions = parseKey(dataBase, JSON_TRANSACTIONS_KEY, Transaction.class);
        System.out.println(transactions);
    }

    private static void isFileExist(File file) throws NoSuchFileException {
        if (!file.exists()){
            throw new NoSuchFileException(file.getName() + " is not exist!");
        }
    }

    private static void transactionsUploadingFromSubDBFiles(File mainDBFile){
        List<JSONObject> subDBList = getSubDBList();
        List<JSONArray> subDBTransactions = subDBList.stream()
                .map(object -> object.getJSONArray(JSON_TRANSACTIONS_KEY))
                .collect(Collectors.toList());

        JSONObject dateBase = new JSONObject(PathToString(Paths.get(mainDBFile.getPath())));
        JSONObject data = dateBase.getJSONObject(JSON_DATA_KEY);
        JSONArray DBTransactions = data.getJSONArray(JSON_TRANSACTIONS_KEY);

        subDBTransactions.forEach(array -> {
            for (Object item : array) DBTransactions.put(item);
        });

        data.put(JSON_TRANSACTIONS_KEY, DBTransactions);
        dateBase.put(JSON_DATA_KEY, data);
        writeStringInFile(mainDBFile, dateBase.toString(4));
        clearSubDBFiles();
    }
}
