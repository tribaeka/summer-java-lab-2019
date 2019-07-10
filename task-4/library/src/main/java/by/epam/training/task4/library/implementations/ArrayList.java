package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterable;
import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>, Iterable<E> {

    private int size;
    private E[] items;
    private int maxSize = 8;
    private Comparator<E> comparator;
    public ArrayList(Comparator<E> comparator) {
        clear();
        this.comparator = comparator;
    }

    public ArrayList(Comparator<E> comparator, E[] array) {
        clear();
        this.comparator = comparator;
        addAll(array);
    }

    public void clear() {
        size = 0;
        ensureCapacity(maxSize);
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size || size + 1 > maxSize) return;

        E[] old = items;
        items = (E[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            items[i] = old[i];
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public E get(int index) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        return items[index];
    }

    public E set(int index, E newVal) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        E old = items[index];
        items[index] = newVal;
        sort();
        return old;
    }

    public int add(E element) {
        return add(size(), element);
    }

    public int add(int index, E element) {
        if (items.length == size()) ensureCapacity(size() * 2 + 1);
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = element;
        size++;
        sort();
        return index;
    }

    public E remove(int index) {
        E removeItem = items[index];
        for (int i = index; i < size() - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return removeItem;
    }

    public void addAll(E[] eArray) {
        int numNew = eArray.length;
        if (numNew > (items.length - size)){
            ensureCapacity(size + numNew);
        }
        System.arraycopy(eArray, 0, items, size, numNew);
        size += numNew;
        sort();
    }

    public void addAll(List<E> eList) {
        Object[] a = eList.toArray();
        addAll((E[]) a);
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public <T> T[] toArray(T[] eArray) {
        if (eArray.length < size)
            return (T[]) Arrays.copyOf(items, size, eArray.getClass());
        System.arraycopy(items, 0, eArray, 0, size);
        if (eArray.length > size)
            eArray[size] = null;
        return eArray;
    }

    public <T> T[] toArray() {
        return (T[]) Arrays.copyOf(items, size);
    }


    public void trim() {
        Iterator iterator = getIterator();
        while (iterator.hasNext()){
            if (iterator.getNext() == null){
                iterator.remove();
            }
        }
    }

    public void filterMatches(List<E> eList) {

    }

    public void filterMatches(E[] eArray) {

    }

    public void filterDifference(List<E> eList) {

    }

    public void filterDifference(E[] eArray) {

    }

    public int find(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (items[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(items[i]))
                    return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        Arrays.sort(items, 0, size, comparator);
    }

    public Iterator<E> getIterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {

        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public E getNext() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[current++];
        }

        public void remove() {
            ArrayList.this.remove(--current);
        }
        //TODO
        public void addBefore(E e) {
            add(find(e) - 1, e);
        }

        public void addAfter(E e) {
            add(find(e) + 1, e);
        }
        public void reset(){
            current = 0;
        }
    }
}