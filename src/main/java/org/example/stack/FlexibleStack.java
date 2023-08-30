package org.example.stack;

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
