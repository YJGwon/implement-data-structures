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
        head = nodeToAdd;
        if (isEmpty()) {
            tail = nodeToAdd;
        }
        numberOfElements++;
    }

    public void addLast(final E element) {
        final Node<E> nodeToAdd = new Node<>(element, Node.ofEmpty());
        if (isEmpty()) {
            head = nodeToAdd;
        } else {
            tail.next = nodeToAdd;
        }
        tail = nodeToAdd;
        numberOfElements++;
    }

    public E getFirst() {
        checkElementExists();
        return head.getValue();
    }

    public E getLast() {
        checkElementExists();
        return tail.getValue();
    }

    public E get(final int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.getValue();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return numberOfElements;
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
}
