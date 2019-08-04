package by.epam.training.task6.utilities;

import by.epam.training.task6.model.Transaction;

import java.util.Comparator;

public class TransactionsByDateComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        if (o1.getDate().isEqual(o2.getDate())) return 0;
        if (o1.getDate().isBefore(o2.getDate())) return -1;
        if (o2.getDate().isAfter(o2.getDate())) return 1;
        return 0;
    }
}
