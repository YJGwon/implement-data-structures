package org.example.bst;

import java.util.List;
import java.util.Optional;

public class BinarySearchTree<T extends Comparable<T>> {

    private BstNode<T> root;

    public BinarySearchTree() {
        this.root = BstNode.ofEmpty();
    }

    public void add(final BstNode<T> node) {
        if (root.isEmpty()) {
            root = node;
            return;
        }

        root.addChild(node);
    }

    public Optional<BstNode<T>> search(final T value) {
        final BstNode<T> found = root.find(value);
        if (found.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(found);
    }

    public List<T> getValues() {
        return root.getOrderedValues();
    }

    public void remove(final T value) {
        root = root.removeAndGetResult(value);
    }
}
