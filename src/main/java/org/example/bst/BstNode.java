package org.example.bst;

import java.util.ArrayList;
import java.util.List;

public class BstNode<T extends Comparable<T>> {

    private static final BstNode EMPTY_NODE = new BstNode(null);

    private final T value;
    private BstNode<T> left;
    private BstNode<T> right;

    public BstNode(final T value) {
        this.value = value;
        this.left = ofEmpty();
        this.right = ofEmpty();
    }

    static <T extends Comparable<T>> BstNode<T> ofEmpty() {
        return (BstNode<T>) EMPTY_NODE;
    }

    boolean addChild(final BstNode<T> node) {
        if (this.isBiggerThan(node)) {
            if (left == EMPTY_NODE) {
                left = node;
            }
            return left.addChild(node);
        }

        if (this.isSmallerThan(node)) {
            if (right == EMPTY_NODE) {
                right = node;
            }
            return right.addChild(node);
        }

        return false;
    }

    BstNode<T> find(final T value) {
        if (this == EMPTY_NODE) {
            return this;
        }

        if (this.isBiggerThan(value)) {
            return left.find(value);
        }
        if (this.isSmallerThan(value)) {
            return right.find(value);
        }

        return this;
    }

    List<T> getOrderedValues() {
        final List<T> result = new ArrayList<>();
        this.addValuesInOrder(result);
        return List.copyOf(result);
    }

    private void addValuesInOrder(final List<T> values) {
        if (this == EMPTY_NODE) {
            return;
        }

        left.addValuesInOrder(values);
        values.add(value);
        right.addValuesInOrder(values);
    }

    private boolean isBiggerThan(final BstNode<T> node) {
        return isBiggerThan(node.value);
    }

    private boolean isSmallerThan(final BstNode<T> node) {
        return isSmallerThan(node.value);
    }

    private boolean isBiggerThan(final T value) {
        return this.value.compareTo(value) > 0;
    }

    private boolean isSmallerThan(final T value) {
        return this.value.compareTo(value) < 0;
    }

    public T getValue() {
        return value;
    }

    public BstNode<T> getLeft() {
        return left;
    }

    public BstNode<T> getRight() {
        return right;
    }
}
