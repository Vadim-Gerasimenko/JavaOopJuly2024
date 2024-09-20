package ru.academits.gerasimenko.binary_tree.main;

import ru.academits.gerasimenko.shapes.Rectangle;
import ru.academits.gerasimenko.binary_tree.BinaryTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        List<Integer> elements = new LinkedList<>(List.of(8, 3, 10, 1, 0, 6, 14, 4, 7, 13, 30, 25, 23, 24, 35, 27));

        for (Integer element : elements) {
            binaryTree.insert(element);
        }

        binaryTree.insert(null);

        System.out.println("Binary tree nodes count: " + binaryTree.getSize());
        System.out.println();

        System.out.println("BFS and print nodes data:");

        binaryTree.breadthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        final int searchElement = 4;

        if (binaryTree.contains(searchElement)) {
            System.out.println("Search element '" + searchElement + "' in the binary tree.");
        } else {
            System.out.println("Search element '" + searchElement + "' not in the binary tree.");
        }

        final int removedElement = 14;

        if (binaryTree.remove(removedElement)) {
            System.out.println("The element '" + removedElement + "' was removed.");
        } else {
            System.out.println("The element '" + removedElement + "' was not removed.");
        }

        System.out.println("BFS and print nodes data:");

        binaryTree.breadthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        System.out.println("DFS and print nodes data:");

        binaryTree.depthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        BinaryTree<Rectangle> rectanglesBinaryTree = new BinaryTree<>(Comparator.comparingDouble(Rectangle::getArea));

        rectanglesBinaryTree.insert(new Rectangle(10, 9));
        rectanglesBinaryTree.insert(new Rectangle(5, 4));
        rectanglesBinaryTree.insert(null);
        rectanglesBinaryTree.insert(new Rectangle(2, 4));
        rectanglesBinaryTree.insert(new Rectangle(50, 4));

        System.out.println("Recursive DFS and print info about rectangles:");

        AtomicInteger i = new AtomicInteger(1);

        rectanglesBinaryTree.depthFirstSearchRecursively(rectangle -> {
            System.out.println("Rectangle #" + i.getAndIncrement());
            System.out.println(rectangle);
            System.out.println();
        });
    }
}