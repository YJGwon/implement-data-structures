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
        if (head.equals(Node.ofEmpty())) {
            tail = nodeToAdd;
        }
        numberOfElements++;
    }

    public E getFirst() {
        return head.getValue();
    }
}
