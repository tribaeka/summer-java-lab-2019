package by.epam.training.task1;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        final String helloString = "Hello word: ";
        final String datePattern = "dd MMM ss HH yyyy mm";
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern, Locale.ENGLISH);
        final LocalDateTime date = LocalDateTime.now();

        System.out.println(helloString + dateTimeFormatter.format(date));
        System.out.println(helloString + dateTimeFormatter.format(date.plusWeeks(1)));

    }
}
