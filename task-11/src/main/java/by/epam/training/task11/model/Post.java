package by.epam.training.task11.model;

import by.epam.training.task11.annotations.After;
import by.epam.training.task11.annotations.Before;

@Before(message = "before class")
@After(message = "after class")
public class Post implements IMetaService {
    @After(message = "after info")
    @Before(message = "before info")
    public void printMetadataInfo() {
        System.out.println("\tPost info");
    }

    public void printMetadataExpiredTime() {
        System.out.println("\tPost expiredTime");
    }

    public void printMetadataUser() {
        System.out.println("\tPost added by user");
    }

    public void printMetadataBuilder() {
        System.out.println("\tPost was build");
    }
}
