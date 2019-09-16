package by.epam.training.task11.model;

import by.epam.training.task11.annotations.After;
import by.epam.training.task11.annotations.Before;
import by.epam.training.task11.annotations.Ignore;
import by.epam.training.task11.annotations.ThrowException;

public class Blog extends Service implements ITimeService{
    @Override
    public void printTimeInfo() {
        System.out.println("\tBlog time info");
    }

    @Override
    public void printTimeExpiredTime() {
        System.out.println("\tBlog expired time");
    }
    @ThrowException
    @Override
    public void printTimeUser() {
        System.out.println("\tBlog time user");
    }
    @After(message = "after info3")
    @Before(message = "before info1")
    @Before(message = "before info2")
    @Ignore
    @Override
    public void printTimeBuilder() {
        System.out.println("\tBlog time builder");
    }
}
