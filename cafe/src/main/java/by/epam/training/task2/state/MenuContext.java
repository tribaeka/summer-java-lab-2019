package by.epam.training.task2.state;

import by.epam.training.task2.domain.Dish;
import by.epam.training.task2.domain.Drink;
import by.epam.training.task2.domain.MenuItem;
import by.epam.training.task2.exceptions.MenuItemNotFoundException;

public class MenuContext {
    private MenuState menuState;

    public MenuContext(){
        this.menuState = new SummerMenuState();
    }

    public MenuContext(MenuState menuState){
        this.menuState = menuState;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public MenuItem getMenuItemByName(String name) throws MenuItemNotFoundException{
        MenuItem resultOfSearch = null;

        for (MenuItem item : menuState.getDishesState()){
            if (item.getName().equals(name)){
                resultOfSearch = item;
            }
        }
        if (resultOfSearch == null){
            for (MenuItem item : menuState.getDrinksState()){
                if (item.getName().equals(name)){
                    resultOfSearch = item;
                }
            }
        }

        if (resultOfSearch != null){
            return resultOfSearch;
        }else {
            throw new MenuItemNotFoundException();
        }
    }

    public Drink getCustomDrink(String name) throws MenuItemNotFoundException{
        Drink drink = null;

        for (Drink item : menuState.getDrinksState()){
            if (item.getName().equals(name)){
                drink = item;
            }
        }

        if (drink != null){
            return drink;
        }else {
            throw new MenuItemNotFoundException();
        }
    }

    @Override
    public String toString() {
        return "Dishes: " + menuState.getDishesState() + "\nDrinks: " + menuState.getDrinksState();
    }
}
