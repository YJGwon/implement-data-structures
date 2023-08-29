package org.example.linkedlist;

public class Node<T> {

    private static final Node<Object> EMPTY_NODE = new Node<>(null, null);

    private final T value;
    Node<T> next;

    public Node(final T value, final Node<T> next) {
        this.value = value;
        this.next = next;
    }

    static <T> Node<T> ofEmpty() {
        return (Node<T>) EMPTY_NODE;
    }

    T getValue() {
        return value;
    }
}
