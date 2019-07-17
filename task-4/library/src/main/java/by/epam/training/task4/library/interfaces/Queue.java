package by.epam.training.task4.library.interfaces;

public interface Queue<E> {
    Iterator getIterator();
    boolean isEmpty();
    E peek();
    E poll();
    E pull();
    E remove();
    void push(E e);
    void pushAll(Queue<E> eQueue);
    void pushAll(E[] eArray);
    int search(E e);
    int clear();
    int size();
}
