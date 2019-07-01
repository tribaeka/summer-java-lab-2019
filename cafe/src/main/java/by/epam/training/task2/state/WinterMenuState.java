package by.epam.training.task2.state;

import by.epam.training.task2.domain.Dish;
import by.epam.training.task2.domain.Drink;

import java.util.Arrays;
import java.util.List;

public class WinterMenuState implements MenuState {
    public List<Dish> getDishesState() {
        return Arrays.asList(
                new Dish("winter cookies", 150),
                new Dish("winter cake", 250),
                new Dish("winter muffin", 200, true),
                new Dish("hot winter spring", 300));
    }

    public List<Drink> getDrinksState() {
        return Arrays.asList(
                new Drink("tea",90),
                new Drink("cappuccino", 150),
                new Drink("coffee", 120),
                new Drink("cola", 100)
        );
    }
}
