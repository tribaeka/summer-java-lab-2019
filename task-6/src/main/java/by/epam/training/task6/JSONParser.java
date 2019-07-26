package by.epam.training.task6;

import by.epam.training.task6.model.Transaction;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class JSONParser {
    public static List<Transaction> getTransactionsFromSubDB(Path path){
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String toParseString = builder.toString()
                .substring(builder.toString().indexOf("["),builder.toString().indexOf("]")+1);

        return JSON.parseArray(toParseString, Transaction.class);
    }
}
