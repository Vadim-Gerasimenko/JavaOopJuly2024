package ru.academits.gerasimenko.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<E> {
    private TreeNode<E> root;
    private int size;

    private Comparator<E> comparator;

    public Tree() {
    }

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int compare(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            return o1 == o2 ? 0 : (o1 == null ? -1 : 1);
        }

        //noinspection unchecked
        return comparator == null
                ? ((Comparable<E>) o1).compareTo((E) o2)
                : comparator.compare((E) o1, (E) o2);
    }

    public int getSize() {
        return size;
    }

    public void insert(E data) {
        if (root == null) {
            root = new TreeNode<>(data);
            ++size;
            return;
        }

        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(new TreeNode<>(data));
                    ++size;
                    return;
                }
            } else {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(new TreeNode<>(data));
                    ++size;
                    return;
                }
            }
        }
    }

    public boolean contains(E data) {
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (compare(data, currentNode.getData()) == 0) {
                return true;
            }

            currentNode = compare(data, currentNode.getData()) < 0
                    ? currentNode.getLeftChild()
                    : currentNode.getRightChild();
        }

        return false;
    }

    public boolean remove(E data) {
        TreeNode<E> removedNode = root;
        TreeNode<E> removedNodeParent = null;

        while (removedNode != null) {
            int nodesDataComparisonResult = compare(data, removedNode.getData());

            if (nodesDataComparisonResult == 0) {
                break;
            }

            removedNodeParent = removedNode;
            removedNode = nodesDataComparisonResult < 0
                    ? removedNode.getLeftChild()
                    : removedNode.getRightChild();
        }

        if (removedNode == null) {
            return false;
        }

        TreeNode<E> removedNodeLeftChild = removedNode.getLeftChild();
        TreeNode<E> removedNodeRightChild = removedNode.getRightChild();

        if (removedNodeLeftChild == null || removedNodeRightChild == null) {
            TreeNode<E> removedNodeSignificantChild = removedNodeLeftChild != null
                    ? removedNodeLeftChild
                    : removedNodeRightChild;

            if (removedNodeParent == null) {
                root = removedNodeSignificantChild;
                --size;
                return true;
            }

            if (removedNodeParent.getLeftChild() == removedNode) {
                removedNodeParent.setLeftChild(removedNodeSignificantChild);
            } else {
                removedNodeParent.setRightChild(removedNodeSignificantChild);
            }

            --size;
            return true;
        }

        TreeNode<E> rightSubtreeMinNode = removedNodeRightChild;
        TreeNode<E> rightSubtreeMinNodeParent = removedNode;

        while (rightSubtreeMinNode.getLeftChild() != null) {
            rightSubtreeMinNodeParent = rightSubtreeMinNode;
            rightSubtreeMinNode = rightSubtreeMinNode.getLeftChild();
        }

        rightSubtreeMinNode.setLeftChild(removedNodeLeftChild);

        if (removedNodeParent == null) {
            root = removedNodeRightChild;
            --size;
            return true;
        }

        if (rightSubtreeMinNodeParent != removedNode) {
            rightSubtreeMinNodeParent.setLeftChild(rightSubtreeMinNode.getRightChild());
            rightSubtreeMinNode.setRightChild(removedNodeRightChild);
        }

        if (removedNodeParent.getLeftChild() == removedNode) {
            removedNodeParent.setLeftChild(rightSubtreeMinNode);
        } else {
            removedNodeParent.setRightChild(rightSubtreeMinNode);
        }

        --size;
        return true;
    }

    public void breadthFirstSearch(Consumer<E> consumer) {
        if (getSize() == 0) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.remove();
            consumer.accept(node.getData());

            TreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                queue.add(nodeLeftChild);
            }

            TreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                queue.add(nodeRightChild);
            }
        }
    }

    public void depthFirstSearchRecursively(Consumer<E> consumer) {
        visitTreeNodesRecursively(root, consumer);
    }

    private static <E> void visitTreeNodesRecursively(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());
        visitTreeNodesRecursively(node.getLeftChild(), consumer);
        visitTreeNodesRecursively(node.getRightChild(), consumer);
    }

    public void depthFirstSearch(Consumer<E> consumer) {
        if (getSize() == 0) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            consumer.accept(node.getData());

            TreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                stack.push(nodeRightChild);
            }

            TreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                stack.push(nodeLeftChild);
            }
        }
    }
}