package org.example.queue;

public class FlexibleQueue<E> implements Queue<E> {

    private Node<E> tail;
    private int numberOfElements;

    public FlexibleQueue() {
        this.tail = Node.ofEmpty();
        this.numberOfElements = 0;
    }

    @Override
    public void enqueue(final E element) {
        final Node<E> nodeToAdd = new Node<>(element, tail.next);
        if (isEmpty()) {
            nodeToAdd.next = nodeToAdd;
        } else {
            tail.next = nodeToAdd;
        }
        tail = nodeToAdd;
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

    private static class Node<E> {

        private static final Node<Object> EMPTY_NODE = new Node<>(null, null);

        final E value;
        Node<E> next;

        private Node(final E value, final Node<E> next) {
            this.value = value;
            this.next = next;
        }

        static <E> Node<E> ofEmpty() {
            return (Node<E>) EMPTY_NODE;
        }
    }
}
