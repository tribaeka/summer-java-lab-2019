package by.epam.training.task10.model;

public class User {
    private static final String DEFAULT_IMAGE_PATH = "/df/path/";
    private int id;
    private String username;
    private String password;
    private String email;
    private String imagePath;
    private boolean active;

    public User(int id, String username, String password, String email,
                String imagePath, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imagePath = imagePath;
        this.active = active;
    }

    public User(String username, String password, String email) {
        this.id = -1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.imagePath = DEFAULT_IMAGE_PATH;
        this.active = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", active=" + active +
                '}';
    }
}
