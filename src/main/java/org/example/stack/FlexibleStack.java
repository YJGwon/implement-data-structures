package org.example.stack;

import java.util.EmptyStackException;

public class FlexibleStack<E> implements Stack<E> {

    private Node<E> tail;
    private int numberOfElements;

    public FlexibleStack() {
        this.tail = Node.ofEmpty();
        this.numberOfElements = 0;
    }

    @Override
    public void push(final E element) {
        tail = new Node<>(element, tail);
        numberOfElements++;
    }

    @Override
    public E pop() {
        checkEmpty();
        final Node<E> removedNode = tail;
        tail = tail.prev;
        numberOfElements--;
        return removedNode.value;
    }

    @Override
    public E peek() {
        checkEmpty();
        return tail.value;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
    }

    private static class Node<E> {

        private static final Node<Object> EMPTY_NODE = new Node<>(null, null);

        final E value;
        final Node<E> prev;

        private Node(final E value, final Node<E> prev) {
            this.value = value;
            this.prev = prev;
        }

        static <E> Node<E> ofEmpty() {
            return (Node<E>) EMPTY_NODE;
        }
    }
}
