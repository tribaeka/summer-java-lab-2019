package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.Stack;

import java.util.Comparator;

public class LinkedStack<E> implements Stack<E> {
    LinkedList<E> stack = new LinkedList<E>();
    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    public void push(E value) {
        stack.linkLast(value);
    }

    public E pop() {
        E value = peek();
        stack.removeLast();
        return value;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.getLast();
    }

    public void poke(E value) {
        if (!isEmpty()) {
            stack.removeLast();
        }
        push(value);
    }

    public int clear() {
        stack.clear();
        return size();
    }


    @Override
    public Iterator getIterator() {
        return new LinkedStackIterator(this);
    }

    private class LinkedStackIterator implements Iterator<E> {
        private LinkedStack linkedStack;
        private LinkedStack restoreStack;
        public LinkedStackIterator(LinkedStack linkedStack) {
            this.linkedStack = linkedStack;
            this.restoreStack = linkedStack;

        }

        public boolean hasNext() {
            return !linkedStack.isEmpty();
        }

        public E getNext() {
            return pop();
        }

        public void remove() {
        }

        public void addBefore(E e) {
           linkedStack.stack.linkBefore(e, linkedStack.stack.node(linkedStack.stack.find(linkedStack.stack.getLast())));

        }

        public void addAfter(E e) {
            push(e);
        }
        public void reset(){
            linkedStack = restoreStack;
        }
    }

    @Override
    public void pushAll(Stack<E> eStack) {
        Iterator<E> iterator = eStack.getIterator();
        while (iterator.hasNext()){
            push(iterator.getNext());
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
        return stack.find(e);
    }

    @Override
    public void sort(Comparator<E> comparator) {
        stack.sort(comparator);
    }
}
