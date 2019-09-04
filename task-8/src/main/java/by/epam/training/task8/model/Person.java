package by.epam.training.task8.model;

import java.time.LocalDate;
import java.time.Period;

public class Person {

    private int id;
    private String surname;
    private String name;
    private Birthday birthday;
    private Birthplace birthplace;
    private String work;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDay());
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = new Birthday(birthday.getDayOfMonth(), birthday.getMonthValue(), birthday.getYear());
    }

    public String getBirthplace() {
        return birthplace.getCity();
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = new Birthplace(birthplace);
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getAge(){
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", birthplace='" + birthplace + '\'' +
                ", work='" + work + '\'' +
                '}';
    }
}
