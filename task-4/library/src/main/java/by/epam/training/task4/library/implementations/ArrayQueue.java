package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.Queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] queue;
    private int maxSize;
    private int size;
    private int first;
    private int last;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = (E[]) new Object[maxSize];
        last = -1;
        first = 0;
        size = 0;
    }

    public void push(E elem) {
        if (last == maxSize - 1) {
            last = -1;
        }

        queue[++last] = elem;
        size++;
    }
    public E poll() {
        E temp = queue[first++];

        if (first == maxSize) {
            first = 0;
        }
        size--;
        return temp;

    }

    public E remove() {
        E temp = queue[last--];

        if (last == maxSize) {
            last = 0;
        }
        size--;
        return temp;
    }

    @Override
    public Iterator getIterator() {
        return new QueueIterator(this);
    }

    @Override
    public E pull() {
        return queue[last];
    }

    public E peek() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return queue[first];
    }

    @Override
    public void pushAll(Queue<E> eQueue) {
        Iterator iterator = getIterator();
        while (iterator.hasNext()){
            push((E) iterator.getNext());
        }
    }

    @Override
    public void pushAll(E[] eArray) {
        for (E e : eArray){
            push(e);
        }
    }

    @Override
    public int search(E e) {
        for (int i = 0; i < queue.length; i++){
            if (queue[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int clear() {
        for (E e : queue){
            e = null;
        }
        return size;
    }

    public boolean isFull() {
        return (size == maxSize - 1);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    private class QueueIterator implements Iterator<E> {
        private ArrayQueue arrayQueue;

        public QueueIterator(ArrayQueue arrayQueue) {
            this.arrayQueue = arrayQueue;
        }

        public boolean hasNext() {
            return !arrayQueue.isEmpty();
        }

        public E getNext() {
            return poll();
        }

        public void remove() {
            arrayQueue.remove();
        }
        public void addBefore(E e) {

        }

        public void addAfter(E e) {
            push(e);
        }
        public void reset(){
            last = -1;
            first = 0;
        }
    }
}
