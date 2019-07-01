package by.epam.training.task2.state;

import by.epam.training.task2.domain.Dish;
import by.epam.training.task2.domain.Drink;

import java.util.List;

public interface MenuState {
    List<Dish> getDishesState();
    List<Drink> getDrinksState();
}
