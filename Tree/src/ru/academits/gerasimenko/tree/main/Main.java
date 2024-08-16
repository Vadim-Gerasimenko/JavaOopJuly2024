package ru.academits.gerasimenko.tree.main;

import ru.academits.gerasimenko.tree.Tree;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        List<Integer> elements = new LinkedList<>(List.of(8, 3, 10, 1, 6, 14, 4, 7, 13, 30, 25, 23, 24, 35, 27));

        for (Integer element : elements) {
            tree.insert(element);
        }

        System.out.println("Tree nodes count: " + tree.getTreeNodesCount());
        System.out.println();

        Tree.breadthFirstSearch(tree);
        System.out.println();

        Tree.depthFirstSearch(tree);
        System.out.println();

        final int searchElement = 4;

        if (tree.search(searchElement) == null) {
            System.out.println("Search element '" + searchElement + "' not in the tree.");
        } else {
            System.out.println("Search element '" + searchElement + "' in the tree.");
        }

        final int removedElement = 14;

        if (tree.remove(removedElement)) {
            System.out.println("The element '" + removedElement + "' was removed.");
        } else {
            System.out.println("The element '" + removedElement + "' was not removed.");
        }

        System.out.println();

        Tree.depthFirstSearchRecursive(tree);
    }
}