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

    public Comparator<E> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
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
        E[] matchedItems = (E[]) new Object[size];
        int matchedIterator = 0;
        Iterator<E> subIterator = eList.getIterator();
        while (subIterator.hasNext()){
            Iterator<E> thisIterator = getIterator();
            E subItem = subIterator.getNext();
            while (thisIterator.hasNext()) {
                E item = thisIterator.getNext();
                if (subItem.equals(item)) {
                    matchedItems[matchedIterator] = item;
                    matchedIterator++;
                }
            }
        }
        this.items = matchedItems;
        this.trim();
    }

    public void filterMatches(E[] eArray) {
        E[] matchedItems = (E[]) new Object[size];
        int matchedIterator = 0;
        for (E subItem : eArray) {
            Iterator<E> thisIterator = getIterator();
            while (thisIterator.hasNext()) {
                E item = thisIterator.getNext();
                if (subItem.equals(item)) {
                    matchedItems[matchedIterator] = item;
                    matchedIterator++;
                }
            }
        }
        this.items = matchedItems;
        this.trim();
    }

    public void filterDifference(List<E> eList) {
        Iterator<E> inIterator = eList.getIterator();
        while (inIterator.hasNext()){
            Iterator<E> mainIterator = getIterator();
            E item = inIterator.getNext();
            while (mainIterator.hasNext()){
                if (item.equals(mainIterator.getNext())){
                    mainIterator.remove();
                }
            }
        }
    }

    public void filterDifference(E[] eArray) {
        for (E e : eArray) {
            Iterator<E> mainIterator = getIterator();
            while (mainIterator.hasNext()) {
                if (e.equals(mainIterator.getNext())) {
                    mainIterator.remove();
                }
            }
        }
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

        private int cursor = 0;
        private int lastRet = -1;

        public boolean hasNext() {
            return cursor < size();
        }

        public E getNext() {
            //System.out.println("getNext->");
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                //System.out.println(lastRet+"in get");
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            //System.out.println("remove->");
            //System.out.println(lastRet+" in remove");
            if (lastRet < 0)
                throw new IllegalStateException();
            //System.out.println("removing "+lastRet);
                ArrayList.this.remove(lastRet);
            //System.out.println("before if block "+lastRet+"_"+cursor);
                if (lastRet < cursor){
                    cursor--;
                    //System.out.println("cursor after if block "+cursor);
                }
                lastRet = -1;

        }
        //TODO
        public void addBefore(E e) {
            add(find(e) - 1, e);
        }

        public void addAfter(E e) {
            add(find(e) + 1, e);
        }
        public void reset(){
            cursor = 0;
        }
    }
}