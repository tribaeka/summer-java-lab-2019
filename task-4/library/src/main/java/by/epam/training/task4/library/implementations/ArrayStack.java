package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterable;
import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.Stack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    private int size;
    private E[] stackArray;
    private int top;

    public ArrayStack(int size) {
        this.size = size;
        stackArray = (E[]) new Object[this.size];
        top = -1;
    }

    public void push(E e) {
        if (isFull()){
            grow();
        }
        stackArray[++top] = e;
    }

    public E pop() {
        return stackArray[top--];
    }

    public E peek() {
        return stackArray[top];

    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == size - 1);
    }

    private void grow(int newSize){
        E[] old = stackArray;
        stackArray = (E[]) new Object[newSize];
        for (int i = 0; i < size(); i++) {
            stackArray[i] = old[i];
        }
    }

    private void grow(){
        grow(size + 1);
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
        for (int i = 0; i < stackArray.length; i++){
            if (stackArray[i] != null){
                if (stackArray[i].equals(e)){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int clear() {
        final Object[] es = stackArray;
        int count = 0;
        for (int to = size, i = size = 0; i < to; i++){
            es[i] = null;
            count++;
        }
        return count;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort(Comparator<E> comparator) {
        Arrays.sort(stackArray, comparator);
    }

    @Override
    public Iterator getIterator() {
        return new ArrayStackIterator(this);
    }

    private class ArrayStackIterator implements Iterator<E> {
        private int cursor;
        private ArrayStack arrayStack;
        private ArrayStack restoreStack;
        public ArrayStackIterator(ArrayStack arrayStack) {
            this.cursor = arrayStack.top;
            this.arrayStack = arrayStack;
            this.restoreStack = arrayStack;

        }

        public boolean hasNext() {
            return cursor >= 0;
        }

        public E getNext() {
            cursor--;
            return pop();
        }

        public void remove() {
        }

        public void addBefore(E e) {
            if (isFull()){
                grow();
            }
            E tmpE = stackArray[top];
            stackArray[top] = e;
            top++; cursor++;
            stackArray[top] = tmpE;

        }

        public void addAfter(E e) {
            push(e);
        }
        public void reset(){
            cursor = restoreStack.top;
            arrayStack = restoreStack;
        }
    }
}
