package by.epam.training.task1;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        String helloString = "Hello word: ";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM ss HH yyyy mm", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.now();

        System.out.println(helloString + dateTimeFormatter.format(date));
        System.out.println(helloString + dateTimeFormatter.format(date.plusWeeks(1)));

    }
}
