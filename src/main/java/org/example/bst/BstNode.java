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

    void addChild(final BstNode<T> node) {
        if (this.isBiggerThan(node)) {
            if (left.isEmpty()) {
                left = node;
                return;
            }
            left.addChild(node);
            return;
        }

        if (this.isSmallerThan(node)) {
            if (right.isEmpty()) {
                right = node;
                return;
            }
            right.addChild(node);
        }
    }

    BstNode<T> find(final T value) {
        if (isEmpty()) {
            return this;
        }

        if (this.isValueBiggerThan(value)) {
            return left.find(value);
        }
        if (this.isValueSmallerThan(value)) {
            return right.find(value);
        }

        return this;
    }

    List<T> getOrderedValues() {
        final List<T> result = new ArrayList<>();
        this.addValuesInOrder(result);
        return List.copyOf(result);
    }

    BstNode<T> removeAndGetResult(final T value) {
        if (isEmpty()) {
            return this;
        }

        if (isValueBiggerThan(value)) {
            left = left.removeAndGetResult(value);
            return this;
        }

        if (isValueSmallerThan(value)) {
            right = right.removeAndGetResult(value);
            return this;
        }

        if (left.isEmpty()) {
            return right;
        }

        if (right.isEmpty()) {
            return left;
        }

        return removeAndGetReplacingNode();
    }

    boolean isEmpty() {
        return this == EMPTY_NODE;
    }

    private void addValuesInOrder(final List<T> values) {
        if (isEmpty()) {
            return;
        }

        left.addValuesInOrder(values);
        values.add(value);
        right.addValuesInOrder(values);
    }

    private BstNode<T> removeAndGetReplacingNode() {
        final BstNode<T> replacingNode = left.findBiggestChild();
        replacingNode.left = left.removeAndGetResult(replacingNode.getValue());
        replacingNode.right = right;
        return replacingNode;
    }

    private BstNode<T> findBiggestChild() {
        if (right.isEmpty()) {
            return this;
        }
        return right.findBiggestChild();
    }

    private boolean isBiggerThan(final BstNode<T> node) {
        return isValueBiggerThan(node.value);
    }

    private boolean isSmallerThan(final BstNode<T> node) {
        return isValueSmallerThan(node.value);
    }

    private boolean isValueBiggerThan(final T value) {
        return this.value.compareTo(value) > 0;
    }

    private boolean isValueSmallerThan(final T value) {
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
