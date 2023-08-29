package org.example.linkedlist;

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
            tail.linkNext(nodeToAdd);
        }
        tail = nodeToAdd;
        numberOfElements++;
    }

    public E getFirst() {
        return head.getValue();
    }

    public E getLast() {
        return tail.getValue();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return numberOfElements;
    }
}
