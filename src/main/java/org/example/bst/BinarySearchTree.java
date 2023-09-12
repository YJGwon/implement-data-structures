package org.example.bst;

import java.util.List;
import java.util.Optional;

public class BinarySearchTree<T extends Comparable<T>> {

    private BstNode<T> root;
    private int numberOfNodes;

    public BinarySearchTree() {
        this.root = BstNode.ofEmpty();
        this.numberOfNodes = 0;
    }

    public void add(final BstNode<T> node) {
        if (isEmpty()) {
            root = node;
            numberOfNodes++;
            return;
        }

        if (root.addChild(node)) {
            numberOfNodes++;
        }
    }

    public Optional<BstNode<T>> search(final T value) {
        final BstNode<T> found = root.find(value);
        if (found == BstNode.ofEmpty()) {
            return Optional.empty();
        }
        return Optional.of(found);
    }

    public List<T> getValues() {
        return root.getOrderedValues();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return numberOfNodes;
    }
}
