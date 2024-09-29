package ru.academits.gerasimenko.binary_tree;

public class BinaryTreeNode<E> {
    private BinaryTreeNode<E> leftChild;
    private BinaryTreeNode<E> rightChild;
    private final E data;

    public BinaryTreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public BinaryTreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }
}