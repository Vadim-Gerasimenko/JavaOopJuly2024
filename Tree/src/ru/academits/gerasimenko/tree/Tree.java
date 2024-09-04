package ru.academits.gerasimenko.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public Tree() {
    }

    public int getSize() {
        return size;
    }

    public void insert(E data) {
        validateTreeNodeData(data);
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                    continue;
                }

                currentNode.setLeftChild(new TreeNode<>(data));
                ++size;
                return;
            }

            if (currentNode.getRightChild() != null) {
                currentNode = currentNode.getRightChild();
                continue;
            }

            currentNode.setRightChild(new TreeNode<>(data));
            ++size;
            return;
        }

        root = new TreeNode<>(data);
        ++size;
    }

    public TreeNode<E> search(E data) {
        validateTreeNodeData(data);
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            if (data.equals(currentNode.getData())) {
                return currentNode;
            }

            if (data.compareTo(currentNode.getData()) < 0) {
                currentNode = currentNode.getLeftChild();
                continue;
            }

            currentNode = currentNode.getRightChild();
        }

        return null;
    }

    public boolean remove(E data) {
        validateTreeNodeData(data);

        TreeNode<E> removedNode = root;
        TreeNode<E> removedNodeParent = null;

        while (removedNode != null) {
            if (data.equals(removedNode.getData())) {
                break;
            }

            if (data.compareTo(removedNode.getData()) < 0) {
                removedNodeParent = removedNode;
                removedNode = removedNode.getLeftChild();
                continue;
            }

            removedNodeParent = removedNode;
            removedNode = removedNode.getRightChild();
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
            root = removedNode.getRightChild();
            --size;
            return true;
        }

        if (rightSubtreeMinNodeParent == removedNode) {
            rightSubtreeMinNode.setLeftChild(removedNodeLeftChild);
        } else {
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

    private static <E extends Comparable<E>> void printNodeData(TreeNode<E> node) {
        System.out.print(node.getData() + " ");
    }

    private static <E extends Comparable<E>> void validateTreeForNull(Tree<E> tree) {
        if (tree == null) {
            throw new NullPointerException("Tree should not refer to null.");
        }
    }

    private static <E extends Comparable<E>> void validateTreeNodeData(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Tree node data should not be null.");
        }
    }

    public static <E extends Comparable<E>> void breadthFirstSearch(Tree<E> tree) {
        validateTreeForNull(tree);

        if (tree.getSize() == 0) {
            return;
        }

        System.out.println("BFS and print nodes data:");

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(tree.root);

        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.remove();
            printNodeData(node);

            TreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                queue.add(nodeLeftChild);
            }

            TreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                queue.add(nodeRightChild);
            }
        }

        System.out.println();
    }

    public static <E extends Comparable<E>> void depthFirstSearchRecursively(Tree<E> tree) {
        validateTreeForNull(tree);

        System.out.println("Recursive DFS and print nodes data:");
        visitTreeNodesRecursively(tree.root);
        System.out.println();
    }

    private static <E extends Comparable<E>> void visitTreeNodesRecursively(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        printNodeData(node);
        visitTreeNodesRecursively(node.getLeftChild());
        visitTreeNodesRecursively(node.getRightChild());
    }

    public static <E extends Comparable<E>> void depthFirstSearch(Tree<E> tree) {
        validateTreeForNull(tree);

        if (tree.getSize() == 0) {
            return;
        }

        System.out.println("DFS and print nodes data:");

        Deque<TreeNode<E>> stack = new LinkedList<>();
        stack.addFirst(tree.root);

        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.removeFirst();
            printNodeData(node);

            TreeNode<E> nodeRightChild = node.getRightChild();

            if (nodeRightChild != null) {
                stack.addFirst(nodeRightChild);
            }

            TreeNode<E> nodeLeftChild = node.getLeftChild();

            if (nodeLeftChild != null) {
                stack.addFirst(nodeLeftChild);
            }
        }

        System.out.println();
    }
}