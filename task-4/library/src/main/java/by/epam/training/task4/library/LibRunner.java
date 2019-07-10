package by.epam.training.task4.library;

import by.epam.training.task4.library.implementations.ArrayList;
import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.List;

import java.util.Comparator;

public class LibRunner {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(new IntegerComparator());
        list.setMaxSize(8);
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(null);
        list.trim();
        Iterator<Integer> iterator = list.getIterator();
        while (iterator.hasNext()){
            switch (iterator.getNext()){
                case 5:
                    iterator.remove();
                    break;
                case 2:
                    iterator.addAfter(3);
                    break;
            }
        }
        iterator.reset();
        list.addAll(new Integer[]{4,5});
        list.addAll(new ArrayList<Integer>(new IntegerComparator(), new Integer[]{6,7}));
        list.set(0, 0);
        while (iterator.hasNext()){
            System.out.println(iterator.getNext());
        }
        Object[] someArray = list.toArray();
        System.out.println(someArray.length);
    }
}
