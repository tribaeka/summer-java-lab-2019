package by.epam.training.task6.model;

public enum Currency {
    EUR(1),
    USD(1),
    BR(1);

    private double currencyCost;

    Currency(double currencyCost) {
        this.currencyCost = currencyCost;
    }

    public double getCurrencyCost() {
        return currencyCost;
    }

    public void setCurrencyCost(double currencyCost) {
        this.currencyCost = currencyCost;
    }

    public static void setStartCost(Settings settings){
        EUR.setCurrencyCost(settings.getStartCostEUR());
        USD.setCurrencyCost(settings.getStartCostUSD());
    }

    @Override
    public String toString() {
        return this.name() + "(" + currencyCost + ")";
    }
}
