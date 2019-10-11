package by.epam.training.finalTask.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
