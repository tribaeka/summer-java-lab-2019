package by.epam.training.task10.model;

public class Chapter {
    private int id;
    private String title;
    private String content;
    private String upload_date;

    public Chapter(){}

    public Chapter(int id, String title, String content, String upload_date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.upload_date = upload_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }
}
