package ru.academits.gerasimenko.binary_search_tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private BinarySearchTreeNode<E> root;
    private int size;

    private final Comparator<E> comparator;

    public BinarySearchTree() {
        comparator = null;
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            return data2 == null ? 0 : -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<E>) data1).compareTo(data2);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(E data) {
        if (root == null) {
            root = new BinarySearchTreeNode<>(data);
            ++size;
            return;
        }

        BinarySearchTreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new BinarySearchTreeNode<>(data));
                    ++size;
                    return;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new BinarySearchTreeNode<>(data));
                    ++size;
                    return;
                }
            }
        }
    }

    public boolean contains(E data) {
        BinarySearchTreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            currentNode = comparisonResult < 0
                    ? currentNode.getLeftChild()
                    : currentNode.getRightChild();
        }

        return false;
    }

    public boolean remove(E data) {
        BinarySearchTreeNode<E> removedNode = root;
        BinarySearchTreeNode<E> removedNodeParent = null;

        while (removedNode != null) {
            int comparisonResult = compare(data, removedNode.getData());

            if (comparisonResult == 0) {
                break;
            }

            removedNodeParent = removedNode;
            removedNode = comparisonResult < 0
                    ? removedNode.getLeftChild()
                    : removedNode.getRightChild();
        }

        if (removedNode == null) {
            return false;
        }

        --size;

        BinarySearchTreeNode<E> removedNodeLeftChild = removedNode.getLeftChild();
        BinarySearchTreeNode<E> removedNodeRightChild = removedNode.getRightChild();

        if (removedNodeLeftChild == null || removedNodeRightChild == null) {
            BinarySearchTreeNode<E> removedNodeSignificantChild = removedNodeLeftChild != null
                    ? removedNodeLeftChild
                    : removedNodeRightChild;

            remove(removedNodeParent, removedNode, removedNodeSignificantChild);
            return true;
        }

        BinarySearchTreeNode<E> rightSubtreeMinNode = removedNodeRightChild;
        BinarySearchTreeNode<E> rightSubtreeMinNodeParent = removedNode;

        while (rightSubtreeMinNode.getLeftChild() != null) {
            rightSubtreeMinNodeParent = rightSubtreeMinNode;
            rightSubtreeMinNode = rightSubtreeMinNode.getLeftChild();
        }

        rightSubtreeMinNode.setLeftChild(removedNodeLeftChild);
        remove(removedNodeParent, removedNode, rightSubtreeMinNode);

        if (removedNodeParent != null && rightSubtreeMinNodeParent != removedNode) {
            rightSubtreeMinNodeParent.setLeftChild(rightSubtreeMinNode.getRightChild());
            rightSubtreeMinNode.setRightChild(removedNodeRightChild);
        }

        return true;
    }

    private void remove(BinarySearchTreeNode<E> removedNodeParent,
                        BinarySearchTreeNode<E> removedNode,
                        BinarySearchTreeNode<E> removedNodeChild) {
        if (removedNodeParent == null) {
            root = removedNodeChild;
            return;
        }

        if (removedNodeParent.getLeftChild() == removedNode) {
            removedNodeParent.setLeftChild(removedNodeChild);
        } else {
            removedNodeParent.setRightChild(removedNodeChild);
        }
    }

    public void breadthFirstSearch(Consumer<E> consumer) {
        if (isEmpty()) {
            return;
        }

        Queue<BinarySearchTreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinarySearchTreeNode<E> node = queue.remove();
            consumer.accept(node.getData());

            BinarySearchTreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                queue.add(nodeLeftChild);
            }

            BinarySearchTreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                queue.add(nodeRightChild);
            }
        }
    }

    public void depthFirstSearchRecursively(Consumer<E> consumer) {
        depthFirstSearchRecursively(root, consumer);
    }

    private void depthFirstSearchRecursively(BinarySearchTreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());
        depthFirstSearchRecursively(node.getLeftChild(), consumer);
        depthFirstSearchRecursively(node.getRightChild(), consumer);
    }

    public void depthFirstSearch(Consumer<E> consumer) {
        if (isEmpty()) {
            return;
        }

        Deque<BinarySearchTreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinarySearchTreeNode<E> node = stack.pop();
            consumer.accept(node.getData());

            BinarySearchTreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                stack.push(nodeRightChild);
            }

            BinarySearchTreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                stack.push(nodeLeftChild);
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "<>";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('<');

        final String separator = ", ";
        breadthFirstSearch(e -> stringBuilder.append(e).append(separator));

        int stringBuilderLength = stringBuilder.length();
        stringBuilder.delete(stringBuilderLength - separator.length(), stringBuilderLength);

        return stringBuilder.append('>').toString();
    }
}