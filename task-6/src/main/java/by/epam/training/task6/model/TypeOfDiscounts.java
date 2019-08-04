package by.epam.training.task6.model;

import java.time.LocalDate;

public enum TypeOfDiscounts {
    ONE{
        public boolean isAvailable(Discount discount, LocalDate transactionDate){
            return transactionDate.isEqual(discount.getDate());
        }
    },
    MANY{
        public boolean isAvailable(Discount discount, LocalDate transactionDate){
            return transactionDate.isBefore(discount.getDateTo())
                    && transactionDate.isAfter(discount.getDateFrom());
        }
    };

    public boolean isAvailable(Discount discount, LocalDate transactionDate){
        return false;
    }
}
