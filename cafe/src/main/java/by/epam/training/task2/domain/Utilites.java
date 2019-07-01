package by.epam.training.task2.domain;

public class Utilites {
    public static String toUSD(int copic){
        return copic/100 + "." + copic/10%10 + copic % 10+" USD";
    }
}
