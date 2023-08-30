package org.example.stack;

public class FixedStack<E> implements Stack<E> {

    private final E[] elements;
    private int numberOfElements;

    public FixedStack(final int capacity) {
        validateCapacity(capacity);
        this.elements = (E[]) new Object[capacity];
        this.numberOfElements = 0;
    }

    @Override
    public void push(final E element) {
        checkSize();
        elements[numberOfElements] = element;
        numberOfElements++;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    private void validateCapacity(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("스택의 크기는 1 이상이어야 합니다.");
        }
    }

    private void checkSize() {
        if (numberOfElements == elements.length) {
            throw new FullStackException();
        }
    }
}
