package by.epam.training.task11.model;

import by.epam.training.task11.annotations.After;
import by.epam.training.task11.annotations.Before;
import by.epam.training.task11.annotations.Ignore;

@Before(message = "before class1")
@Before(message = "before class2")
@Before(message = "before class3")
@After(message = "after class")
@After(message = "after class")
@After(message = "after class")
public class Post implements IMetaService {

    @After(message = "after info1")
    @After(message = "after info2")
    @After(message = "after info3")
    @Before(message = "before info1")
    @Before(message = "before info2")
    @Before(message = "before info3")
    @Before(message = "before info4")
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
