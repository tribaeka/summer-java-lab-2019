package by.epam.training.task10;

public class Runner {
    public static void main(String[] args) {
        Database library = new Database("jdbc:mysql://localhost:3306/library",
                "root", "924462");
        System.out.println(library.getConnection());
    }
}
