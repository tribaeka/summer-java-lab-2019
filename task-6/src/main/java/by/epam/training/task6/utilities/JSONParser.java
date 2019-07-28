package by.epam.training.task6.utilities;

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONParser {
    public final static String DIRECTORY_PATH = "/Users/tribaeka/summer-java-lab-2019-yahor-hlushak/task-6/src/main/resources/data/";
    public final static String SUB_DB_POSTFIX = "^(db_)(\\w+)(\\.json)";
    public final static String JSON_TRANSACTIONS_KEY = "transactions";
    public final static String JSON_DATA_KEY = "data";


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
                .forEach(item -> writeStringInFile(item, ""));
    }

    public static void writeStringInFile(File file, String data) {
        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            bufferedWriter.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
