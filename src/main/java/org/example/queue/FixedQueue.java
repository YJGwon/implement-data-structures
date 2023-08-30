package org.example.queue;

public class FixedQueue<E> implements Queue<E> {

    private final E[] elements;
    private int numberOfElements;
    private int first;
    private int last;

    public FixedQueue(final int capacity) {
        validateCapacity(capacity);
        this.elements = (E[]) new Object[capacity];
        this.numberOfElements = 0;
        this.first = 0;
        this.last = -1;
    }

    @Override
    public void enqueue(final E element) {
        checkSize();
        last = stepForward(last);
        elements[last] = element;
        numberOfElements++;
    }

    @Override
    public E dequeue() {
        checkEmpty();
        final E removedElement = elements[first];
        first = stepForward(first);
        numberOfElements--;
        return removedElement;
    }

    @Override
    public E peek() {
        checkEmpty();
        return elements[first];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    private void validateCapacity(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("큐의 크기는 1 이상이어야 합니다.");
        }
    }

    private void checkSize() {
        if (size() == elements.length) {
            throw new FullQueueException();
        }
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
    }

    private int stepForward(final int index) {
        return (index + 1) % elements.length;
    }
}
