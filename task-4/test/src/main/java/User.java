import by.epam.training.task4.library.implementations.Entity;

public class User {
    private String name;
    private String email;
    private Integer age;
    private Entity sex;

    public User(String name, String email, Integer age, Entity sex) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Entity getSex() {
        return sex;
    }

    public void setSex(Entity sex) {
        this.sex = sex;
    }
}
