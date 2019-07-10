package by.epam.training.task4.library.interfaces;

public interface Iterator<E> {
    boolean hasNext();
    E getNext();
    void remove();
    void addBefore(E e);
    void addAfter(E e);
    void reset();
}
