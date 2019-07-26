package by.epam.training.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class JSONParser {

    public static String getTransactionStringFromSubDB(Path path){
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString()
                .substring(builder.toString().indexOf("["),builder.toString().indexOf("]")+1);
    }

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
}
