package org.example.linkedlist;

import java.util.NoSuchElementException;

public class LinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int numberOfElements;

    public LinkedList() {
        this.head = Node.ofEmpty();
        this.tail = Node.ofEmpty();
        this.numberOfElements = 0;
    }

    public void addFirst(final E element) {
        final Node<E> nodeToAdd = new Node<>(element, head);
        if (isEmpty()) {
            tail = nodeToAdd;
        } else {
            head.prev = nodeToAdd;
        }
        head = nodeToAdd;
        numberOfElements++;
    }

    public void addLast(final E element) {
        final Node<E> nodeToAdd = new Node<>(element, tail, Node.ofEmpty());
        if (isEmpty()) {
            head = nodeToAdd;
        } else {
            tail.next = nodeToAdd;
        }
        tail = nodeToAdd;
        numberOfElements++;
    }

    public void add(final int index, final E element) {
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size()) {
            addLast(element);
            return;
        }

        final Node<E> old = getNode(index);
        final Node<E> prev = old.prev;
        final Node<E> nodeToAdd = new Node<>(element, prev, old);
        prev.next = nodeToAdd;
        old.prev = nodeToAdd;
        numberOfElements++;
    }

    public void set(final int index, final E element) {
        final Node<E> node = getNode(index);
        node.value = element;
    }

    public E removeFirst() {
        checkElementExists();
        final E removedValue = head.value;
        final Node<E> nodeToBeHead = head.next;
        nodeToBeHead.prev = Node.ofEmpty();
        head = nodeToBeHead;
        numberOfElements--;
        return removedValue;
    }

    public E removeLast() {
        checkElementExists();
        final E removedValue = tail.value;
        final Node<E> nodeToBeTail = tail.prev;
        nodeToBeTail.next = Node.ofEmpty();
        tail = nodeToBeTail;
        numberOfElements--;
        return removedValue;
    }

    public E remove(final int index) {
        if (index == 0) {
            return removeFirst();
        }
        if (index == size() - 1) {
            return removeLast();
        }

        final Node<E> removedNode = getNode(index);
        final E removedValue = removedNode.value;
        final Node<E> prevToRemoved = removedNode.prev;
        final Node<E> nextToRemoved = removedNode.next;
        prevToRemoved.next = nextToRemoved;
        nextToRemoved.prev = prevToRemoved;
        numberOfElements--;
        return removedValue;
    }

    public E getFirst() {
        checkElementExists();
        return head.value;
    }

    public E getLast() {
        checkElementExists();
        return tail.value;
    }

    public E get(final int index) {
        final Node<E> found = getNode(index);
        return found.value;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return numberOfElements;
    }

    private Node<E> getNode(final int index) {
        checkIndex(index);
        Node<E> current;
        if (index < size() / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size() - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void checkElementExists() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private void checkIndex(final int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node<T> {

        private static final Node<Object> EMPTY_NODE = new Node<>(null, null);

        T value;
        Node<T> prev;
        Node<T> next;

        public Node(final T value, final Node<T> next) {
            this(value, ofEmpty(), next);
        }

        public Node(final T value, final Node<T> prev, final Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        static <T> Node<T> ofEmpty() {
            return (Node<T>) EMPTY_NODE;
        }
    }
}
