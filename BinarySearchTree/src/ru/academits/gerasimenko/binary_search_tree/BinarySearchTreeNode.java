package ru.academits.gerasimenko.binary_search_tree;

public class BinarySearchTreeNode<E> {
    private BinarySearchTreeNode<E> leftChild;
    private BinarySearchTreeNode<E> rightChild;
    private final E data;

    public BinarySearchTreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public BinarySearchTreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinarySearchTreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public BinarySearchTreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinarySearchTreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }
}