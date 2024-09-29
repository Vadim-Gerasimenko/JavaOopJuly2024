package ru.academits.gerasimenko.binary_tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private BinaryTreeNode<E> root;
    private int size;

    private final Comparator<E> comparator;

    public BinaryTree() {
        comparator = null;
    }

    public BinaryTree(Comparator<E> comparator) {
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
            root = new BinaryTreeNode<>(data);
            ++size;
            return;
        }

        BinaryTreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new BinaryTreeNode<>(data));
                    ++size;
                    return;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new BinaryTreeNode<>(data));
                    ++size;
                    return;
                }
            }
        }
    }

    public boolean contains(E data) {
        BinaryTreeNode<E> currentNode = root;

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
        BinaryTreeNode<E> removedNode = root;
        BinaryTreeNode<E> removedNodeParent = null;

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

        BinaryTreeNode<E> removedNodeLeftChild = removedNode.getLeftChild();
        BinaryTreeNode<E> removedNodeRightChild = removedNode.getRightChild();

        if (removedNodeLeftChild == null || removedNodeRightChild == null) {
            BinaryTreeNode<E> removedNodeSignificantChild = removedNodeLeftChild != null
                    ? removedNodeLeftChild
                    : removedNodeRightChild;

            remove(removedNodeParent, removedNode, removedNodeSignificantChild);
            return true;
        }

        BinaryTreeNode<E> rightSubtreeMinNode = removedNodeRightChild;
        BinaryTreeNode<E> rightSubtreeMinNodeParent = removedNode;

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

    private void remove(BinaryTreeNode<E> removedNodeParent,
                        BinaryTreeNode<E> removedNode,
                        BinaryTreeNode<E> nextNode) {
        if (removedNodeParent == null) {
            root = nextNode;
            return;
        }

        if (removedNodeParent.getLeftChild() == removedNode) {
            removedNodeParent.setLeftChild(nextNode);
        } else {
            removedNodeParent.setRightChild(nextNode);
        }
    }

    public void breadthFirstSearch(Consumer<E> consumer) {
        if (isEmpty()) {
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.remove();
            consumer.accept(node.getData());

            BinaryTreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                queue.add(nodeLeftChild);
            }

            BinaryTreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                queue.add(nodeRightChild);
            }
        }
    }

    public void depthFirstSearchRecursively(Consumer<E> consumer) {
        depthFirstSearchRecursively(root, consumer);
    }

    private void depthFirstSearchRecursively(BinaryTreeNode<E> node, Consumer<E> consumer) {
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

        Deque<BinaryTreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<E> node = stack.pop();
            consumer.accept(node.getData());

            BinaryTreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                stack.push(nodeRightChild);
            }

            BinaryTreeNode<E> nodeLeftChild = node.getLeftChild();

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