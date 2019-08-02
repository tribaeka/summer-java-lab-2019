package by.epam.training.task6.utilities;

import by.epam.training.task6.model.Settings;
import com.alibaba.fastjson.JSON;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONParser {
    public final static String DIRECTORY_PATH = "/Users/tribaeka/summer-java-lab-2019-yahor-hlushak/task-6/src/main/resources/data/";
    public final static String SUB_DB_POSTFIX = "^(db_)(\\w+)(\\.json)";
    public final static String TRANSACTIONS_REPLACEMENT = "{\ntransactions: [\n  ]\n}";
    public final static String JSON_DATA_KEY = "data";
    public final static String JSON_USERS_KEY = "users";
    public final static String JSON_CREDITS_KEY = "credits";
    public final static String JSON_DISCOUNTS_KEY = "discounts";
    public final static String JSON_EVENTS_KEY = "events";
    public final static String JSON_TRANSACTIONS_KEY = "transactions";
    public final static String SETTINGS_BODY_KEY = "settings";


    public static String PathToString(Path path){
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(path)){
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static Settings uploadSettings(File settingsFile){
        JSONObject settingsJSONBody = new JSONObject(PathToString(Paths.get(settingsFile.getPath())));
        JSONObject settingJSON = settingsJSONBody.getJSONObject(SETTINGS_BODY_KEY);
        return JSON.parseObject(settingJSON.toString(), Settings.class);
    }

    public static List<JSONObject> getSubDBList(){
        return Stream.of(Objects.requireNonNull(new File(DIRECTORY_PATH).list()))
                .filter(item -> item.matches(SUB_DB_POSTFIX))
                .map(item -> item = DIRECTORY_PATH + item)
                .map(item -> Paths.get(item))
                .map(JSONParser::PathToString)
                .map(JSONObject::new)
                .collect(Collectors.toList());
    }
    public static void clearSubDBFiles(){
        Stream.of(Objects.requireNonNull(new File(DIRECTORY_PATH).list()))
                .filter(item -> item.matches(SUB_DB_POSTFIX))
                .map(item -> item = DIRECTORY_PATH + item)
                .map(File::new)
                .forEach(item -> writeStringInFile(item, TRANSACTIONS_REPLACEMENT));
    }

    public static void writeStringInFile(File file, String data) {
        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            bufferedWriter.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  <T> List<T> parseKey(JSONObject data, String key, Class<T> type) {
        List<T> answer = new ArrayList<>();
        for (Object item : data.getJSONArray(key)) {
            answer.add(JSON.parseObject(item.toString(), type));
        }
        return answer;
    }
}
