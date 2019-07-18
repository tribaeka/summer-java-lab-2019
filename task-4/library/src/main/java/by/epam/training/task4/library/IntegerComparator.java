package by.epam.training.task4.library;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 == null && o2 == null){
            return -1;
        }
        if (o1 == null){
            return o2;
        }
        if (o2 == null){
            return o1;
        }
        return o1 - o2;
    }
}
