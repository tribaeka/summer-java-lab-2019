package by.epam.training.task4.library.interfaces;

import java.util.Comparator;

public interface Stack<E> {
    Iterator getIterator();
    boolean isEmpty();
    E peek();
    E pop();
    void push(E e);
    void pushAll(Stack<E> eStack);
    void pushAll(E[] eArray);
    int search(E e);
    int clear();
    int size();
    void sort(Comparator<E> comparator);
}
