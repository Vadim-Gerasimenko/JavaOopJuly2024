package ru.academits.gerasimenko.shapes.main;

import ru.academits.gerasimenko.shapes.*;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(2), new Square(4),
                new Triangle(1, 0, 5, 0, 4, 4),
                new Triangle(7, 3, 5, 4, 4, 5),
                new Rectangle(10, 20),
                new Rectangle(11, 8),
                new Circle(2),
                new Circle(3),
                new Circle(7)
        };

        System.out.println("Information about shape with largest area:");

        Shape shapeWithLargestArea = ShapesSort.getShapeWithOrderLargestArea(shapes, 1);
        System.out.println(shapeWithLargestArea);

        System.out.println();

        System.out.println("Information about shape with second largest perimeter:");

        Shape shapeWith2LargestArea = ShapesSort.getShapeWithOrderLargestPerimeter(shapes, 2);
        System.out.println(shapeWith2LargestArea);
    }
}