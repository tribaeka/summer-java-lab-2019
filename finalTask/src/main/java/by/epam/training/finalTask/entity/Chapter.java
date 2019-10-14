package by.epam.training.finalTask.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private Integer id;
    private String title;
    private String content;
    private Integer bookId;
    private LocalDateTime uploadDate;

    public String getTimeAfterUpdating(){
        if (uploadDate.toLocalDate().isAfter(LocalDate.now().minusDays(1))){
            int pastHours = LocalDateTime.now().getHour() - uploadDate.getHour();
            if (pastHours > 0) return pastHours + " hours ago";
            else return "less than an hour";
        }else return "more than a day ago";
    }

    public String getFormatedUploadDate(){
        return uploadDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String titleToUrl(){
        if (title.contains("-")){
            return title.split("-")[0].toLowerCase().trim().replaceAll(" ", "-");
        }else return title.toLowerCase().replaceAll(" ", "-");
    }

    public static String urlToTitle(String url){
        String title = url.replaceAll("-", " ");
        title = title.substring(0, 1).toUpperCase() + title.substring(1);
        return title;
    }

    public List<String> contentToParagraphs(){
        String contentWithFixedEcoding = content
                .replaceAll("[”“]", "\"")
                .replaceAll("’", "'");
        return Arrays.asList(contentWithFixedEcoding.split("\\n"));
    }
}
