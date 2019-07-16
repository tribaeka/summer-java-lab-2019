package by.epam.training.task4.library;

import java.util.Comparator;

public class ComparableComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T lhs, T rhs) {
        return lhs.compareTo(rhs);
    }
}
