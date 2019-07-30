package by.epam.training.task6.model;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String secondName;
    private Sex sex;
    private LocalDate birthday;

    private enum Sex {
        MALE, FEMALE, ANY
    }

    public User(int id, String name, String secondName, Sex sex, String birthday) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.sex = sex;
        this.birthday = LocalDate.parse(birthday);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return id+";"+name+";"+secondName+";"+sex+";"+birthday;
    }
}
