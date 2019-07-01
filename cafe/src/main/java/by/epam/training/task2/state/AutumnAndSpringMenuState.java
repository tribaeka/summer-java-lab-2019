package by.epam.training.task2.state;

import by.epam.training.task2.domain.Dish;
import by.epam.training.task2.domain.Drink;

import java.util.Arrays;
import java.util.List;

public class AutumnAndSpringMenuState implements MenuState {
    public List<Dish> getDishesState() {
        return Arrays.asList(
                new Dish("cookies", 100),
                new Dish("cake", 200),
                new Dish("muffin", 150)
        );
    }

    public List<Drink> getDrinksState() {
        return Arrays.asList(
                new Drink("tea",90),
                new Drink("green tea", 80),
                new Drink("red tea", 95),
                new Drink("coffee", 120),
                new Drink("cola", 100)
        );
    }
}
