package by.epam.training.task2.exceptions;

public class MenuItemNotFoundException extends Exception{
    public MenuItemNotFoundException(){

    }
    public MenuItemNotFoundException(String message){
        super(message);
    }
}
