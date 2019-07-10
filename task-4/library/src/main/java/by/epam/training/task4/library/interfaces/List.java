package by.epam.training.task4.library.interfaces;

public interface List<E> {
    Iterator<E> getIterator();
    int add(E e);
    void addAll(E[] eArray);
    void addAll(List<E> eList);
    E set(int index, E e);
    void setMaxSize(int maxSize);
    int getMaxSize();
    E remove(int index);
    void clear();
    int find(E e);
    E get(int index);
    int size();
    <T> T[] toArray(T[] eArray);
    <T> T[] toArray();
    void trim();
    void filterMatches(List<E> eList);
    void filterMatches(E[] eArray);
    void filterDifference(List<E> eList);
    void filterDifference(E[] eArray);

}
