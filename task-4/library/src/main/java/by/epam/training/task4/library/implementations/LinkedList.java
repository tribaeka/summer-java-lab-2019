package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterable;
import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E>, Iterable<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;

    private Node<E> first;

    private Node<E> last;

    private int maxSize = 8;

    private Comparator<E> comparator = (Comparator<E>) Comparator.naturalOrder();;

    public LinkedList() {
    }

    public LinkedList(Comparator<E> comparator) {
        this.comparator = comparator;
        this.size = 0;
    }
    public LinkedList(Comparator<E> comparator, E[] eArray){
        this(comparator);
        addAll(eArray);
    }

    private void linkFirst(E e) {
        chekMaxSize();
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    int linkLast(E e) {
        chekMaxSize();
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return size;
    }

    void linkBefore(E e, Node<E> succ) {
        chekMaxSize();
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    void linkAfter(E e, Node<E> succ) {
        chekMaxSize();
        final Node<E> pred = succ.next;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.next = newNode;
        if (pred == null)
            last = newNode;
        else
            pred.next = newNode;
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        chekMaxSize();
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }


    private E unlinkLast(Node<E> l) {
        chekMaxSize();
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }


    E unlink(Node<E> x) {
        chekMaxSize();
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    @Override
    public Iterator<E> getIterator() {
        return new ListItr(find(first.item));
    }
    private void sort(){
        E[] eArray = (E[]) toArray();
        clear();
        Arrays.sort(eArray, comparator);
        addAll(eArray);
    }
    public void sort(Comparator<E> eComparator){
        E[] eArray = (E[]) toArray();
        clear();
        Arrays.sort(eArray, eComparator);
        addAll(eArray);
    }
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    @Override
    public int add(E e) {
        return linkLast(e);
    }

    @Override
    public void addAll(E[] eArray) {
        for (E e : eArray) {
            linkLast(e);
        }
    }

    @Override
    public void addAll(List<E> eList) {
        addAll(size, eList);
    }

    public boolean addAll(int index, List<E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        return true;
    }

    @Override
    public E set(int index, E e) {
        E ePast = node(index).item;
        node(index).item = e;
        return ePast;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public E remove(int index) {
        return unlink(node(index));
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int find(E e) {
        int index = 0;
        if (e == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (e.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    public <T> T[] toArray(T[] eArray) {
        if (eArray.length < size)
            return (T[]) Arrays.copyOf(toArray(), size, eArray.getClass());
        System.arraycopy(toArray(), 0, eArray, 0, size);
        if (eArray.length > size)
            eArray[size] = null;
        return eArray;
    }

    @Override
    public void trim() {
        Iterator<E> iterator = getIterator();
        while (iterator.hasNext()){
            if (iterator.getNext() == null){
                iterator.remove();
            }
        }
    }

    @Override
    public void filterMatches(List<E> eList) {
        E[] matchedItems = (E[]) new Object[size];
        int matchedIterator = 0;
        Iterator<E> subIterator = eList.getIterator();
        while (subIterator.hasNext()){
            E subItem = subIterator.getNext();
            Iterator<E> thisIterator = getIterator();
            while (thisIterator.hasNext()){
                E item = thisIterator.getNext();
                if (subItem.equals(item)) {
                    matchedItems[matchedIterator] = item;
                    matchedIterator++;
                }
            }
        }
        for (int i = 0; i < size; i++){
            set(i, matchedItems[i]);
        }
        trim();
    }

    @Override
    public void filterMatches(E[] eArray) {
        E[] matchedItems = (E[]) new Object[size];
        int matchedIterator = 0;
        for (E subItem : eArray){
            Iterator<E> thisIterator = getIterator();
            while (thisIterator.hasNext()){
                E item = thisIterator.getNext();
                if (subItem.equals(item)) {
                    matchedItems[matchedIterator] = item;
                    matchedIterator++;
                }
            }
        }
        for (int i = 0; i < size; i++){
            set(i, matchedItems[i]);
        }
        trim();
    }

    @Override
    public void filterDifference(List<E> eList) {
        Iterator<E> subIterator = eList.getIterator();
        while (subIterator.hasNext()){
            E subItem = subIterator.getNext();
            Iterator<E> thisIterator = getIterator();
            while (thisIterator.hasNext()){
                if (thisIterator.getNext().equals(subItem)){
                    thisIterator.remove();
                }
            }
        }
    }

    @Override
    public void filterDifference(E[] eArray) {
        for (E subItem : eArray){
            Iterator<E> thisIterator = getIterator();
            while (thisIterator.hasNext()){
                if (thisIterator.getNext().equals(subItem)){
                    thisIterator.remove();
                }
            }
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void chekMaxSize(){
        if (size == maxSize){
            throw new IndexOutOfBoundsException();
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    Node<E> node(int index) {

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;

        }
    }

    private class ListItr implements Iterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int resetIndex;

        ListItr(int index) {
            resetIndex = index;
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E getNext() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public E getPrevious() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }

        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.item = e;
        }

        public void addBefore(E e) {
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
        }

        @Override
        public void addAfter(E e) {
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkAfter(e, next);
            nextIndex++;
        }


        @Override
        public void reset() {
            next = (resetIndex == size) ? null : node(resetIndex);
            nextIndex = resetIndex;
        }
    }
}