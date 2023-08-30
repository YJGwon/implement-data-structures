package org.example.queue;

public class FixedQueue<E> implements Queue<E> {

    private final E[] elements;
    private int numberOfElements;
    private int first;
    private int last;

    public FixedQueue(final int capacity) {
        this.elements = (E[]) new Object[capacity];
        this.numberOfElements = 0;
        this.first = -1;
        this.last = -1;
    }

    @Override
    public void enqueue(final E element) {
        checkSize();
        last = (last + 1) % elements.length;
        elements[last] = element;
        numberOfElements++;
    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    private void checkSize() {
        if (size() == elements.length) {
            throw new FullQueueException();
        }
    }
}
