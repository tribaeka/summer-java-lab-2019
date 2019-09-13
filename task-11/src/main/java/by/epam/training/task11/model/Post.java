package by.epam.training.task11.model;

public class Post implements IMetaService {
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
