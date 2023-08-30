package org.example.stack;

import java.util.EmptyStackException;

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
        checkEmpty();
        numberOfElements--;
        return elements[numberOfElements];
    }

    @Override
    public E peek() {
        checkEmpty();
        return elements[numberOfElements - 1];
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
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

    private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
    }
}
