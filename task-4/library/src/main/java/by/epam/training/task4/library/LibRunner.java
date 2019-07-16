package by.epam.training.task4.library;

import by.epam.training.task4.library.implementations.ArrayList;
import by.epam.training.task4.library.implementations.LinkedList;
import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.List;

public class LibRunner {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>(new IntegerComparator());
        list.setMaxSize(10);
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(null);
        list.trim();
        Iterator<Integer> iterator = list.getIterator();
        iterator.reset();
        Integer[] integers = new Integer[]{4,5};
        list.addAll(integers);
        List<Integer> integerList = new LinkedList<>(new IntegerComparator());
        integerList.add(6);
        integerList.add(7);
        list.addAll(integerList);
        list.set(0, 0);
        list.filterMatches(integers);
        while (iterator.hasNext()){
            System.out.println(iterator.getNext());
        }
        Object[] someArray = list.toArray();
        System.out.println("toArray lenght="+someArray.length);
    }
}
