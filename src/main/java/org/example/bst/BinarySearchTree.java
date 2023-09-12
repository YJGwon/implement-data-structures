package org.example.bst;

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

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return numberOfNodes;
    }
}
