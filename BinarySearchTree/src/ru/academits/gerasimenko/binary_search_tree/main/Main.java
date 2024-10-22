package ru.academits.gerasimenko.binary_search_tree.main;

import ru.academits.gerasimenko.shapes.Rectangle;
import ru.academits.gerasimenko.binary_search_tree.BinarySearchTree;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        List<Integer> elements = List.of(8, 3, 10, 1, 0, 6, 14, 4, 7, 13, 30, 25, 23, 24, 35, 27);

        for (Integer element : elements) {
            binarySearchTree.insert(element);
        }

        binarySearchTree.insert(null);

        System.out.println("Binary search tree nodes count: " + binarySearchTree.getSize());
        System.out.printf("Nodes data: " + binarySearchTree + "%n%n");

        final int searchElement = 4;

        if (binarySearchTree.contains(searchElement)) {
            System.out.println("Search element '" + searchElement + "' in the binary search tree.");
        } else {
            System.out.println("Search element '" + searchElement + "' not in the binary search tree.");
        }

        final int removedElement = 14;

        if (binarySearchTree.remove(removedElement)) {
            System.out.println("The element '" + removedElement + "' was removed.");
        } else {
            System.out.println("The element '" + removedElement + "' was not removed.");
        }

        System.out.println("BFS and print nodes data:");

        binarySearchTree.breadthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        System.out.println("DFS and print nodes data:");

        binarySearchTree.depthFirstSearch(integer -> System.out.print(integer + " "));
        System.out.printf("%n%n");

        BinarySearchTree<Rectangle> rectanglesBinarySearchTree = new BinarySearchTree<>((rectangle1, rectangle2) -> {
            if (rectangle1 == null) {
                return rectangle2 == null ? 0 : -1;
            }

            if (rectangle2 == null) {
                return 1;
            }

            return Double.compare(rectangle1.getArea(), rectangle2.getArea());
        });

        rectanglesBinarySearchTree.insert(new Rectangle(10, 9));
        rectanglesBinarySearchTree.insert(new Rectangle(5, 4));
        rectanglesBinarySearchTree.insert(null);
        rectanglesBinarySearchTree.insert(new Rectangle(2, 4));
        rectanglesBinarySearchTree.insert(new Rectangle(50, 4));

        System.out.println("Recursive DFS and print info about rectangles:");

        AtomicInteger i = new AtomicInteger(1);

        rectanglesBinarySearchTree.depthFirstSearchRecursively(rectangle -> {
            System.out.println("Rectangle #" + i.getAndIncrement());
            System.out.println(rectangle);
            System.out.println();
        });
    }
}