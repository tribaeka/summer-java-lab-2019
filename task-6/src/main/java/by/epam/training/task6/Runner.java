package by.epam.training.task6;


import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.epam.training.task6.JSONParser.*;

public class Runner {
    private final static String DIRECTORY_PATH = "/Users/tribaeka/summer-java-lab-2019-yahor-hlushak/task-6/src/main/resources/data/db.json";
    private final static String SUB_DB_POSTFIX = "^(db_)(\\w+)(\\.json)";
    public static void main(String[] args) throws NoSuchFileException {
        File dateBase = new File(DIRECTORY_PATH + "db.json");
        File settings = new File(DIRECTORY_PATH + "settings.json");
        if (!dateBase.exists()){
            throw new NoSuchFileException("Date base is not exist!");
        }
        if (!settings.exists()){
            throw new NoSuchFileException("Settings is not exist!");
        }
        List<Path> subDBList = Stream.of(Objects.requireNonNull(new File(DIRECTORY_PATH).list()))
                .filter(item -> item.matches(SUB_DB_POSTFIX))
                .map(item -> item = DIRECTORY_PATH + item)
                .map(item -> Paths.get(item))
                .collect(Collectors.toList());
        for (Path path : subDBList){
            System.out.println(getTransactionsFromSubDB(path));
        }
    }
}
