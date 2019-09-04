package by.epam.training.task8.model;

import java.util.List;

public class People {
    private List<Person> people;

    public People(List<Person> people) {
        this.people = people;
    }

    public void add(Person person){
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "People{" +
                "people=" + people +
                '}';
    }
}
