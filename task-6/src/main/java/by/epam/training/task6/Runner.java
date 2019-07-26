package by.epam.training.task6;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.epam.training.task6.JSONParser.*;

public class Runner {
    private final static String DIRECTORY_PATH = "/Users/tribaeka/summer-java-lab-2019-yahor-hlushak/task-6/src/main/resources/data/";
    private final static String SUB_DB_POSTFIX = "^(db_)(\\w+)(\\.json)";
    public static void main(String[] args) throws NoSuchFileException {
        File dateBaseFile = new File(DIRECTORY_PATH + "db.json");
        File settingsFile = new File(DIRECTORY_PATH + "settings.json");
        if (!dateBaseFile.exists()){
            throw new NoSuchFileException("Date base is not exist!");
        }
        if (!settingsFile.exists()){
            throw new NoSuchFileException("Settings is not exist!");
        }
        List<JSONObject> subDBList = Stream.of(Objects.requireNonNull(new File(DIRECTORY_PATH).list()))
                .filter(item -> item.matches(SUB_DB_POSTFIX))
                .map(item -> item = DIRECTORY_PATH + item)
                .map(item -> Paths.get(item))
                .map(JSONParser::PathToString)
                .map(JSONObject::new)
                .collect(Collectors.toList());
        JSONArray transactions = subDBList.get(1).getJSONArray("transactions");

        for (Object object : transactions){
            System.out.println(object);
        }
        System.out.println("db:");
        System.out.println(PathToString(Paths.get(dateBaseFile.getPath())));
        JSONObject dateBase = new JSONObject(PathToString(Paths.get(dateBaseFile.getPath())));
        JSONObject data = dateBase.getJSONObject("data");
        JSONArray DBTransactions = data.getJSONArray("transactions");
        System.out.println(DBTransactions);

    }
}
