package ru.academits.gerasimenko.tree.main;

import ru.academits.gerasimenko.shapes.Rectangle;
import ru.academits.gerasimenko.tree.Tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        List<Integer> elements = new LinkedList<>(List.of(8, 3, 10, 1, 0, 6, 14, 4, 7, 13, 30, 25, 23, 24, 35, 27));

        for (Integer element : elements) {
            tree.insert(element);
        }

        tree.insert(null);

        System.out.println("Tree nodes count: " + tree.getSize());
        System.out.println();

        System.out.println("BFS and print nodes data:");

        tree.breadthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        final int searchElement = 4;

        if (tree.contains(searchElement)) {
            System.out.println("Search element '" + searchElement + "' in the tree.");
        } else {
            System.out.println("Search element '" + searchElement + "' not in the tree.");
        }

        final int removedElement = 14;

        if (tree.remove(removedElement)) {
            System.out.println("The element '" + removedElement + "' was removed.");
        } else {
            System.out.println("The element '" + removedElement + "' was not removed.");
        }

        System.out.println("BFS and print nodes data:");

        tree.breadthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        System.out.println("DFS and print nodes data:");

        tree.depthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        System.out.println();

        Tree<Rectangle> rectanglesTree = new Tree<>(Comparator.comparingDouble(Rectangle::getArea));

        rectanglesTree.insert(new Rectangle(10, 9));
        rectanglesTree.insert(new Rectangle(5, 4));
        rectanglesTree.insert(null);
        rectanglesTree.insert(new Rectangle(2, 4));
        rectanglesTree.insert(new Rectangle(50, 4));

        System.out.println("Recursive DFS and print info about rectangles:");

        AtomicInteger i = new AtomicInteger(1);

        rectanglesTree.depthFirstSearchRecursively(rectangle -> {
            System.out.println("Rectangle #" + i.getAndIncrement());
            System.out.println(rectangle);
            System.out.println();
        });
    }
}