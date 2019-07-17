package by.epam.training.task4.library.implementations;

import by.epam.training.task4.library.interfaces.Iterator;
import by.epam.training.task4.library.interfaces.Queue;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private static class QueueNode<E> {
        public E data;
        public QueueNode next;
        public QueueNode prev;

        public QueueNode(E data) {
            this(data, null, null);
        }

        public QueueNode(E data, QueueNode next, QueueNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }

    private QueueNode front;
    private QueueNode back;
    private int size;

    public LinkedQueue() {
        this.front = null;
        this.back = this.front;
        this.size = 0;
    }

    private class LinkedQueueIterator implements Iterator<E> {
        private LinkedQueue queue;
        private int position;
        private boolean isRemovable;
        private LinkedQueue resetQueue;

        public LinkedQueueIterator(LinkedQueue queue) {
            this.queue = queue;
            this.position = 0;
            this.isRemovable = false;
            this.resetQueue = queue;
        }

        public E getNext() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E data = (E) LinkedQueue.this.nodeAt(position).data;
            this.position++;
            this.isRemovable = true;
            return data;
        }

        public boolean hasNext() {
            return this.position < this.queue.size();
        }

        public void remove() {
            if (!this.isRemovable) {
                throw new IllegalStateException();
            }
            queue.remove(this.position - 1);
            this.position--;
            this.isRemovable = false;
        }

        @Override
        public void addBefore(E e) {

        }

        @Override
        public void addAfter(E e) {

        }

        @Override
        public void reset() {
            queue = resetQueue;
            position = 0;
            isRemovable = false;
        }
    }

    public QueueNode nodeAt(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == 0) {
            return this.front;
        } else {
            QueueNode current = this.front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
    }

    public void push(E data) {
        if (this.isEmpty()) {
            this.front = new QueueNode(data);
            this.back = this.front;
            this.front.prev = null;
        } else {
            QueueNode next = new QueueNode(data);
            this.back.next = next;
            QueueNode prev = this.back;
            this.back = this.back.next;
            next.prev = prev;
        }
        this.size++;

    }

    public void pushAll(Queue<E> other) {
        int size = other.size();
        for (int i = 0; i < size; i++) {
            E next = other.remove();
            this.push(next);
            other.push(next);
        }
    }
    public void pushAll(E[] eArray) {
        for (E e : eArray){
            push(e);
        }
    }

    public int clear() {
        int hasSize = size;
        this.size = 0;
        this.front = null;
        return hasSize;
    }

    public QueueNode get(Object o) {
        if (this.isEmpty()) {
            return null;
        } else {
            QueueNode current = this.front;
            while (current != null) {
                if (current.data.equals(o)) {
                    return current;
                } else {
                    current = current.next;
                }
            }
            return null;
        }
    }

    @Override
    public E poll() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(size - 1);
    }

    public E pull() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.back.data;
    }


    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) this.front.data;
    }

    public E remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.remove(0);
    }

    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size);
        }
        E data;
        this.size--;
        if (index == 0) {
            data = (E) this.front.data;
            this.front = this.front.next;
            if (this.front != null) {
                this.front.prev = null;
            }
        } else {
            QueueNode current = this.front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = (E) current.next.data;
            current.next = current.next.next;
        }
        return data;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator getIterator() {
        return new LinkedQueueIterator(this);
    }
    public boolean contains(E e) {
        if (this.front == null) {
            return false;
        } else if (this.front.data.equals(e)) {
            return true;
        } else {
            QueueNode current = this.front.next;
            while (current != null) {
                if (current.data.equals(e)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    @Override
    public int search(E e) {
        if (!this.contains(e)) {
            return -1;
        } else {
            int index = 0;
            if (this.front.data.equals(e)) {
                return 0;
            } else {
                QueueNode current = this.front.next;
                while (current != null) {
                    index++;
                    if (current.data.equals(e)) {
                        current = null;
                    } else {
                        current = current.next;
                    }
                }
                return index;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }
}
