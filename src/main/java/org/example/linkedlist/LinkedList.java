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
        node.setValue(element);
    }

    public E removeFirst() {
        checkElementExists();
        final E removedValue = head.getValue();
        final Node<E> nodeToBeHead = head.next;
        nodeToBeHead.prev = Node.ofEmpty();
        head = nodeToBeHead;
        numberOfElements--;
        return removedValue;
    }

    public E removeLast() {
        checkElementExists();
        final E removedValue = tail.getValue();
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
        final E removedValue = removedNode.getValue();
        final Node<E> prevToRemoved = removedNode.prev;
        final Node<E> nextToRemoved = removedNode.next;
        prevToRemoved.next = nextToRemoved;
        nextToRemoved.prev = prevToRemoved;
        numberOfElements--;
        return removedValue;
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
        final Node<E> found = getNode(index);
        return found.getValue();
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
}
