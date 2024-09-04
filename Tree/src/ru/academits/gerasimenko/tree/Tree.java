package ru.academits.gerasimenko.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<E> {
    private TreeNode<E> root;
    private int size;

    private Comparator<E> comparator;

    public Tree() {
    }

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private int compare(Object o1, Object o2) {
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
            if (compare(data, removedNode.getData()) == 0) {
                break;
            }

            if (compare(data, removedNode.getData()) < 0) {
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

    private static <E> void printNodeData(TreeNode<E> node) {
        System.out.print(node.getData() + " ");
    }

    private static <E> void validateTreeForNull(Tree<E> tree) {
        if (tree == null) {
            throw new NullPointerException("Tree should not refer to null.");
        }
    }

    public static <E> void breadthFirstSearch(Tree<E> tree) {
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

    public static <E> void depthFirstSearchRecursively(Tree<E> tree) {
        validateTreeForNull(tree);

        System.out.println("Recursive DFS and print nodes data:");
        visitTreeNodesRecursively(tree.root);
        System.out.println();
    }

    private static <E> void visitTreeNodesRecursively(TreeNode<E> node) {
        if (node == null) {
            return;
        }

        printNodeData(node);
        visitTreeNodesRecursively(node.getLeftChild());
        visitTreeNodesRecursively(node.getRightChild());
    }

    public static <E> void depthFirstSearch(Tree<E> tree) {
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