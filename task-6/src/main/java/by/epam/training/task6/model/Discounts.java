package by.epam.training.task6.model;

public class Discounts {
    private int id;
    private Type type;//TODO


    private enum Type {
        ONE, MANY
    }
    /*
    для ONE – акция действует только 1 день, возможные поля
    date – сама дата и discount – скидка, она отнимается от текущего rate
    (если rate – меньше скидки – тогда просто обнуляем начисление в эту итерацию)
    для MANY – акция действует в период между dateFrom и dateTo, остальное – тоже самое
     */
}
