package by.epam.training.finalTask.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "chapters")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {
    private Integer id;
    private String title;
    private String description;
    private String imagePath;
    private double rating;
    private String author;
    private List<Genre> genres = new ArrayList<>();
    private List<Chapter> chapters = new ArrayList<>();

    public Chapter getLastChapter(){
        return chapters.stream()
                .min(new Comparator<Chapter>() {
                    @Override
                    public int compare(Chapter o1, Chapter o2) {
                        if (o1.getUploadDate().isBefore(o2.getUploadDate())) return 1;
                        if (o1.getUploadDate().isAfter(o2.getUploadDate())) return -1;
                        if (o1.getUploadDate().isEqual(o2.getUploadDate())) return 0;
                        return 0;
                    }
                })
                .get();
    }

    public String titleToUrl(){
        return title.toLowerCase().replaceAll(" ", "-");
    }

    public static String urlToTitle(String url){
        return url.replaceAll("-", " ");
    }
}
