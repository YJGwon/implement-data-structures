package org.example.bst;

public class BstNode<T extends Comparable<T>> {

    private static final BstNode EMPTY_NODE = new BstNode(null);

    final T value;
    BstNode<T> left;
    BstNode<T> right;

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

    private boolean isBiggerThan(final BstNode<T> node) {
        return this.value.compareTo(node.value) > 0;
    }

    private boolean isSmallerThan(final BstNode<T> node) {
        return this.value.compareTo(node.value) < 0;
    }
}
