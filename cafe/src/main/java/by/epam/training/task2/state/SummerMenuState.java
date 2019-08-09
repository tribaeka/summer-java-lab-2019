package by.epam.training.task2.state;

import by.epam.training.task2.domain.Dish;
import by.epam.training.task2.domain.Drink;

import java.util.Arrays;
import java.util.List;

public class SummerMenuState implements MenuState {
    public List<Dish> getDishesState() {
        return Arrays.asList(
                new Dish("summer cookies", 150),
                new Dish("summer cake", 250),
                new Dish("summer muffin", 200, true),
                new Dish("summer cold spring", 300)
        );
    }

    public List<Drink> getDrinksState() {
        return Arrays.asList(
                new Drink("tea",90),
                new Drink("coffee", 120),
                new Drink("cola", 100),
                new Drink("pepsi", 100),
                new Drink("fanta", 100)
        );
    }
}
