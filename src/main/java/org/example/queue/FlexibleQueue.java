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
        checkEmpty();
        final Node<E> removedNode = tail.next;
        if (removedNode == tail) {
            tail = Node.ofEmpty();
        } else {
            tail.next = removedNode.next;
        }
        numberOfElements--;
        return removedNode.value;
    }

    @Override
    public E peek() {
        checkEmpty();
        final Node<E> head = tail.next;
        return head.value;
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
            throw new EmptyQueueException();
        }
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
